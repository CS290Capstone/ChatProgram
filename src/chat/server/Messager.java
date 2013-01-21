package chat.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import chat.UserCredentials;
import chat.client.message.Message;
import chat.client.message.Recipient;

public class Messager extends ServerProcess{

	private final Socket sock;
	
	public Messager(Socket sock, UserCredentials user) {
		super(user);
		this.sock = sock;
	}

	@Override
	public void run() {
		try {
			
			//ObjectOutputStream out = new ObjectOutputStream(sock.getOutputStream());
			ObjectInputStream in = new ObjectInputStream(sock.getInputStream());
			
			final Message msg = (Message) in.readObject();
			
			ArrayList<Recipient> recipients = (ArrayList<Recipient>) in.readObject();
			
			ThreadPoolExecutor executor = new ThreadPoolExecutor(10,10,100,TimeUnit.SECONDS,new ArrayBlockingQueue<Runnable>(1000));
			for (final Recipient r : recipients){
				executor.submit(new Runnable(){
					@Override
					public void run() {
						// TODO: Send message to individual clients
						if (r.isOnline()){
							try {
								
								Socket con = r.createSocket();
								
								ObjectOutputStream cOut = new ObjectOutputStream(con.getOutputStream());
								
								cOut.writeObject(msg);
								
								con.close();
								
							} catch (UnknownHostException e) {
								e.printStackTrace();
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
					}
					
				});
			}
			executor.shutdown();
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}

}
