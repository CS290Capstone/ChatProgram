package chat.server.processes;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

import chat.UserCredentials;
import chat.server.Server;

public class Registerer extends ServerProcess{
	
	private final Socket sock;
	
	public Registerer(Socket sock, UserCredentials user){
		super(user);
		this.sock = sock;
	}
	
	@Override
	public void run() {
		
		try {
			
			ObjectOutputStream out = new ObjectOutputStream(sock.getOutputStream());
			
			if (Server.userExists(user)){
				//out.writeBoolean(false); // Tell client registration failed
				out.writeInt(0);
				Server.m("User registration failed: " + user.toString());
			}else{
				if (server.registerUser(user)){
					//out.writeBoolean(true); // Tell client registration succeeded
					out.writeInt(1);
					Server.m("User registered: " + user.toString());
				}else{
					out.writeBoolean(false);
					out.writeInt(-1);
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
