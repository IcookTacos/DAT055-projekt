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
	

	// TEXTFIELD
	public JTextField nameField;
	public JTextField tf_ip;
	public JTextField tf_name;
	public JTextField tf_port;

	// BUTTON
	private JButton login;

	// STRING
	private String[] colors = {"Red","Blue","Magenta","Yellow"};
	public static String user = null;
	@SuppressWarnings({ "unchecked", "rawtypes" })

	// COMBOBOX
	private JComboBox colorSelect = new JComboBox(colors);

	// FONT & BORDER
	Font f1 = new Font(Font.DIALOG,Font.PLAIN,9);

	public LoginGUI() {

		// Title window configurations
		setTitle("Jada");
		setSize(300,300);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);

		// Main title (Welcome)
		maintitle = new JLabel("Welcome to Jada!");
		maintitle.setFont(new Font(Font.DIALOG,Font.BOLD,23));
		maintitle.setBounds(40,70,200,20);
		add(maintitle);

		// Under title 
		undertitle = new JLabel("Messanger application in DAT055 ");
		undertitle.setFont(new Font(Font.DIALOG,Font.ITALIC,12));
		undertitle.setBounds(40,88,300,20);
		add(undertitle);

		// USERNAME TEXTFIELD
		nameField = new JTextField("");
		nameField.setBounds(90,150,90,20);
		nameField.addActionListener(e -> logInClicked());
		add(nameField);

		// USERNAME LABEL
		enterName = new JLabel("USERNAME:");
		enterName.setFont(new Font(Font.DIALOG,Font.PLAIN,10));
		enterName.setBounds(18,150,120,20);
		add(enterName);

		// IP LABEL
		lblIp = new JLabel("IP:");
		lblIp.setBounds(65,168,120,20);
		lblIp.setFont(new Font(Font.DIALOG,Font.PLAIN,10));
		add(lblIp);

		// IP TEXTFIELD
		tf_ip = new JTextField("localhost");
		tf_ip.setBounds(90,170,90,20);
		tf_ip.setFont(new Font(Font.DIALOG,Font.PLAIN,10));
		add(tf_ip);

		// PORT LABEL
		lblPort = new JLabel("PORT:");
		lblPort.setBounds(48,190,120,20);
		lblPort.setFont(new Font(Font.DIALOG,Font.PLAIN,10));
		add(lblPort);

		// PORT TEXTFIELD
		tf_port = new JTextField("2309");
		tf_port.setBounds(90,190,90,20);
		tf_port.setFont(new Font(Font.DIALOG,Font.PLAIN,10));
		add(tf_port);

		// LOGIN BUTTON
		login = new JButton("login");
		login.setBounds(90,210,90,20);
		login.setFont(f1);
		login.setContentAreaFilled(false);
		login.addActionListener(e -> logInClicked());
		add(login);	

		// Combobox
		colorSelect.setSelectedIndex(1);
		colorSelect.setBounds(185,230,90,20);
		colorSelect.setSelectedIndex(0);
		colorSelect.setBackground(Color.LIGHT_GRAY);
		//add(colorSelect);

		// Decorations
		getContentPane().add(new DrawingComponent());

		// Set visible
		setVisible(true);


	}

	public void logInClicked() {

		Client.connected = true;
		String ip = tf_ip.getText();
		int port = Integer.parseInt(tf_port.getText());
		user = nameField.getText();
		JOptionPane.showMessageDialog(null,"Welcome " + user,"Logged In!",JOptionPane.INFORMATION_MESSAGE);
		Client.logIn(ip, port, user);
		dispose();
		new Client();

		//ta_chat.append("Connected!");
		//ta_chat.append("s=" + "\n");  // Skrivs till chatten [Socket-Information]

	}



}