package chat.client.message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

public class Recipient implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2868787654483774424L;
	private transient int userId;
	private String user;
	
	private void readObject(ObjectInputStream inputStream) throws IOException, ClassNotFoundException{
		inputStream.defaultReadObject();
		// TODO: Request userId from server
	}

	public Recipient(int userId){
		this.userId = userId;
		// TODO: Request username from server
	}
	
	public int getUserId() {
		return userId;
	}
	
	public String getUserName() {
		return user;
	}
	
}
