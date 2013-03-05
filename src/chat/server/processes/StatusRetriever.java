package chat.server.processes;

import java.net.Socket;

import chat.UserCredentials;

public class StatusRetriever extends ServerProcess{

	private final Socket sock;
	
	public StatusRetriever(Socket sock, UserCredentials user){
		super(user);
		this.sock = sock;
	}

	@Override
	public void run() {
		// TODO: Create StatusRetriever
		
	}
	
}
