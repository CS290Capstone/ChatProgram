package chat.server.processes;

import chat.UserCredentials;
import chat.server.Server;

abstract class ServerProcess implements Runnable{

	protected final Server server;
	protected final UserCredentials user;
	
	public ServerProcess (UserCredentials user){
		this.server = Server.getServer();
		this.user = user;
	}
	
	
	
}
