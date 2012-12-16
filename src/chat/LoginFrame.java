package chat;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import java.awt.GridBagLayout;
import java.awt.Component;
import javax.swing.Box;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

import chat.client.Client;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;

public class LoginFrame extends JFrame {

	private JPanel contentPane;
	private JTextField txtUsername;
	private JPasswordField pwdPassword;
	private final LoginFrame loginFrame = this;
	private JComboBox<UserStatus> cbStatus;
	private JLabel lblErrormessage;
	private HashMap<String,Integer> departments;

	/**
	 * Launch the application.
	 */
	public static void main(final String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					if (args.length>0){
						if (args[0].equalsIgnoreCase("-s")){
							System.out.println("Starting Server");
						}else System.out.println("Incorrect Parameters");
					}else{
						LoginFrame frame = new LoginFrame();
						frame.setVisible(true);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginFrame() {
		setTitle("Chat login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 517, 326);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel pnlBase = new JPanel();
		contentPane.add(pnlBase, BorderLayout.CENTER);
		GridBagLayout gbl_pnlBase = new GridBagLayout();
		gbl_pnlBase.columnWidths = new int[]{0, 0, 0, 0};
		gbl_pnlBase.rowHeights = new int[]{0, 0, 0, 0};
		gbl_pnlBase.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_pnlBase.rowWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		pnlBase.setLayout(gbl_pnlBase);
		
		Component rigidArea = Box.createRigidArea(new Dimension(20, 20));
		GridBagConstraints gbc_rigidArea = new GridBagConstraints();
		gbc_rigidArea.insets = new Insets(0, 0, 5, 5);
		gbc_rigidArea.gridx = 0;
		gbc_rigidArea.gridy = 0;
		pnlBase.add(rigidArea, gbc_rigidArea);
		
		JPanel pnlLogin = new JPanel();
		pnlLogin.setBorder(new TitledBorder(null, "Login", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_pnlLogin = new GridBagConstraints();
		gbc_pnlLogin.insets = new Insets(0, 0, 5, 5);
		gbc_pnlLogin.fill = GridBagConstraints.BOTH;
		gbc_pnlLogin.gridx = 1;
		gbc_pnlLogin.gridy = 1;
		pnlBase.add(pnlLogin, gbc_pnlLogin);
		GridBagLayout gbl_pnlLogin = new GridBagLayout();
		gbl_pnlLogin.columnWidths = new int[]{0, 0, 0};
		gbl_pnlLogin.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_pnlLogin.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_pnlLogin.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		pnlLogin.setLayout(gbl_pnlLogin);
		
		JLabel lblServerHostAddress = new JLabel("Server Address:");
		GridBagConstraints gbc_lblServerHostAddress = new GridBagConstraints();
		gbc_lblServerHostAddress.insets = new Insets(0, 0, 5, 5);
		gbc_lblServerHostAddress.anchor = GridBagConstraints.EAST;
		gbc_lblServerHostAddress.gridx = 0;
		gbc_lblServerHostAddress.gridy = 0;
		pnlLogin.add(lblServerHostAddress, gbc_lblServerHostAddress);
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"skorcraft.net"}));
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 5, 0);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 1;
		gbc_comboBox.gridy = 0;
		pnlLogin.add(comboBox, gbc_comboBox);
		
		Component verticalStrut = Box.createVerticalStrut(5);
		GridBagConstraints gbc_verticalStrut = new GridBagConstraints();
		gbc_verticalStrut.insets = new Insets(0, 0, 5, 0);
		gbc_verticalStrut.gridx = 1;
		gbc_verticalStrut.gridy = 1;
		pnlLogin.add(verticalStrut, gbc_verticalStrut);
		
		JLabel lblUsername = new JLabel("UserName:");
		GridBagConstraints gbc_lblUsername = new GridBagConstraints();
		gbc_lblUsername.anchor = GridBagConstraints.EAST;
		gbc_lblUsername.insets = new Insets(0, 0, 5, 5);
		gbc_lblUsername.gridx = 0;
		gbc_lblUsername.gridy = 2;
		pnlLogin.add(lblUsername, gbc_lblUsername);
		
		txtUsername = new JTextField();
		txtUsername.setText("UserName");
		GridBagConstraints gbc_txtUsername = new GridBagConstraints();
		gbc_txtUsername.insets = new Insets(0, 0, 5, 0);
		gbc_txtUsername.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtUsername.gridx = 1;
		gbc_txtUsername.gridy = 2;
		pnlLogin.add(txtUsername, gbc_txtUsername);
		txtUsername.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password:");
		GridBagConstraints gbc_lblPassword = new GridBagConstraints();
		gbc_lblPassword.anchor = GridBagConstraints.EAST;
		gbc_lblPassword.insets = new Insets(0, 0, 5, 5);
		gbc_lblPassword.gridx = 0;
		gbc_lblPassword.gridy = 3;
		pnlLogin.add(lblPassword, gbc_lblPassword);
		
		pwdPassword = new JPasswordField();
		pwdPassword.setText("Password");
		GridBagConstraints gbc_pwdPassword = new GridBagConstraints();
		gbc_pwdPassword.insets = new Insets(0, 0, 5, 0);
		gbc_pwdPassword.fill = GridBagConstraints.HORIZONTAL;
		gbc_pwdPassword.gridx = 1;
		gbc_pwdPassword.gridy = 3;
		pnlLogin.add(pwdPassword, gbc_pwdPassword);
		
		JLabel lblSignInAs = new JLabel("Sign in as:");
		GridBagConstraints gbc_lblSignInAs = new GridBagConstraints();
		gbc_lblSignInAs.anchor = GridBagConstraints.EAST;
		gbc_lblSignInAs.insets = new Insets(0, 0, 5, 5);
		gbc_lblSignInAs.gridx = 0;
		gbc_lblSignInAs.gridy = 4;
		pnlLogin.add(lblSignInAs, gbc_lblSignInAs);
		
		cbStatus = new JComboBox<UserStatus>();
		cbStatus.setModel(new DefaultComboBoxModel<UserStatus>(UserStatus.values()));
		GridBagConstraints gbc_cbStatus = new GridBagConstraints();
		gbc_cbStatus.insets = new Insets(0, 0, 5, 0);
		gbc_cbStatus.fill = GridBagConstraints.HORIZONTAL;
		gbc_cbStatus.gridx = 1;
		gbc_cbStatus.gridy = 4;
		pnlLogin.add(cbStatus, gbc_cbStatus);
		
		JPanel panel_2 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_2.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.insets = new Insets(0, 0, 5, 0);
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridx = 1;
		gbc_panel_2.gridy = 5;
		pnlLogin.add(panel_2, gbc_panel_2);
		
		Component verticalStrut_1 = Box.createVerticalStrut(20);
		panel_2.add(verticalStrut_1);
		
		lblErrormessage = new JLabel("");
		panel_2.add(lblErrormessage);
		lblErrormessage.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblErrormessage.setForeground(Color.RED);
		lblErrormessage.setHorizontalAlignment(SwingConstants.LEFT);
		
		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setVgap(0);
		flowLayout.setAlignment(FlowLayout.RIGHT);
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 6;
		pnlLogin.add(panel, gbc_panel);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		panel.add(btnCancel);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (validateLogin(txtUsername.getText(),concat(pwdPassword.getPassword()))){
					UserStatus status = (UserStatus) cbStatus.getSelectedItem();
					new Client(status).setVisible(true);
					loginFrame.setVisible(false);
				}else{
					errorMessage(LoginError.INVALID_CREDENTIALS);
				}
			}
		});
		panel.add(btnLogin);
		
		JPanel panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 1;
		gbc_panel_1.gridy = 7;
		pnlLogin.add(panel_1, gbc_panel_1);
		
		JLabel lblDontHaveAn = new JLabel("Don't have an account? Click HERE to register!");
		lblDontHaveAn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new RegisterFrame(txtUsername.getText(),loginFrame).setVisible(true);
				loginFrame.setVisible(false);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				JLabel l = (JLabel) e.getComponent();
				l.setForeground(Color.BLUE);
				l.setFont(new Font(l.getFont().getFamily(),Font.ITALIC,l.getFont().getSize()));
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				JLabel l = (JLabel) e.getComponent();
				l.setForeground(Color.BLACK);
				l.setFont(new Font(l.getFont().getFamily(),Font.PLAIN,l.getFont().getSize()));
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				JLabel l = (JLabel) e.getComponent();
				l.setForeground(Color.red);
			}
			
			@Override
			public void mouseReleased(MouseEvent e) {
				JLabel l = (JLabel) e.getComponent();
				l.setForeground(Color.BLUE);
			}
		});
		panel_1.add(lblDontHaveAn);
		
		Component rigidArea_2 = Box.createRigidArea(new Dimension(20, 20));
		GridBagConstraints gbc_rigidArea_2 = new GridBagConstraints();
		gbc_rigidArea_2.gridx = 2;
		gbc_rigidArea_2.gridy = 2;
		pnlBase.add(rigidArea_2, gbc_rigidArea_2);
	}

	private boolean validateLogin(String user, String pass){
		// TODO: Validate credentials
		return false;
	}
	
	private void errorMessage(LoginError error){
		lblErrormessage.setText(error.getMessage());
		pwdPassword.setText("");
	}
	
	public String concat(char... chars) {
		if (chars.length == 0) {
			return "";
		}
		StringBuilder s = new StringBuilder(chars.length);
		for (char c : chars) {
			s.append(c);
		}
		return s.toString();
	}
	
	private void populateDepartments(){
		// TEMPORARY
		// TODO: Retrieve departments from server
		this.departments = new HashMap<String,Integer>();
		String d[] = {"Human Resources","Retail","Advertising","Public Relations","IT"};
		
		for (int i = 0; i < d.length; i++){
			departments.put(d[i], i+1);
		}
	}
	
	protected enum LoginError{
		UNABLE_TO_CONNECT("Unable to connect to server!"),
		INVALID_CREDENTIALS("Invalid username/password!");
		
		private String message;
		
		LoginError(String msg){
			this.message = msg;
		}
		
		public String getMessage(){
			return this.message;
		}
	}
	
}
