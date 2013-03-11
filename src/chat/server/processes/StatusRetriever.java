package chat.server.processes;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import chat.UserCredentials;
import chat.client.message.Message;

@Deprecated
public class StatusRetriever extends ServerProcess{

	private final Socket sock;
	private final UserCredentials user;
	
	public StatusRetriever(Socket sock, UserCredentials user){
		super(user);
		this.sock = sock;
		this.user = user;
	}

	@Override
	public void run() {
		// XXX: Create StatusRetriever
		ObjectInputStream in;
		try {
			in = new ObjectInputStream(sock.getInputStream());
			ObjectOutputStream out = new ObjectOutputStream(sock.getOutputStream());
			
			final Message msg = (Message) in.readObject();
			
			
			//Socket clientB = new Socket(Server.getServer().getUserIP(new Recipient()))			
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
}
