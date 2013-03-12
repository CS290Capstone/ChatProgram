package chat.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import chat.Contact;
import chat.ServerPorts;
import chat.UserCredentials;
import chat.UserStatus;
import chat.client.message.Conversation;
import chat.client.message.Message;
import chat.client.message.Recipient;

public class Server {
	
	/*private String DB_SCHEMA;// = "ChatProgram";
	private String DB_USER;// = "chatprogram";
	private String DB_PASS;// = "chat"; */
	private String DB_URL;// = "jdbc:mysql://skorcraft.net/"+DB_SCHEMA+"?user="+DB_USER+"&password="+DB_PASS;
	private String DB_CLASS = "com.mysql.jdbc.Driver";	
	
	private Thread /*registerListener,*/ commandListener;
	private static Server server;
	private final ThreadPoolExecutor executor;
	
	private String host;
	
	public static void main(String args[]){
		
		/*	-u:UserName
			-p:PassWord
			-s:Schema
			-a:URL(Address)*/
		
		String u,p,s;
		u = p = s = null;
		String a = "skorcraft.net";
		for (String arg : args){
			if (arg.toLowerCase().startsWith("-u:")){
				u = arg.substring(3, arg.length());
			}else if(arg.toLowerCase().startsWith("-p:")){
				p = arg.substring(3, arg.length());
			}else if(arg.toLowerCase().startsWith("-s:")){
				s = arg.substring(3, arg.length());
			}else if(arg.toLowerCase().startsWith("-a:")){
				a = arg.substring(3, arg.length());
			}else{
				System.out.println("Invalid Argument: \""+arg+"\"");
				break;
			}
		}
		
		if (s==null || u==null || p==null){
			m("Server startup failed: Null database login input!");
		}else{
			Server.server = new Server(String.format("jdbc:mysql://%s/%s?user=%s&password=%s", a,s,u,p));
		}
		
		
	}
	
	protected synchronized ThreadPoolExecutor getExecutor(){
		return executor;
	}
	
	private Server(String url){
		//Server.server = this;
		this.DB_URL = url; System.out.println(url);
		
		this.executor = new ThreadPoolExecutor(10,10,1000,TimeUnit.SECONDS,new ArrayBlockingQueue<Runnable>(1000)); 
		
		try {
			Class.forName(DB_CLASS);
			
			commandListener = new Thread(new CommandListener(ServerPorts.CommandListener));
			executor.submit(commandListener);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		this.executor.shutdown();
	}
	
	public static Server getServer(){
		return Server.server;
	}
	
	@Deprecated
	protected ArrayList<Contact> getContacts(UserCredentials user){		
		try {
			Connection con = DriverManager.getConnection(DB_URL);
			Statement stmt = con.createStatement();
			
			ResultSet rs = stmt.executeQuery(String.format("SELECT UC.username FROM User AS u, UserContacts as UC WHERE u.uid = uc.uid AND u.uid = '%s'",user.getUserId()));
			
			if (rs != null){
				ArrayList<Contact> contacts = new ArrayList<Contact>();
				while (rs.next()){
					String username = rs.getString("username");
					String displayname = rs.getString("displayname");
					int userid = rs.getInt("uid");
					contacts.add(new Contact(username,displayname,userid,null));
				}
				return contacts;
			}
		} catch (SQLException e) {
			m(new String[]{"Contact retrieval failed: " + user.toString(), "Error: " + e.getMessage()});
		}
		return null;
	}
	
	public static boolean validateCredentials(UserCredentials user){
		try{
			Connection con = DriverManager.getConnection(Server.getServer().DB_URL);
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(String.format("SELECT password FROM User WHERE username = '%s';", user.getUser()));
			if (rs != null){
				if (rs.getString("password").equals(user.getPass())) return true;
			}
			
		}catch (SQLException e){
			m(new String[]{"Credential validation failed: " + user.toString(), "Error: " + e.getMessage()});
		}
		return false;
	}
	
	public static boolean userExists(UserCredentials user){
		try{
			Connection con = DriverManager.getConnection(Server.getServer().DB_URL);
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(String.format("SELECT uid FROM User WHERE username = '%s';", user.getUser()));
			
			if (rs.next()) return true;
			
		}catch (SQLException e){
			m(new String[]{"User Exists check failed: " + user.toString(), "Error: " + e.getMessage()});
		}
		return false;
	}
	
	public boolean registerUser(UserCredentials user){
		try {
			Connection con = DriverManager.getConnection(DB_URL);
			Statement stmt = con.createStatement();
			
			String userInsert = String.format("INSERT INTO `User`(username,password,name) VALUES('%s','%s','%s');", user.getUser(), user.getPass(), user.getName());
			
			stmt.executeUpdate(userInsert);
			ResultSet rs = stmt.executeQuery("SELECT uid FROM `User` WHERE username='"+user.getUser()+"';");
			rs.next();
			int userId = rs.getInt(1);
			String userDeptInsert = String.format("INSERT INTO `UserDepartment`(uid,did) VALUES(%d,%d);", userId, user.getDept());
			stmt.executeUpdate(userDeptInsert);
			
			/*String loginInsert = String.format("INSERT INTO Login(uid,time,ipaddress,success) VALUES(%d, NOW(), '%s', true);", userId, user.getIp());
			stmt.executeQuery(loginInsert);*/
			
			//System.out.println("registered user.");
			
			return true;
		} catch (SQLException e) {
			//e.printStackTrace();
			m(new String[]{"User registration failed: " + user.toString(), "Error: " + e.getMessage()});
			return false;
		}
	}
	
	public String getUserIP(Recipient r){
		try {
			Connection con = DriverManager.getConnection(DB_URL);
			Statement stmt = con.createStatement();
			
			String q = String.format("SELECT L.ipaddress FROM Login L WHERE uid = %d ORDER BY L.time DESC;", r.getUserId());
			
			ResultSet rs = stmt.executeQuery(q);
			
			if (rs.next()){
				return rs.getString("L.ipaddress");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "";
	}
	
	public Conversation getConversation(int id){
		try {
			Connection con = DriverManager.getConnection(DB_URL);
			Statement stmt = con.createStatement();
			
			String q = String.format("SELECT M.message, UM.time, U.username FROM Conversation C, UserMessage UM, Message M, User U, WHERE U.uid = C.uid AND C.cid = UM.cid AND UM.mid = M.mid AND C.cid = %d;", id);
			
			ResultSet rs = stmt.executeQuery(q);
			
			Conversation c = new Conversation(id);
			ArrayList<Message> messages = new ArrayList<Message>();
			while (rs.next()){
				c.addRecipient(new Recipient(rs.getInt("U.uid")));
				messages.add(new Message(rs.getString("U.username"),rs.getString("M.message"),id)); // Still need to incorporate time into the message object
			}
			
			return c;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void m(String msg){
		System.out.println(new Date().toString() + " > " + msg);
	}
	
	protected static void m(String...msg){
		m(msg[0]);
		if (msg.length>1)
			for (int i = 1; i < msg.length; i++)
				m("    "+msg[i]);
	}

	public String getDbUrl(){
		return this.DB_URL;
	}
	
	@Deprecated
	protected static UserStatus getUserStatus(Contact contact){
		try{
			Socket sock = new Socket("localhost",ServerPorts.ClientListener);
			ObjectInputStream in = new ObjectInputStream(sock.getInputStream());
			UserStatus status = (UserStatus) in.readObject();
			sock.close();
			return status;
		}catch (IOException e){
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return UserStatus.ONLINE;
	}
}
