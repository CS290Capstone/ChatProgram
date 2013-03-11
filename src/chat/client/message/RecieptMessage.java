package chat.client.message;
@Deprecated
public class RecieptMessage extends Message{

	/**
	 * 
	 */
	private static final long serialVersionUID = 865559936976727861L;
	private final boolean answer;
	private final int messageId;
	
	public RecieptMessage(String user, boolean answer, int messageId, int conversationId) {
		super(user, MessageType.RECIEPT, conversationId);
		// XXX: Create RecieptMessage
		this.answer = answer;
		this.messageId = messageId;
	}
	
	public boolean getAnswer(){
		return answer;
	}
	
	public int getMessageId(){
		return messageId;
	}

}
