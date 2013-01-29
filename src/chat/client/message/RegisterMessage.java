package chat.client.message;

public class RegisterMessage extends Message{

	public RegisterMessage(String user, int conversationId) {
		super(user, MessageType.REGISTER, conversationId);
		// TODO: Create RegisterMessage
	}

}
