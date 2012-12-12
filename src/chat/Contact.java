package chat;

import java.util.ArrayList;

public class Contact {

	private String username;
	private UserStatus status;
	
	public Contact(int userId){
		// TODO: Request username from server, then request user status.
	}
	
	public static ArrayList<Contact> getContacts(int userId){
		// TODO: Request contacts from server. Server will do DB query.
		return null;
	}
}
