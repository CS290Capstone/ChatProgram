package chat.client.message;

import java.io.Serializable;
import java.util.ArrayList;


public class Conversation implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4302372160551159831L;
	private ArrayList<Recipient> recipients = new ArrayList<Recipient>();
	private transient ArrayList<Message> messages = new ArrayList<Message>();
	private int conversationId;
	
	public Conversation(int id){
		this.conversationId = id;
		init();
	}
	
	public Conversation() {
		this.conversationId = getConversationId();
		init();
	}
	
	private void init(){
		this.recipients = getRecipients();
		this.messages = getMessages();
	}

	private int getConversationId(){
		return conversationId;
	}
	
	public ArrayList<Message> getMessages(){
		return messages;
	}
	
	public ArrayList<Recipient> getRecipients(){
		return recipients;
	}
		
	public void leaveConversation(Recipient user){
		if (recipients!=null && recipients.contains(user))
			recipients.remove(user);
	}
	
}
