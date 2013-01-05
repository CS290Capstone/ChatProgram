package chat;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import chat.client.message.Message.MessageType;

public class Contact implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6674375979439005366L;
	
	private String username, displayname;
	private UserStatus status;
	private int userid;
	
	public Contact(String username, String displayname, int userid, UserStatus status){
		this.username = username;
		this.displayname = displayname;
		this.userid = userid;
		this.status = status;
	}
	
	public String getDisplayName(){
		return displayname;
	}
	
	public String getUserName(){
		return username;
	}
	
	public UserStatus getStatus(){
		status = getUpdatedStatus();
		return status;
	}
	
	private UserStatus getUpdatedStatus(){ // called from client
		try {
			
			Socket s = new Socket("localhost",ServerPorts.CommandListener);
			
			ObjectInputStream in = new ObjectInputStream(s.getInputStream());
			ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());

			out.writeObject(MessageType.CMD_GET_CONTACTS);
			out.writeObject(new UserCredentials(this));
			
			UserStatus newStatus = (UserStatus) in.readObject();
			
			in.close();
			out.close();
			
			return newStatus;
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return status;
	}
	
	public int getUserId(){
		return userid;
	}
	
	@SuppressWarnings("unchecked")
	public static ArrayList<Contact> getContacts(UserCredentials user){ // called from client
		try {
			
			Socket s = new Socket("localhost",ServerPorts.CommandListener);
			
			ObjectInputStream in = new ObjectInputStream(s.getInputStream());
			ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());

			out.writeObject(MessageType.CMD_GET_CONTACTS);
			out.writeObject(user);
			
			ArrayList<Contact> contacts = (ArrayList<Contact>) in.readObject();
			
			in.close();
			out.close();
			
			return contacts;
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
}
