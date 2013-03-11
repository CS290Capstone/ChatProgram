package chat.client.message;

import java.io.IOException;
import java.io.Serializable;
import java.net.Socket;

import chat.ServerPorts;
import chat.UserStatus;
import chat.client.Client;
import chat.server.Server;
import chat.server.processes.UserDataRetriever;

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
        if (user != null && userId == 0){
            Message msg = new Message(UserDataRetriever.UserData.IDNumber, user);
            // TODO: Request ID from server
            
        }
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
	
	public Socket createSocket() throws IOException{
		return new Socket(Server.getServer().getUserIP(this),ServerPorts.ClientListener);
	}
	
}
