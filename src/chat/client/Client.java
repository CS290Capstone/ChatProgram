package chat.client;

import java.awt.BorderLayout;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JSplitPane;
import java.awt.Component;

import javax.swing.AbstractButton;
import javax.swing.Box;
import javax.swing.JMenu;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JMenuItem;

import chat.UserStatus;
import chat.client.message.Conversation;
import chat.client.message.Message;

import javax.swing.JRadioButtonMenuItem;
import javax.swing.ButtonGroup;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.border.BevelBorder;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import java.awt.Color;
import javax.swing.JTextArea;
import java.util.Enumeration;

import javax.swing.JButton;
import java.awt.Dimension;
import javax.swing.SwingConstants;
import javax.swing.ScrollPaneConstants;
import java.awt.GridLayout;
import javax.swing.JTabbedPane;
import java.awt.event.KeyEvent;
import javax.swing.JPopupMenu;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@SuppressWarnings("serial")
public class Client extends JFrame {

	private JPanel contentPane;//, pnlConversation, pnlConversationBase;
	//private JScrollPane scrlChat;
	private UserStatus status;
	private final ButtonGroup btnGrpOnlineStatus = new ButtonGroup();
	private JTextArea txtrMessage;
	private JLabel lblMessagestatus;
	private JTabbedPane tabConversations;
	private ClientOptions optionsWindow = new ClientOptions();

	class UserStatusActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() instanceof AbstractButton){
				AbstractButton b = (AbstractButton) e.getSource();
				setUserStatus(UserStatus.valueOf(b.getName()));
			}
		}
		
	}
	
	public Client(UserStatus s) {
		UserStatusActionListener statusListener = new UserStatusActionListener();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 827, 571);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenu mnOnlineStatus = new JMenu("Online Status");
		mnFile.add(mnOnlineStatus);
		
		JRadioButtonMenuItem rdbtnmntmOnline = new JRadioButtonMenuItem("Online");
		rdbtnmntmOnline.setName(UserStatus.ONLINE.name());
		rdbtnmntmOnline.addActionListener(statusListener);
		btnGrpOnlineStatus.add(rdbtnmntmOnline);
		mnOnlineStatus.add(rdbtnmntmOnline);
		
		JRadioButtonMenuItem rdbtnmntmAway = new JRadioButtonMenuItem("Away");
		rdbtnmntmAway.setName(UserStatus.AWAY.name());
		rdbtnmntmAway.addActionListener(statusListener);
		btnGrpOnlineStatus.add(rdbtnmntmAway);
		mnOnlineStatus.add(rdbtnmntmAway);
		
		JRadioButtonMenuItem rdbtnmntmDnd = new JRadioButtonMenuItem("DND");
		rdbtnmntmDnd.setName(UserStatus.DND.name());
		rdbtnmntmDnd.addActionListener(statusListener);
		btnGrpOnlineStatus.add(rdbtnmntmDnd);
		mnOnlineStatus.add(rdbtnmntmDnd);
		
		JRadioButtonMenuItem rdbtnmntmInvisible = new JRadioButtonMenuItem("Invisible");
		rdbtnmntmInvisible.setName(UserStatus.INVISIBLE.name());
		rdbtnmntmInvisible.addActionListener(statusListener);
		btnGrpOnlineStatus.add(rdbtnmntmInvisible);
		mnOnlineStatus.add(rdbtnmntmInvisible);
		
		JSeparator separator = new JSeparator();
		mnFile.add(separator);
		
		JMenuItem mntmChangePassword = new JMenuItem("Change Password");
		mnFile.add(mntmChangePassword);
		
		JMenuItem mntmSignOut = new JMenuItem("Sign Out");
		mnFile.add(mntmSignOut);
		
		JMenuItem mntmClose = new JMenuItem("Close");
		mnFile.add(mntmClose);
		
		JMenu mnContacts = new JMenu("Contacts");
		menuBar.add(mnContacts);
		
		JMenuItem mntmAddContact = new JMenuItem("Add Contact");
		mnContacts.add(mntmAddContact);
		
		JMenuItem mntmRemoveContact = new JMenuItem("Remove Contact");
		mnContacts.add(mntmRemoveContact);
		
		JSeparator separator_1 = new JSeparator();
		mnContacts.add(separator_1);
		
		JMenuItem mntmImportContacts = new JMenuItem("Import Contacts");
		mnContacts.add(mntmImportContacts);
		
		JMenuItem mntmExportContacts = new JMenuItem("Export Contacts");
		mnContacts.add(mntmExportContacts);
		
		JMenu mnConversation = new JMenu("Conversation");
		menuBar.add(mnConversation);
		
		JMenu mnSend = new JMenu("Send");
		mnConversation.add(mnSend);
		
		JMenuItem mntmFile = new JMenuItem("File");
		mnSend.add(mntmFile);
		
		JMenuItem mntmContacts = new JMenuItem("Contacts");
		mnSend.add(mntmContacts);
		
		JMenuItem mntmAddPeople = new JMenuItem("Add People");
		mnConversation.add(mntmAddPeople);
		
		JMenuItem mntmLeaveConversation = new JMenuItem("Leave Conversation");
		mnConversation.add(mntmLeaveConversation);
		
		JMenu mnTools = new JMenu("Tools");
		menuBar.add(mnTools);
		
		JMenuItem mntmOptions = new JMenuItem("Options");
		mntmOptions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				optionsWindow.setVisible(true);
			}
		});
		mnTools.add(mntmOptions);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JSplitPane splitMain = new JSplitPane();
		splitMain.setResizeWeight(0.2);
		contentPane.add(splitMain, BorderLayout.CENTER);
		
		JPanel pnlContacts = new JPanel();
		pnlContacts.setBackground(Color.WHITE);
		splitMain.setLeftComponent(pnlContacts);
		
		JPanel pnlMain = new JPanel();
		splitMain.setRightComponent(pnlMain);
		pnlMain.setLayout(new BorderLayout(0, 0));
		
		JSplitPane splitConversation = new JSplitPane();
		splitConversation.setMinimumSize(new Dimension(400, 400));
		splitConversation.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				//pnlConversation.revalidate();
				//pnlConversationBase.setPreferredSize(new Dimension(scrlChat.getWidth(),pnlConversation.getHeight()));
				//pnlConversationBase.revalidate();
			}
		});
		splitConversation.setResizeWeight(0.8);
		splitConversation.setOrientation(JSplitPane.VERTICAL_SPLIT);
		pnlMain.add(splitConversation, BorderLayout.CENTER);
		
		JPanel pnlMessage = new JPanel();
		pnlMessage.setBackground(Color.WHITE);
		splitConversation.setRightComponent(pnlMessage);
		pnlMessage.setLayout(new BorderLayout(0, 0));
		
		JPanel pnlMsgOptions = new JPanel();
		pnlMessage.add(pnlMsgOptions, BorderLayout.SOUTH);
		pnlMsgOptions.setLayout(new BorderLayout(0, 0));
		
		JPanel pnlMsgBtns = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) pnlMsgBtns.getLayout();
		flowLayout_1.setAlignment(FlowLayout.RIGHT);
		flowLayout_1.setHgap(1);
		flowLayout_1.setVgap(0);
		pnlMsgOptions.add(pnlMsgBtns, BorderLayout.EAST);
		
		JButton btnEmoticons = new JButton("=)");
		btnEmoticons.setVerticalAlignment(SwingConstants.TOP);
		pnlMsgBtns.add(btnEmoticons);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txtrMessage.setText("");
			}
		});
		btnClear.setVerticalAlignment(SwingConstants.TOP);
		pnlMsgBtns.add(btnClear);
		
		JButton btnSend = new JButton("Send");
		pnlMsgBtns.add(btnSend);
		
		lblMessagestatus = new JLabel("");
		pnlMsgOptions.add(lblMessagestatus, BorderLayout.WEST);
		
		JScrollPane scrlMessage = new JScrollPane();
		scrlMessage.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		pnlMessage.add(scrlMessage, BorderLayout.CENTER);
		
		txtrMessage = new JTextArea();
		txtrMessage.setLineWrap(true);
		txtrMessage.setWrapStyleWord(true);
		scrlMessage.setViewportView(txtrMessage);
				
		tabConversations = new JTabbedPane(JTabbedPane.TOP);
		splitConversation.setLeftComponent(tabConversations);
		
		for (int i = 0; i < 4; i++){
			Conversation c = new Conversation();
			c.getMessages().add(new Message("User "+i,"Some Stupid MESSAGE!!"));
			
			ConversationPanel cp = new ConversationPanel(c);
			
			tabConversations.addTab("Convo " + (i+1), null, cp, null);
			cp.revalidate();
		}
		
		/*scrlChat = new JScrollPane();
		tabConversations.addTab("New tab", null, scrlChat, null);
		
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
		});*/
		
		JPanel pnlStatusBar = new JPanel();
		FlowLayout flowLayout = (FlowLayout) pnlStatusBar.getLayout();
		flowLayout.setVgap(0);
		flowLayout.setAlignment(FlowLayout.LEFT);
		pnlStatusBar.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		contentPane.add(pnlStatusBar, BorderLayout.SOUTH);
		
		JLabel lblStatus = new JLabel("");
		pnlStatusBar.add(lblStatus);
		
		Component verticalStrut = Box.createVerticalStrut(15);
		pnlStatusBar.add(verticalStrut);
		
		setUserStatus(s);
		
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
	
	private void setUserStatus(UserStatus s){
		this.status=s;
		for (Enumeration<AbstractButton> e = btnGrpOnlineStatus.getElements(); e.hasMoreElements();){
			AbstractButton b = (AbstractButton) e.nextElement();
			if (b.getName().equalsIgnoreCase(s.name())){
				btnGrpOnlineStatus.setSelected(b.getModel(), true);
				break;
			}
		}
	}
	
}
