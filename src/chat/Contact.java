package chat;

import java.io.Serializable;
import java.util.ArrayList;

public class Contact implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6674375979439005366L;
	private String username;
	private transient int userId;
	
	public Contact(int userId){
		// TODO: Request username from server, then request user status.
	}
	
	public Contact(String userId){
		// TODO: Request userId from server, then request user status.
	}
	
	public static ArrayList<Contact> getContacts(int userId){
		// TODO: Request contacts from server. Server will do DB query.
		return null;
	}
	
	public String getName(){ 
		return username;
	}
	
	public UserStatus getStatus(){
		// TODO: Request user status from server
		return UserStatus.ONLINE;
	}
	
}
