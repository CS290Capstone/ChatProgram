package chat.client.message;

import java.io.Serializable;
import java.util.ArrayList;


public class Conversation implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4302372160551159831L;
	private ArrayList<Recipient> recipients = new ArrayList<Recipient>();
	private ArrayList<Message> messages = new ArrayList<Message>();
	private int conversationId;
	
	public Conversation(int id){
		this.conversationId = id;
	}
	
	public Conversation() {
		//this.conversationId = getConversationId();
	}

	public int getConversationId(){
		return conversationId;
	}
	
	public void setId(int id){ this.conversationId = id; }
	
	public ArrayList<Message> getMessages(){
		return messages;
	}
	
	public ArrayList<Recipient> getRecipients(){
		return recipients;
	}
	
	public void setMessages(ArrayList<Message> messages){ this.messages = messages; }
	
	public void setRecipients(ArrayList<Recipient> recipients){ this.recipients = recipients; }
	
	public void addRecipient(Recipient recipient){
		if (!recipients.contains(recipient))
			recipients.add(recipient);
	}
	
	public void removeRecipient(Recipient recipient){
		if (recipients.contains(recipient))
			recipients.remove(recipient);
	}
		
	public void leaveConversation(Recipient user){
		if (recipients!=null && recipients.contains(user))
			recipients.remove(user);
	}
	
}
