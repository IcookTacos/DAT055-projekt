package clientPackage;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;



public class Client_GUI extends JFrame implements GuiInterface {

	private static final long serialVersionUID = 1L;

	// JPANEL
	public JPanel contentPane;

	// TEXTAREA
	public static JTextArea ta_chat;

	// TEXTFIELD
	public JTextField tf_input;

	// BUTTON
	public JButton btnDc;
	public JButton Refresh;
	public JButton b_send;

	// LABEL
	public JLabel lblIp;			
	public JLabel lblPort;		
	public JLabel lblName;		
	public JLabel titleLabel;
	public JLabel jadaLabel;

	// SCROLLPANE 
	public JScrollPane scrollPane;

	// STRING
	public String userName;
	public String received = null;
	public String user = null;		//	MOVED

	// INT, BOOLEAN, ETC
	Border blackline = BorderFactory.createLineBorder(Color.BLACK);
	Border outline = BorderFactory.createTitledBorder(blackline);
	public static int autoScroll = 0;
	Font f1 = new Font(Font.DIALOG,Font.PLAIN,9);
	Font f2 = new Font(Font.DIALOG,Font.PLAIN,25);
	Font f3 = new Font(Font.DIALOG,Font.BOLD|Font.ITALIC,40);



	public Client_GUI() {
		createMainWindow();
		createClientName();
		createBtns();
		createTxtArea();
		createTxtFields();
		createActionListerners();
		setVisible(true);
	}


	@Override
	public void createActionListerners() {
		// CONNECT BUTTON
		//b_connect.addActionListener((e) -> logInClicked());	// MOVED
		// SEND MESSAGE BUTTON
		b_send.addActionListener((e) -> sendMsgClicked());

		// DISCONNECT BUTTON
		btnDc.addActionListener((e) -> disconnect());

		// REFRESH BUTTON
		Refresh.addActionListener((e) -> setAutoScroll());


	}

	@Override
	public void createMainWindow() {
		// MAIN WINDOW CONFIGURATION
		setTitle("Client");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(645,480);
		setLocationRelativeTo(null);
		setResizable(false);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);


	}

	@Override
	public void createClientName() {
		// MAIN TITLE
		
		jadaLabel = new JLabel("J");
		jadaLabel.setForeground(Color.RED);
		jadaLabel.setBounds(20,20,50,50);
		jadaLabel.setBorder(outline);
		jadaLabel.setFont(f3);
		
		titleLabel = new JLabel("  ada Jada");
		titleLabel.setBounds(25,35,300,20);
		titleLabel.setFont(f2);

		


		



		add(titleLabel);
		add(jadaLabel);


	}

	@Override
	public void createBtns() {

		// SEND BUTTON
		b_send = new JButton("Send");
		b_send.setBounds(300,380,80,20);
		b_send.setContentAreaFilled(false);
		b_send.setFont(f1);
		contentPane.add(b_send);

		// DISCONNECT BUTTON
		btnDc = new JButton("Disconnect");
		btnDc.setContentAreaFilled(false);
		btnDc.setFont(f1);
		btnDc.setBounds(390,55, 81,18);
		contentPane.add(btnDc);

		// REFRESH BUTTON
		Refresh = new JButton("Refresh");
		Refresh.setContentAreaFilled(false);
		Refresh.setFont(f1);
		Refresh.setBounds(382,380,80,20);
		contentPane.add(Refresh);

	}

	@Override
	public void createTxtArea() {
		// SCROLLPANE
		scrollPane = new JScrollPane();
		scrollPane.setBounds(39,75,435,300);
		contentPane.add(scrollPane);

		//TEXTAREA CHATT
		ta_chat = new JTextArea();
		scrollPane.setViewportView(ta_chat);
		ta_chat.setEditable(false);

	}

	@Override
	public void createTxtFields() {
		// TEXTFIELD INPUT
		tf_input = new JTextField();
		tf_input.setBounds(39,380,258,20);
		contentPane.add(tf_input);
		tf_input.setColumns(10);


	}
	public void setAutoScroll() {
		if(Client_GUI.autoScroll == 1)
		{
			Client_GUI.autoScroll = 0;
		} else
		{
			Client_GUI.autoScroll = 1;
		}

	}


	@Override
	public void disconnect() {
		Client.killConnection();
		contentPane.setVisible(false);
		dispose();
		System.exit(0);

	}


	@Override
	public void sendMsgClicked() {
		String message = tf_input.getText();
		Client.sendMsg(message, user);
		tf_input.setText(null);

	}

	public static void recieveMsg(String messageRecieved) {
		ta_chat.append(messageRecieved + "\n");
		if(autoScroll == 1) {
			ta_chat.setCaretPosition(ta_chat.getDocument().getLength());
		}

	}

}