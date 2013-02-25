package chat.client.message;

public class ConversationMessage extends Message{

	/**
	 * 
	 */
	private static final long serialVersionUID = 9134374480406585677L;
	
	public ConversationMessage(String user, MessageType type, int conversationId){
		super(user,type,conversationId);
	}
	
}
