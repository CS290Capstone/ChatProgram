package chat.server.processes;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import chat.UserCredentials;
import chat.server.Server;

public class LoginListener extends ServerProcess{

	private Socket sock;
	
	public LoginListener(Socket sock, UserCredentials user) {
		super(user);
		this.sock = sock;
	}

	@Override
	public void run() {

        try {
        	
			ObjectInputStream in = new ObjectInputStream(sock.getInputStream());
	        ObjectOutputStream out = new ObjectOutputStream(sock.getOutputStream());
	        
	        Connection con = DriverManager.getConnection(Server.getServer().getDbUrl());
            Statement stmt = con.createStatement();
            
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
