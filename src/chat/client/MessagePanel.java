package chat.client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;

import chat.client.message.Message;

@SuppressWarnings("serial")
public class MessagePanel extends JPanel{
	private JLabel lblUser, lblDate;
	private JTextPane txtpMsg;
	
	public MessagePanel(Message msg){
		this.setLayout(new BorderLayout());
		this.setBackground(Color.WHITE);
		
		lblUser = new JLabel(msg.getSender());
		lblUser.setVerticalAlignment(SwingConstants.TOP);
		
		this.add(lblUser, BorderLayout.WEST);
		
		txtpMsg = new JTextPane();
		txtpMsg.setEditable(false);
		txtpMsg.setText(msg.getMessageText());
		
		this.add(txtpMsg, BorderLayout.CENTER);
		
		lblDate = new JLabel(msg.getDateString());
		lblDate.setVerticalAlignment(SwingConstants.TOP);
		
		this.add(lblDate,BorderLayout.EAST);
	}

	public MessagePanel(String user, String message) {
		this(new Message(user,message));
	}
	
}
