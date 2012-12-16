package chat.client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import chat.client.message.Conversation;
import chat.client.message.Message;

public class ConversationPanel extends JPanel{

	private Conversation convo;
	private JPanel pnlConversationBase, pnlConversation;
	private JScrollPane scrlChat;
	
	public ConversationPanel(Conversation convo){
		this.convo = convo;
		
		scrlChat = new JScrollPane();
		
		pnlConversationBase = new JPanel();
		pnlConversationBase.setMinimumSize(new Dimension(400, 400));
		pnlConversationBase.setBackground(Color.WHITE);
		scrlChat.setViewportView(pnlConversationBase);
		pnlConversationBase.setLayout(new BorderLayout(0, 0));
		
		pnlConversation = new JPanel();
		pnlConversation.setBackground(Color.WHITE);
		pnlConversationBase.add(pnlConversation, BorderLayout.SOUTH);
		pnlConversation.setLayout(new GridLayout(0, 1, 0, 0));
		pnlConversation.addComponentListener(new ComponentAdapter(){
			@Override
			public void componentResized(ComponentEvent e){
				pnlConversationBase.setPreferredSize(new Dimension(scrlChat.getWidth(),pnlConversation.getHeight()));
			}
		});
		//System.out.println(convo.getMessages());
		for (Message m : convo.getMessages()){
			pnlConversation.add(new MessagePanel(m));
			System.out.println("Added '"+m.getMessageText()+"'");
		}
		
		this.setLayout(new BorderLayout());
		this.add(scrlChat,BorderLayout.CENTER);
		
	}
	
}
