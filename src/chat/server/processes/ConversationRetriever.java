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

import chat.UserCredentials;
import chat.client.message.Conversation;
import chat.client.message.Message;
import chat.client.message.Recipient;
import chat.server.Server;

public class ConversationRetriever extends ServerProcess{

	private final Socket sock;
	
	public ConversationRetriever(Socket sock, UserCredentials user) {
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
				ResultSet rs = s.executeQuery("SELECT * FROM Message M, UserMessage UM, Conversation C, User U WHERE M.mid = UM.mid AND C.cid = UM.cid AND C.uid = U.uid AND UM.cid = "+ msg.getConversationId() +";");
				ResultSet rs2 = s.executeQuery("SELECT * FROM Conversation C, User U WHERE C.uid = U.uid AND C.cid = "+ msg.getConversationId() +";");
				
				if (rs != null && rs2 != null){

					ArrayList<Recipient> recipients = new ArrayList<Recipient>();
					
					if (rs.next()){
						
						Conversation c = new Conversation();
						ArrayList<Message> messages = new ArrayList<Message>();
						
						c.setId(rs.getInt("UM.cid"));
						
						while (rs.next()){
							messages.add(new Message(rs.getString("U.username"),rs.getString("M.message"),rs.getInt("C.cid")));
						}
						
						while (rs2.next()){
							if (rs.getBoolean("C.active"))
								recipients.add(new Recipient(rs2.getInt("U.uid")));
						}
						
						out.writeObject(c);
						
					}
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
