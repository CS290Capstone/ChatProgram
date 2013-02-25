package chat.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

import chat.UserCredentials;
import chat.client.message.Message.MessageType;
import chat.server.processes.ContactRetriever;
import chat.server.processes.ConversationRetriever;
import chat.server.processes.Messager;
import chat.server.processes.Registerer;

public class CommandListener implements Runnable{
	private ServerSocket svrSocket;
	
	public CommandListener(int port){
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
				Socket socket = svrSocket.accept();
				
				//ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
				ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
				
				// expecting MessageType enum
				
				MessageType m = (MessageType) in.readObject();
				UserCredentials user = (UserCredentials) in.readObject();
				
				switch (m){
					case GET_CONTACTS:
						Server.getServer().getExecutor().submit(new ContactRetriever(socket,user));
						break;
					case GET_CONVERSATION:
						Server.getServer().getExecutor().submit(new ConversationRetriever(socket,user));
						break;
					case GET_USERSTATUS:
						
						break;
					case REGISTER:
						Server.getServer().getExecutor().submit(new Registerer(socket,user));
						break;
						
					case TEXT:
						Server.getServer().getExecutor().submit(new Messager(socket, user));
						break;
						
					case FILE_TRANSFER:
						// XXX: File transfer
						break;
					case RECIEPT:
						// XXX: Client responds with answer to a file transfer or other request.
						break;
					default: // unexpected type
						Server.m("Unexpected MessageType enum recieved ("+m.name()+") from: "+socket.getInetAddress()+".");
				}
				
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}

}
