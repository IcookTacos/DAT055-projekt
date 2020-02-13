package clientPackage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")

public class GUI extends JFrame{

	// PANEL
	public JPanel contentPane;

	// TEXTFIELD
	public JTextField tf_input;
	public JTextField tf_ip;
	public JTextField tf_port;
	public JTextField tf_name;

	// TEXTAREA
	public static JTextArea ta_chat;

	// LABEL
	public JLabel lblIp;
	public JLabel lblPort;
	public JLabel lblName;


	// BUTTON
	public JButton b_connect;
	public JButton btnDc;
	public JButton Refresh;
	public JButton b_send;
	// SCROLLPANE
	public JScrollPane scrollPane;

	// STRING
	public String userName;
	public String received = null;

	// SOCKET
	public Socket s = null;

	// INPUT & OUTPUT STREAM
	public DataOutputStream dos = null;
	public DataInputStream dis = null;

	// ETC
	public boolean connected = false;
	public static int autoScroll = 0;

	public GUI() {
		
		// FRAME CONFIGURATION
		setTitle("Client");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600,600);
		setLocationRelativeTo(null);
		setResizable(false);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		// TEXTFIELD INPUT
		tf_input = new JTextField();
		tf_input.setBounds(40,385,300,20);
		contentPane.add(tf_input);
		tf_input.setColumns(10);

		// SCROLLPANE
		scrollPane = new JScrollPane();
		scrollPane.setBounds(40,80,450,300);
		contentPane.add(scrollPane);

		// TEXTAREA CHAT
		ta_chat = new JTextArea();
		ta_chat.append("Welcome to Jada Jada\nPlease connect to server\n");
		scrollPane.setViewportView(ta_chat);
		ta_chat.setEditable(false);
		
		// LABEL PORT
		lblPort = new JLabel("Port");
		lblPort.setBounds(10,510,46,14);
		contentPane.add(lblPort);
		
		// TEXTFIELD PORT
		tf_port = new JTextField();
		tf_port.setText("2309");
		tf_port.setColumns(10);
		tf_port.setBounds(55,510,60,20);
		contentPane.add(tf_port);
		
		// LABEL IP
		lblIp = new JLabel("IP:");
		lblIp.setBounds(10,490,50,20);
		contentPane.add(lblIp);
		
		// TEXTFIELD IP
		tf_ip = new JTextField();
		tf_ip.setText("localhost");
		tf_ip.setBounds(55,490,60, 20);
		tf_ip.setColumns(10);
		contentPane.add(tf_ip);

		// LABEL NAME
		lblName = new JLabel("Name:");
		lblName.setBounds(10, 470, 46, 14);
		contentPane.add(lblName);

		// TEXTFIELD NAME
		tf_name = new JTextField();
		tf_name.setText("Axel");
		tf_name.setColumns(10);
		tf_name.setBounds(55,470,60,20);
		contentPane.add(tf_name);

		// CONNECT BUTTON
		b_connect = new JButton("Connect");
		b_connect.setBounds(120,470,85,20);
		contentPane.add(b_connect);
		
		// SEND BUTTON
		b_send = new JButton("Send");
		b_send.setBounds(340,385,65,19);
		contentPane.add(b_send);
		
		// DISCONNECT BUTTON
		btnDc = new JButton("DC");
		btnDc.setBounds(120,520,85,20);
		contentPane.add(btnDc);

		// REFRESH BUTTON
		Refresh = new JButton("Refresh");
		Refresh.setBounds(120,495,85,20);
		contentPane.add(Refresh);
	}
}