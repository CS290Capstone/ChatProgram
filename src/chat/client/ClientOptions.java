package chat.client;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Dialog.ModalExclusionType;

@SuppressWarnings("serial")
public class ClientOptions extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public ClientOptions() {
		setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}

}
