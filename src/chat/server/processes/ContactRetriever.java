package chat.server.processes;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import chat.Contact;
import chat.UserCredentials;
import chat.client.message.Message;
import chat.server.Server;

public class ContactRetriever extends ServerProcess{
	
	private final Socket sock;

	public ContactRetriever(Socket sock, UserCredentials user) {
		super(user);
		this.sock = sock;
	}

	@Override
	public void run() {
		try {
			ObjectInputStream in = new ObjectInputStream(sock.getInputStream());
			ObjectOutputStream out = new ObjectOutputStream(sock.getOutputStream());
			
			final Message msg = (Message) in.readObject();
			
			try {
				Connection con = DriverManager.getConnection(Server.getServer().getDbUrl());
				Statement s = con.createStatement();
				ResultSet rs = s.executeQuery("SELECT U.username, U.displayname, U.uid, UC.ucid FROM User U, UserContacts UC WHERE UC.uid = U.uid;");
				
				if (rs != null){
					ArrayList<Contact> contacts = new ArrayList<Contact>();
					while (rs.next()){
						Contact c = new Contact(rs.getString("username"),rs.getString("displayname"),rs.getInt("ucid"),null);
						contacts.add(c);
					}
					out.writeObject(contacts);
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
