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
	public static JLabel error;

	// TEXTFIELD
	public JTextField nameField;
	public JTextField tf_ip;
	public JTextField tf_name;
	public JTextField tf_port;

	// BUTTON
	private JButton login;

	// STRING
	private static String[] colors = { "Default", "Tibia", "Matrix" };

	// FONT & BORDER
	Font f1 = new Font(Font.DIALOG, Font.PLAIN, 9);
	Font f2 = new Font(Font.DIALOG, Font.PLAIN, 10);
	Font f3 = new Font(Font.DIALOG, Font.ITALIC, 12);
	Font f4 = new Font(Font.DIALOG, Font.BOLD, 23);

	// INT
	public static int colorIndex = 0;

	// COMBOBOX
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private JComboBox colorSelect = new JComboBox(colors);
	

	public LoginGUI() {

		windowInit();
		interfaceInit();
		setVisible(true);
	}

	public void logInClicked() {

		Client.connected = true;
		String ip = tf_ip.getText();
		int port = Integer.parseInt(tf_port.getText());
		User.name = nameField.getText();
		colorIndex = storeColor();
		JOptionPane.showMessageDialog(null, "Welcome " + User.name, "Logged In!", JOptionPane.INFORMATION_MESSAGE);
		Client.logIn(ip, port, User.name);
		dispose();
		new Client();

	}

	public int storeColor() {
		return colorSelect.getSelectedIndex();
	}

	private void windowInit() {

		// Title window configurations
		setTitle("Jada");
		setSize(300, 300);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);

	}

	private void interfaceInit() {

		// MAIN TITLE
		maintitle = new JLabel("Welcome to Jada!");
		maintitle.setFont(f4);
		maintitle.setBounds(40, 70, 200, 20);
		add(maintitle);

		// UNDERTITLE
		undertitle = new JLabel("Messanger application in DAT055 ");
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
		tf_ip.addActionListener(e->logInClicked());
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
		tf_port.addActionListener(e->logInClicked());
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
		error.setBounds(90,110,200,20);
		error.setForeground(Color.red);
		add(error);
		error.setVisible(false);
		

		// DECORATIONS
		getContentPane().add(new DrawingComponent());
	}

}