package chat.client.message;

public class FileMessage extends Message{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7258686162452120901L;

	public FileMessage(String user, int conversationId) {
		super(user, MessageType.FILE_TRANSFER, conversationId);
		// TODO: Create FileMessage
	}

}
