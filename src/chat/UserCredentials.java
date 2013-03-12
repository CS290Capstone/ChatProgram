package chat;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;

import chat.client.Client;
import chat.client.message.Message;
import chat.client.message.Message.MessageType;
import chat.server.processes.UserDataRetriever.UserData;

public class UserCredentials implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6489156545872614076L;
	private String username, password, name, ip;
	private int department, userid;
	
	public UserCredentials(String u, String p, String name, int d){
		this.username = u;
		this.password = p;
		this.name = name;
		this.department = d;
	}
	
	public UserCredentials(Contact contact){
		this.username = contact.getUserName();
		this.password = this.name = null;
	}
	
	public void setIp(String ip){
		this.ip = ip;
	}
	
	public String getIp(){
		return ip;
	}
	
	public String getUser(){
		return username;
	}
		
	public int getUserId(){
		if (userid == 0){
			Socket sock = null;
			try {
				System.out.println("Attempting to retrieve userID");
				sock = new Socket(Client.getClient().getServerAddress(), ServerPorts.CommandListener);
				
				ObjectInputStream in = new ObjectInputStream(sock.getInputStream());
	            ObjectOutputStream out = new ObjectOutputStream(sock.getOutputStream());
	            
	            out.writeObject(MessageType.GET_USERDATA);
	            out.writeObject(this);
	            out.writeObject(new Message(UserData.IDNumber, username));
	            
	            this.userid = in.readInt();
				
			} catch (Exception e) {
					try {
						if (sock !=null){
							sock.close();
						}
					} catch (IOException e1) {}
			}	
		}
		return userid;
	}
	
	public String getPass(){
		return password;
	}
	
	public String getName(){
		return name;
	}
	
	public int getDept(){
		return department;
	}

	public String toString(){
		return String.format("[IP=\"%s\";USERNAME=\"%s\";NAME=\"%s\";]", ip, username, name);
	}
}
