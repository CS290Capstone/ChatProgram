package chat.client;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JSplitPane;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JMenu;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JMenuItem;

import chat.UserStatus;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.ButtonGroup;

public class Client extends JFrame {

	private JPanel contentPane;
	private UserStatus status = UserStatus.ONLINE;
	private final ButtonGroup btnGrpOnlineStatus = new ButtonGroup();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Client frame = new Client();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Client() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 827, 571);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenu mnOnlineStatus = new JMenu("Online Status");
		mnFile.add(mnOnlineStatus);
		
		JRadioButtonMenuItem rdbtnmntmOnline = new JRadioButtonMenuItem("Online");
		rdbtnmntmOnline.setSelected(true);
		btnGrpOnlineStatus.add(rdbtnmntmOnline);
		mnOnlineStatus.add(rdbtnmntmOnline);
		
		JRadioButtonMenuItem rdbtnmntmAway = new JRadioButtonMenuItem("Away");
		btnGrpOnlineStatus.add(rdbtnmntmAway);
		mnOnlineStatus.add(rdbtnmntmAway);
		
		JRadioButtonMenuItem rdbtnmntmDnd = new JRadioButtonMenuItem("DND");
		btnGrpOnlineStatus.add(rdbtnmntmDnd);
		mnOnlineStatus.add(rdbtnmntmDnd);
		
		JRadioButtonMenuItem rdbtnmntmInvisible = new JRadioButtonMenuItem("Invisible");
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
		mnTools.add(mntmOptions);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setResizeWeight(0.2);
		contentPane.add(splitPane, BorderLayout.CENTER);
		
		JPanel pnlContacts = new JPanel();
		splitPane.setLeftComponent(pnlContacts);
		
		JPanel pnlMain = new JPanel();
		splitPane.setRightComponent(pnlMain);
		pnlMain.setLayout(new BorderLayout(0, 0));
		
		JSplitPane splitPane_1 = new JSplitPane();
		splitPane_1.setResizeWeight(0.8);
		splitPane_1.setOrientation(JSplitPane.VERTICAL_SPLIT);
		pnlMain.add(splitPane_1, BorderLayout.CENTER);
		
		JPanel pnlMessage = new JPanel();
		splitPane_1.setRightComponent(pnlMessage);
		
		JScrollPane scpnlChat = new JScrollPane();
		splitPane_1.setLeftComponent(scpnlChat);
	}

}
