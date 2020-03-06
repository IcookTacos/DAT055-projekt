package clientPackage;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

@SuppressWarnings("serial")

public class LoginGUI extends JFrame {

	// LABEL
	private JLabel maintitle;
	private JLabel undertitle;
	private JLabel enterName;
	private JLabel lblIp;
	private JLabel lblPort;
	private static JLabel error;

	// TEXTFIELD
	private JTextField nameField;
	private JTextField tf_ip;
	private JTextField tf_port;

	// BUTTON
	private JButton login;

	// STRING
	private static String[] colors = { "Default", "Tibia", "Matrix" };

	// FONT & BORDER
	Font f1 = new Font(Font.DIALOG, Font.PLAIN, 9);
	Font f2 = new Font(Font.DIALOG, Font.PLAIN, 10);
	Font f3 = new Font(Font.DIALOG, Font.ITALIC, 12);
	Font f4 = new Font(Font.DIALOG, Font.BOLD, 23);

	// COMBOBOX
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private JComboBox colorSelect = new JComboBox(colors);

	public LoginGUI(boolean flag) {

//		if true, error message is shown in login window
		
		error.setVisible(flag);
		
		windowInit();
		interfaceInit();
		setVisible(true);
	}

	/*
	 * method for establishing connection
	 */
	private void logInClicked() {

		String user = nameField.getText();
//		Checks if the username is within parameters
		boolean validName = authenticate(user);

//		If username is valid, proceeds to attempt to connect
		if (validName) {
			String ip = tf_ip.getText();
			int port = Integer.parseInt(tf_port.getText());
			int colorIndex = storeColor();
			JOptionPane.showMessageDialog(null, "Welcome " + user, "Logged In!", JOptionPane.INFORMATION_MESSAGE);
			new Client(ip, port, user, colorIndex);
			dispose();

		}

		nameField.setText(null);
	}

	/*
	 * returns index number for what theme has been selected
	 */
	private int storeColor() {
		return colorSelect.getSelectedIndex();
	}

	/*
	 * modifies the extended JFrame
	 */
	private void windowInit() {

		// Title window configurations
		setTitle("Jada");
		setSize(300, 300);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);

	}

	/**
	 * Adds functionality to extended JFrame
	 */
	private void interfaceInit() {

		// MAIN TITLE
		maintitle = new JLabel("Welcome to Jada!");
		maintitle.setFont(f4);
		maintitle.setBounds(40, 70, 200, 20);
		add(maintitle);

		// UNDERTITLE
		undertitle = new JLabel("Messenger application in DAT055 ");
		undertitle.setFont(f3);
		undertitle.setBounds(40, 88, 300, 20);
		add(undertitle);

		// USERNAME TEXTFIELD
		nameField = new JTextField("");
		nameField.setBounds(90, 150, 90, 20);
		nameField.addActionListener(e -> logInClicked());
		add(nameField);

		// USERNAME LABEL
		enterName = new JLabel("USERNAME:");
		enterName.setFont(f2);
		enterName.setBounds(18, 150, 120, 20);
		add(enterName);

		// IP LABEL
		lblIp = new JLabel("IP:");
		lblIp.setBounds(65, 168, 120, 20);
		lblIp.setFont(f2);
		add(lblIp);

		// IP TEXTFIELD 
		tf_ip = new JTextField("64.227.46.227");
		tf_ip.setBounds(90, 170, 90, 20);
		tf_ip.setFont(f2);		
		tf_ip.addActionListener(e -> logInClicked());
		//tf_ip.setEditable(false);
		add(tf_ip);

		// PORT LABEL
		lblPort = new JLabel("PORT:");
		lblPort.setBounds(48, 190, 120, 20);
		lblPort.setFont(f2);
		add(lblPort);

		// PORT TEXTFIELD 
		tf_port = new JTextField("2309");
		tf_port.setBounds(90, 190, 90, 20);
		tf_port.setFont(f2);
		tf_port.addActionListener(e -> logInClicked());		
		//tf_port.setEditable(false);
		add(tf_port);

		// LOGIN BUTTON
		login = new JButton("login");
		login.setBounds(90, 210, 90, 20);
		login.setFont(f1);
		login.setContentAreaFilled(false);
		login.addActionListener(e -> logInClicked());
		add(login);

		// COMBOBOX
		colorSelect.setSelectedIndex(0);
		colorSelect.setBounds(90, 230, 90, 20);
		colorSelect.setBackground(Color.LIGHT_GRAY);
		add(colorSelect);

		// ERROR LABEL
		error = new JLabel("Invalid Server");
		error.setBounds(90, 110, 200, 20);
		error.setForeground(Color.red);
		add(error);
		error.setVisible(false);

		// DECORATIONS
		getContentPane().add(new DrawingComponent());
	}

	/**
	 * checks for invalid username
	 * 
	 * @param string
	 * @return
	 */
	private boolean authenticate(String string) {
		// CONTROLLS USER IS NOT NAMED ADMIN
		String check = string;
		System.out.println(check);
		boolean validName = true;
		if (check.equalsIgnoreCase("ADMIN")) {
			validName = false;
			JOptionPane.showMessageDialog(null, "Cannot be named " + string, "Bad username", JOptionPane.ERROR_MESSAGE);
		}

		// CONTROLLS USENAME IS NOT EMPTY
		if (check.isEmpty()) {
			validName = false;
			JOptionPane.showMessageDialog(null, "Username cannot be empty", "Bad username", JOptionPane.ERROR_MESSAGE);
		}

		// CONTROLLS WHITESPACE
		int length = check.length();
		char[] x = check.toCharArray();
		for (int i = 0; i < length; i++) {
			if (x[i] == ' ') {
				validName = false;

				JOptionPane.showMessageDialog(null, "Username cannot contain spaces", "Bad username",
						JOptionPane.ERROR_MESSAGE);

				break;
			}
		}

		return validName;
	}

}