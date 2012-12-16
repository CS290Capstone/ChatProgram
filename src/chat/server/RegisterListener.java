package chat.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import chat.UserCredentials;

public class RegisterListener implements Runnable{
	
	private final Server svr;
	
	private ServerSocket svrSocket;
	private int port;
	
	public RegisterListener(Server svr, int port){
		this.svr = svr;
		this.port = port;
	}

	@Override
	public void run() {
		try {
			svrSocket = new ServerSocket(port);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		while(true){
			try {
				
				
				Socket con = svrSocket.accept();
				System.out.println("server is connected to user...");

				ObjectOutputStream out = new ObjectOutputStream(con.getOutputStream());
				ObjectInputStream in = new ObjectInputStream(con.getInputStream());

				UserCredentials user = (UserCredentials) in.readObject();
				
				System.out.println("got user info...");
				
				user.setIp(con.getInetAddress().getHostAddress());
				
				System.out.println("set IP...");

				
				System.out.println("Sending response...");
				if (svr.userExists(user)){
					out.writeBoolean(false); // Tell client registration failed
					System.out.println("Sent false");
				}else{
					out.writeBoolean(svr.registerUser(user)); // Tell client registration succeeded
					System.out.println("Sent true");
				}
				out.flush();
				out.close();
				in.close();
				System.out.println("Done.");
				
				Thread.sleep(100);
				
			} catch (IOException | InterruptedException | ClassNotFoundException e) {
				e.printStackTrace();
				break;
			}
		}
		
	}

}
