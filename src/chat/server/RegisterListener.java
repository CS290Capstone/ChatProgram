package chat.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import chat.UserCredentials;

public class RegisterListener implements Runnable{
	
	private final Server s;
	
	private ServerSocket svrSocket;
	private int port;
	
	public RegisterListener(Server svr, int port){
		this.s = svr;
		this.port = port;
		try {
			svrSocket = new ServerSocket(port);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		while(true){
			try {
				Socket con = svrSocket.accept();
				//System.out.println("server is connected to user...");
				
				ObjectOutputStream out = new ObjectOutputStream(con.getOutputStream());
				ObjectInputStream in = new ObjectInputStream(con.getInputStream());

				UserCredentials user = (UserCredentials) in.readObject();
				
				//System.out.println("got user info...");
				
				user.setIp(con.getInetAddress().getHostAddress());
				
				//System.out.println("set IP...");

				
				//System.out.println("Sending response...");
				if (s.userExists(user)){
					out.writeBoolean(false); // Tell client registration failed
					//System.out.println("Sent false");
					s.m("User registration failed: " + user.toString());
				}else{
					if (s.registerUser(user)){
						out.writeBoolean(true);// Tell client registration succeeded
						s.m("User registered: " + user.toString());
					}else{
						out.writeBoolean(false);
						// Failed message sent written to console by Server.class
					}
					//System.out.println("Sent true");
					
					
				}
				out.flush();
				out.close();
				in.close();
				//System.out.println("Done.");
				
				Thread.sleep(100);
				
			} catch (IOException | InterruptedException | ClassNotFoundException e) {
				e.printStackTrace();
				break;
			}
		}
		
	}

}
