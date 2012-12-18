package chat.server;

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

public class Server {
	
	private String DB_SCHEMA = "ChatProgram";
	private String DB_USER = "chatprogram";
	private String DB_PASS = "chat";
	private String DB_URL = "jdbc:mysql://skorcraft.net/"+DB_SCHEMA+"?user="+DB_USER+"&password="+DB_PASS;
	private String DB_CLASS = "com.mysql.jdbc.Driver";	
	
	private Thread registerListener;
	
	public static void main(String args[]){
		
		new Server();
		
	}
	
	private Server(){
		try {
			Class.forName(DB_CLASS);
			
			registerListener = new Thread(new RegisterListener(this,ServerPorts.RegisterListener));
			registerListener.start();
			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private static ArrayList<Contact> getContacts(int userId){
		// TODO: Compile contact list for given userId
		/*
		 * 	SELECT username FROM user AS u, usercontacts AS uc
		 * 		WHERE u.uid = uc.uid
		 * 			AND u.uid = userId
		 * 
		 * */
		return null;
	}
	
	private static boolean validateCredentials(UserCredentials user){
		
		return true;
	}
	
	protected boolean userExists(UserCredentials user){
		
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
	
	protected void m(String msg){
		System.out.println(new Date().toString() + " > " + msg);
	}
	
	protected void m(String...msg){
		m(msg[0]);
		if (msg.length>1)
			for (int i = 1; i < msg.length; i++)
				m("    "+msg[i]);
	}

}
