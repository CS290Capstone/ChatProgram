package chat.client.message;

import java.io.Serializable;
import java.util.ArrayList;

public class Conversation implements Serializable{
	private ArrayList<Recipient> recipients;
	
	public ArrayList<Recipient> getRecipients(){
		return recipients;
	}
		
	public void leaveConversation(Recipient user){
		if (recipients!=null && recipients.contains(user))
			recipients.remove(user);
	}
}
