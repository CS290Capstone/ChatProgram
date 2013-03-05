package chat.client.message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import chat.client.MessagePanel;
import chat.server.processes.UserDataRetriever;

public class Message implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6329945084225106898L;

	public enum MessageType{
		TEXT, //
		FILE_TRANSFER, //
		RECIEPT, //
		REGISTER, //
		
		GET_CONTACTS,
		GET_CONVERSATION,
		GET_USERSTATUS,
        GET_USERDATA,

		UPDATE_STATUS
	}
	
	protected String user, msg;	
	protected Date time;
	protected MessageType type = MessageType.TEXT;
	protected int conversationId;

    protected UserDataRetriever.UserData userdata;

    /**
     * For standard text messages
     * */
	public Message(String user, String msg, int conversationId){
		this.user = user;
		this.msg = msg;
		this.time = new Date();
		this.conversationId = conversationId;
	}

    /**
     * For data retrieval (ID number, UserName, IPaddress)
     * */
    public Message(UserDataRetriever.UserData data, String info){
        this.userdata = data;
        this.msg = info;
        this.type = MessageType.GET_USERDATA;
        this.time = new Date();
    }

    /**
     * For Misc. messages
     * */
	public Message(String user, MessageType type, int conversationId) {
		this.user = user;
		this.type = type;
		this.conversationId = conversationId;
		this.time = new Date();
	}

	public int getConversationId(){
		return conversationId;
	}
	
	public String getSender(){
		return user;
	}
	
	public MessageType getMessageType(){
		return type;
	}

    public UserDataRetriever.UserData getDataType(){
        return userdata;
    }
	
	public String getMessageText(){
		return msg;
	}
	
	public String getDateString(){
		Calendar dateSent = Calendar.getInstance();
		dateSent.setTime(this.time);
		
		Calendar dateNow = Calendar.getInstance();
		dateNow.setTime(new Date());
		
		SimpleDateFormat f;
		
		if (dateSent.get(Calendar.YEAR) == dateNow.get(Calendar.YEAR) &&
				dateSent.get(Calendar.DAY_OF_YEAR) == dateNow.get(Calendar.DAY_OF_YEAR)){
			f = new SimpleDateFormat("h:m.s a");
			return f.format(time);
		}else{
			f = new SimpleDateFormat("e h:m a");
			return f.format(time);
		}
	}
	
	public Date getDateSent(){
		return time;
	}
	
	private void readObject(ObjectInputStream inputStream) throws IOException, ClassNotFoundException{
		inputStream.defaultReadObject();
		this.time = new Date();
	}
	
	public MessagePanel toMessagePanel(){
		return new MessagePanel(this);
	}
	
}
