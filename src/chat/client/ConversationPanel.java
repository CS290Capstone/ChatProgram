package chat.client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Rectangle;
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
	
	protected JScrollPane getScrollPanel(){
		return scrlChat;
	}
	
	protected JPanel getConversationBasePanel(){
		return pnlConversationBase;
	}
	
	protected JPanel getConversationPanel(){
		return pnlConversation;
	}
	
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
			//System.out.println("Added '"+m.getMessageText()+"'");
		}
		
		this.setLayout(new BorderLayout());
		this.add(scrlChat,BorderLayout.CENTER);
		
		/*new Thread(new Runnable(){
		@Override
		public void run() {
			for (int i = 0; i < 30; i++){
				final MessagePanel mp = new MessagePanel("username","FILLER FILLER FILLER FILLER FILLER FILLER FILLER FILLER FILLER FILLER FILLER FILLER FILLER FILLER FILLER FILLER FILLER FILLER FILLER FILLER FILLER FILLER FILLER FILLER FILLER FILLER FILLER FILLER FILLER FILLER FILLER FILLER FILLER FILLER FILLER FILLER FILLER FILLER FILLER FILLER FILLER FILLER FILLER FILLER FILLER Message #"+(i+1));
				pnlConversation.add(mp);
				pnlConversation.revalidate();
				int height = (int) pnlConversation.getPreferredSize().getHeight();
				Rectangle rect = new Rectangle(0,height,10,mp.getHeight());
				pnlConversation.scrollRectToVisible(rect);
				pnlConversation.revalidate();
			
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}).start();*/
		
	}
	
}
