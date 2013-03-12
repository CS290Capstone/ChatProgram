package chat.client.message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import chat.ServerPorts;
import chat.UserCredentials;
import chat.UserStatus;
import chat.client.Client;
import chat.client.message.Message.MessageType;
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
            Socket sock = null;
            try {				
            	sock = new Socket(Client.getClient().getServerAddress(), ServerPorts.CommandListener);
            	
            	ObjectInputStream in = new ObjectInputStream(sock.getInputStream());
                ObjectOutputStream out = new ObjectOutputStream(sock.getOutputStream());
                
                out.writeObject(MessageType.GET_USERDATA);
                out.writeObject(new UserCredentials(user,null,null,0));
                out.writeObject(msg);
                
                userId = in.readInt();
                
                sock.close();
            	
            } catch (Exception e) {
				if (sock != null){
					try {
						sock.close();
					} catch (IOException e1) {}
				}
			}
        }
		return userId;
	}
	
	public String getUserName() {
		if (userId != 0 && user == null){
            Message msg = new Message(UserDataRetriever.UserData.UserName, ""+userId);
            Socket sock = null;
            try {				
            	sock = new Socket(Client.getClient().getServerAddress(), ServerPorts.CommandListener);
            	
            	ObjectInputStream in = new ObjectInputStream(sock.getInputStream());
                ObjectOutputStream out = new ObjectOutputStream(sock.getOutputStream());
                
                out.writeObject(MessageType.GET_USERDATA);
                out.writeObject(new UserCredentials(null,null,null,0));
                out.writeObject(msg);
                
                user = in.readUTF();
                
                sock.close();
            	
            } catch (Exception e) {
				if (sock != null){
					try {
						sock.close();
					} catch (IOException e1) {}
				}
			}
		}
		return user;
	}
	
	public boolean isOnline(){
		return getStatus().isOnline();
	}
	
	@Deprecated
	public UserStatus getStatus(){
		// XXX: Request userstatus from server
		return UserStatus.ONLINE;
	}
	
	public Socket createSocket() throws IOException{
		return new Socket(Server.getServer().getUserIP(this),ServerPorts.ClientListener);
	}
	
}
