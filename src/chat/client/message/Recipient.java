package chat.client.message;

import java.io.IOException;
import java.io.Serializable;
import java.net.Socket;
import java.net.UnknownHostException;

import chat.ServerPorts;
import chat.UserStatus;

public class Recipient implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2868787654483774424L;
	private int userId;
	private String user;
	
	public Recipient(int userId){
		this.userId = userId;
		this.user = getUserName();
	}
	
	public Recipient(String user){
		this.user = user;
		this.userId = getUserId();
	}
	
	public int getUserId() {
		// TODO: Request userId from server
		return userId;
	}
	
	public String getUserName() {
		// TODO: Request username from server
		return user;
	}
	
	public boolean isOnline(){
		return getStatus().isOnline();
	}
	
	public UserStatus getStatus(){
		// TODO: Request userstatus from server
		return UserStatus.ONLINE;
	}
	
	public Socket createSocket() throws UnknownHostException, IOException{
		// TODO: Request user IP from server
		// Get latest IP address that was successful and create a socket.
		String client = "";
		return new Socket(client,ServerPorts.ClientListener);
	}
	
}
