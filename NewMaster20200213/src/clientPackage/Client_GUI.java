package clientPackage;

import java.awt.Color;
import java.awt.Font;
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

@SuppressWarnings({ "serial", "unused" })

public class Client_GUI extends JFrame{

	// JPANEL
	public JPanel contentPane;

	// TEXTAREA
	public static JTextArea ta_chat;

	// TEXTFIELD
	public JTextField tf_input;
	public JTextField tf_ip;
	public JTextField tf_port;
	public JTextField tf_name;

	// BUTTON
	public JButton b_connect;
	public JButton btnDc;
	public JButton Refresh;
	public JButton b_send;

	// LABEL
	public JLabel lblIp;
	public JLabel lblPort;
	public JLabel lblName;
	public JLabel titleLabel;
	public JLabel jadaLabel;

	// INPUT & OUTPUT STREAM
	public DataOutputStream dos = null;
	public DataInputStream dis = null;

	// SCROLLPANE 
	public JScrollPane scrollPane;

	// SCOKET
	public Socket s = null;

	// STRING
	public String userName;
	public String received = null;

	// INT, BOOLEAN, ETC
	public boolean connected = false;
	public static int autoScroll = 0;
	Font f1 = new Font(Font.DIALOG,Font.PLAIN,9);
	Font f2 = new Font(Font.DIALOG,Font.PLAIN,25);
	Font f3 = new Font(Font.DIALOG,Font.BOLD|Font.ITALIC,40);



	public Client_GUI() {

		// MAIN WINDOW CONFIGURATION
		setTitle("Client");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600,600);
		setLocationRelativeTo(null);
		setResizable(false);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);

		
		// MAIN TITLE
		jadaLabel = new JLabel("J");
		titleLabel = new JLabel("  ada Jada");

		
		titleLabel.setBounds(15,15,300,20);
		titleLabel.setFont(f2);
		
		jadaLabel.setBounds(10,0,50,50);
		jadaLabel.setFont(f3);
		
		
		add(titleLabel);
		add(jadaLabel);
		


		// SEND BUTTON
		b_send = new JButton("Send");
		b_send.setBounds(300,380,80,20);
		b_send.setContentAreaFilled(false);
		b_send.setFont(f1);
		contentPane.add(b_send);

		// CONNECT BUTTON
		b_connect = new JButton("Connect");
		b_connect.setContentAreaFilled(false);
		b_connect.setFont(f1);
		b_connect.setBounds(125,459,81,18);
		contentPane.add(b_connect);

		// DISCONNECT BUTTON

		btnDc = new JButton("Disconnect");
		btnDc.setContentAreaFilled(false);
		btnDc.setFont(f1);
		btnDc.setBounds(125, 478, 81,18);
		contentPane.add(btnDc);

		// REFRESH BUTTON
		Refresh = new JButton("Refresh");
		Refresh.setContentAreaFilled(false);
		Refresh.setFont(f1);
		Refresh.setBounds(125,497,81,18);
		contentPane.add(Refresh);



		// TEXTFIELD INPUT
		tf_input = new JTextField();
		tf_input.setBounds(39,380,258,20);
		contentPane.add(tf_input);
		tf_input.setColumns(10);

		// SCROLLPANE
		scrollPane = new JScrollPane();
		scrollPane.setBounds(39,75,435,300);
		contentPane.add(scrollPane);

		//TEXTAREA CHATT
		ta_chat = new JTextArea();
		scrollPane.setViewportView(ta_chat);
		ta_chat.setEditable(false);


		// LABEL IP
		lblIp = new JLabel("IP:");
		lblIp.setBounds(5,483,40,10);
		contentPane.add(lblIp);

		// TEXTFIELD IP
		tf_ip = new JTextField();
		tf_ip.setBounds(45,479,65,18);
		tf_ip.setColumns(10);
		tf_ip.setText("localhost");
		contentPane.add(tf_ip);


		// LABEL PORT
		lblPort = new JLabel("Port:");
		lblPort.setBounds(5,502,40,10);
		contentPane.add(lblPort);

		// TEXTFIELD PORT
		tf_port = new JTextField();
		tf_port.setColumns(10);
		tf_port.setBounds(45,499,65,18);
		tf_port.setText("2309");
		contentPane.add(tf_port);


		// LABEL NAME
		lblName = new JLabel("Name:");
		lblName.setBounds(5,465,40,10);
		contentPane.add(lblName);

		// TEXTFIELD NAME
		tf_name = new JTextField();
		tf_name.setColumns(10);
		tf_name.setBounds(45,459,65,20);
		tf_name.setText("Axel");
		contentPane.add(tf_name);


	}

}