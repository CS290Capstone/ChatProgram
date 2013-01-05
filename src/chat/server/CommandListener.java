package chat.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import chat.UserCredentials;
import chat.client.message.Message.MessageType;

public class CommandListener implements Runnable{
	
	private final Server s;
	private ServerSocket svrSocket;
	
	public CommandListener(Server svr, int port){
		this.s = svr;
		try{
			svrSocket = new ServerSocket(port);
		}catch (IOException e){
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		while (true){
			try {
				Socket con = svrSocket.accept();
				
				ObjectOutputStream out = new ObjectOutputStream(con.getOutputStream());
				ObjectInputStream in = new ObjectInputStream(con.getInputStream());
				
				// expecting MessageType enum
				
				MessageType m = (MessageType) in.readObject();
				UserCredentials user = (UserCredentials) in.readObject();
				
				switch (m){
					case CMD_GET_CONTACTS:
						out.writeObject(Server.getContacts(user));
						break;
					case CMD_GET_CONVERSATION:
						
						break;
					case CMD_GET_USERSTATUS:
						
						break;
					case TEXT:
						
						break;
					case FILE_TRANSFER:
						
						break;
					case RECIEPT:
						
						break;
					default: // unexpected type
						Server.m("Unexpected MessageType enum recieved ("+m.name()+") from: "+con.getInetAddress()+".");
				}
				
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}

}
