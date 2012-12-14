package chat.client.message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Message implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6329945084225106898L;

	enum MessageType{
		TEXT,
		FILE_TRANSFER,
		COMMAND,
		RECIEPT
	}
	
	private String user, msg;
	private transient Date time;
	private MessageType type;
	
	public Message(String user, String msg){
		this.user = user;
		this.msg = msg;
		this.time = new Date();
		this.type = MessageType.TEXT;
	}
		
	public String getSender(){
		return user;
	}
	
	public MessageType getMessageType(){
		return type;
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
	
}
