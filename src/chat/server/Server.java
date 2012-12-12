package chat.server;

import java.util.ArrayList;

import chat.Contact;

public class Server {
	
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

}
