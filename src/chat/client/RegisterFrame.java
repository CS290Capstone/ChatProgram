package chat.client;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import java.awt.Component;
import javax.swing.Box;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

import chat.LoginFrame;
import chat.UserCredentials;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class RegisterFrame extends JFrame {

	private JPanel contentPane;
	private final LoginFrame login;
	private JTextField txtUsername;
	private JPasswordField pwdPassword;
	private JPasswordField pwdConfirmpassword;
	private JTextField txtName;
	private JLabel lblRegisterError;
	private RegisterFrame frame;


	/**
	 * @wbp.parser.constructor
	 */
	public RegisterFrame(String user, LoginFrame login){
		this(login);
		txtUsername.setText(user);
	}
	
	private boolean isMatchingPasswords(JPasswordField pw1, JPasswordField pw2){
		return pw1.getPassword() == pw2.getPassword();
	}
	

	public RegisterFrame(final LoginFrame login) {
		this.login = login;
		setResizable(false);
		setTitle("User Registration");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		Component rigidArea = Box.createRigidArea(new Dimension(40, 40));
		GridBagConstraints gbc_rigidArea = new GridBagConstraints();
		gbc_rigidArea.insets = new Insets(0, 0, 5, 5);
		gbc_rigidArea.gridx = 0;
		gbc_rigidArea.gridy = 0;
		panel.add(rigidArea, gbc_rigidArea);
		
		JLabel lblUsername = new JLabel("Username:");
		GridBagConstraints gbc_lblUsername = new GridBagConstraints();
		gbc_lblUsername.anchor = GridBagConstraints.EAST;
		gbc_lblUsername.insets = new Insets(0, 0, 5, 5);
		gbc_lblUsername.gridx = 1;
		gbc_lblUsername.gridy = 1;
		panel.add(lblUsername, gbc_lblUsername);
		
		txtUsername = new JTextField();
		txtUsername.setText("Username");
		GridBagConstraints gbc_txtUsername = new GridBagConstraints();
		gbc_txtUsername.insets = new Insets(0, 0, 5, 5);
		gbc_txtUsername.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtUsername.gridx = 2;
		gbc_txtUsername.gridy = 1;
		panel.add(txtUsername, gbc_txtUsername);
		txtUsername.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password:");
		GridBagConstraints gbc_lblPassword = new GridBagConstraints();
		gbc_lblPassword.anchor = GridBagConstraints.EAST;
		gbc_lblPassword.insets = new Insets(0, 0, 5, 5);
		gbc_lblPassword.gridx = 1;
		gbc_lblPassword.gridy = 2;
		panel.add(lblPassword, gbc_lblPassword);
		
		pwdPassword = new JPasswordField();
		GridBagConstraints gbc_pwdPassword = new GridBagConstraints();
		gbc_pwdPassword.insets = new Insets(0, 0, 5, 5);
		gbc_pwdPassword.fill = GridBagConstraints.HORIZONTAL;
		gbc_pwdPassword.gridx = 2;
		gbc_pwdPassword.gridy = 2;
		panel.add(pwdPassword, gbc_pwdPassword);
		
		pwdConfirmpassword = new JPasswordField();
		GridBagConstraints gbc_pwdConfirmpassword = new GridBagConstraints();
		gbc_pwdConfirmpassword.insets = new Insets(0, 0, 5, 5);
		gbc_pwdConfirmpassword.fill = GridBagConstraints.HORIZONTAL;
		gbc_pwdConfirmpassword.gridx = 2;
		gbc_pwdConfirmpassword.gridy = 3;
		panel.add(pwdConfirmpassword, gbc_pwdConfirmpassword);
		
		JLabel lblName = new JLabel("Name:");
		GridBagConstraints gbc_lblName = new GridBagConstraints();
		gbc_lblName.anchor = GridBagConstraints.EAST;
		gbc_lblName.insets = new Insets(0, 0, 5, 5);
		gbc_lblName.gridx = 1;
		gbc_lblName.gridy = 4;
		panel.add(lblName, gbc_lblName);
		
		txtName = new JTextField();
		txtName.setText("Name");
		GridBagConstraints gbc_txtName = new GridBagConstraints();
		gbc_txtName.insets = new Insets(0, 0, 5, 5);
		gbc_txtName.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtName.gridx = 2;
		gbc_txtName.gridy = 4;
		panel.add(txtName, gbc_txtName);
		txtName.setColumns(10);
		
		JLabel lblDepartment = new JLabel("Department");
		GridBagConstraints gbc_lblDepartment = new GridBagConstraints();
		gbc_lblDepartment.insets = new Insets(0, 0, 5, 5);
		gbc_lblDepartment.anchor = GridBagConstraints.EAST;
		gbc_lblDepartment.gridx = 1;
		gbc_lblDepartment.gridy = 5;
		panel.add(lblDepartment, gbc_lblDepartment);
		
		JComboBox<String> cboDepartment = new JComboBox<String>();
		cboDepartment.setEnabled(false);
		cboDepartment.setModel(new DefaultComboBoxModel<String>(new String[] {"Retail", "Human Resources", "IT"}));
		GridBagConstraints gbc_cboDepartment = new GridBagConstraints();
		gbc_cboDepartment.insets = new Insets(0, 0, 5, 5);
		gbc_cboDepartment.fill = GridBagConstraints.HORIZONTAL;
		gbc_cboDepartment.gridx = 2;
		gbc_cboDepartment.gridy = 5;
		panel.add(cboDepartment, gbc_cboDepartment);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				int status = Client.getClient().registerUser(new UserCredentials(txtUsername.getText(),login.concat(pwdPassword.getPassword()), txtName.getText(), 1));
				
				switch (status){
					case -1:
						lblRegisterError.setText("There was an error.  Please contact administrator for help.");
					case 0:
						lblRegisterError.setText("User already exists.");
						break;
					case 1:
						JOptionPane.showMessageDialog(null,"Welcome.  You can now log in.");
						frame.setVisible(false);
						break;
				}
			}
		});
		
		GridBagConstraints gbc_btnSubmit = new GridBagConstraints();
		gbc_btnSubmit.insets = new Insets(0, 0, 5, 5);
		gbc_btnSubmit.gridx = 2;
		gbc_btnSubmit.gridy = 6;
		panel.add(btnSubmit, gbc_btnSubmit);
		
		Component rigidArea_1 = Box.createRigidArea(new Dimension(40, 40));
		GridBagConstraints gbc_rigidArea_1 = new GridBagConstraints();
		gbc_rigidArea_1.insets = new Insets(0, 0, 5, 0);
		gbc_rigidArea_1.gridx = 3;
		gbc_rigidArea_1.gridy = 6;
		panel.add(rigidArea_1, gbc_rigidArea_1);
		
		lblRegisterError = new JLabel("");
		GridBagConstraints gbc_lblRegisterError = new GridBagConstraints();
		gbc_lblRegisterError.gridwidth = 2;
		gbc_lblRegisterError.insets = new Insets(0, 0, 0, 5);
		gbc_lblRegisterError.gridx = 1;
		gbc_lblRegisterError.gridy = 7;
		panel.add(lblRegisterError, gbc_lblRegisterError);
		
		frame = this;
	}
	
	public String getStatus(){
		return lblRegisterError.getText();
	}
}
