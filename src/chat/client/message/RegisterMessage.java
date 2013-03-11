package chat.client.message;

public class RegisterMessage extends Message{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6424891628805501680L;

	public RegisterMessage(String user, int conversationId) {
		super(user, MessageType.REGISTER, conversationId);
	}

}
