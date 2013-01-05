package chat.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import chat.Contact;
import chat.ServerPorts;
import chat.UserCredentials;
import chat.UserStatus;

public class Server {
	
	/*private String DB_SCHEMA;// = "ChatProgram";
	private String DB_USER;// = "chatprogram";
	private String DB_PASS;// = "chat"; */
	private String DB_URL;// = "jdbc:mysql://skorcraft.net/"+DB_SCHEMA+"?user="+DB_USER+"&password="+DB_PASS;
	private String DB_CLASS = "com.mysql.jdbc.Driver";	
	
	private Thread registerListener, commandListener;
	
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
			a = String.format("jdbc:mysql://%s/%s?user=%s&password=%s", a,s,u,p);
			new Server(a);
		}
		
		
	}
	
	private Server(String url){
		this.DB_URL = url;
		try {
			Class.forName(DB_CLASS);
			
			registerListener = new Thread(new RegisterListener(this,ServerPorts.RegisterListener));
			commandListener = new Thread(new CommandListener(this,ServerPorts.CommandListener));
			
			registerListener.start();
			commandListener.start();
			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	protected static ArrayList<Contact> getContacts(UserCredentials user){
		// TODO: Compile contact list for given user
		
		/*
		 * 	SELECT username FROM user AS u, usercontacts AS uc
		 * 		WHERE u.uid = uc.uid
		 * 			AND u.uid = userId
		 * 
		 * */
		return null;
	}
	
	private static boolean validateCredentials(UserCredentials user){
		// TODO: Verify credentials.
		return true;
	}
	
	protected boolean userExists(UserCredentials user){
		// TODO: Check to see if user exists.
		return false;
	}
	
	protected boolean registerUser(UserCredentials user){
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
	
	protected static void m(String msg){
		System.out.println(new Date().toString() + " > " + msg);
	}
	
	protected static void m(String...msg){
		m(msg[0]);
		if (msg.length>1)
			for (int i = 1; i < msg.length; i++)
				m("    "+msg[i]);
	}

	protected static UserStatus getUserStatus(Contact contact){
		try{
			Socket sock = new Socket("localhost",ServerPorts.ClientStatusListener);
			ObjectInputStream in = new ObjectInputStream(sock.getInputStream());
			UserStatus status = (UserStatus) in.readObject();
			return status;
		}catch (IOException e){
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return UserStatus.ONLINE;
	}
}
