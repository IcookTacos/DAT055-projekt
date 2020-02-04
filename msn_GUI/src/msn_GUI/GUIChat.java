package msn_GUI;

import java.awt.Color;
import java.awt.Font;
import javax.swing.*;
import javax.swing.border.Border;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings({ "serial", "unused" })

public class GUIChat extends JFrame implements ActionListener{

	// Integers
	private int userOnlineIndex=1;
	
	// Labels
	private JLabel mainTitle;
	private JLabel underTitle;
	private JLabel loggo;
	private JLabel placeholder;
	private JLabel userNameLabel;
	private JLabel userNameDisplay;
	private JLabel usersOnline;
	private JLabel userOnlineIndexLabel;
	private JLabel chatWindowLabel;
	private JLabel usersLabel;
	
	// Textfield
	private JTextField message;
	
	// JButton
	public static JButton sendMessageBTN;
	
	// TextArea
	public static JTextArea chatWindow;
	private JTextArea usersWindow;
	
	// Decoration, borders, etc
	Border blackLineBorder = BorderFactory.createLineBorder(Color.BLACK, 1);

	// Other classes & objects
	MessageBox messageField = new MessageBox();
	
	public GUIChat() {
		
		// Window configurations
		setTitle("Welcome to MSN");
		setSize(500,500);
		setLocationRelativeTo(null);
		setResizable(false);
		
		// Main Title
		mainTitle = new JLabel ("Welcome to ");
		mainTitle.setFont(new Font("Arial",Font.PLAIN,20));
		mainTitle.setBounds(10,0,120,20);
		add(mainTitle);
		
		// MSN loggo
		loggo = new JLabel("MSN");
		//Border MSNBorder = BorderFactory.create
		//loggo.setBorder(MSNBorder);
		loggo.setFont(new Font("Arial",Font.BOLD,23));
		loggo.setForeground(Color.GREEN);
		loggo.setBounds(120,0,120,20);
		add(loggo);
		
		// Undertitle
		underTitle = new JLabel("DAT055 projektarbete");
		underTitle.setBounds(10,20,200,20);
		add(underTitle);
		
		// Textmessage field v2
		add(messageField);
		
		// Send message button
		sendMessageBTN = new JButton("Send");
		sendMessageBTN.setBounds(330,419,63,17);
		add(sendMessageBTN);
		
		// Userlabel GUI
		userNameLabel = new JLabel("Username: ");
		userNameLabel.setBounds(325,0,120,20);
		add(userNameLabel);
		
		// LoggedIn UserName display
		userNameDisplay = new JLabel(global.userName);
		userNameDisplay.setForeground(global.userColor);
		userNameDisplay.setBounds(390,0,120,20);
		add(userNameDisplay);
		
		// UsersOnline
		usersOnline = new JLabel("Users Online:");
		usersOnline.setBounds(325,15,120,20);
		add(usersOnline);
		
		// UsersOnline index
		userOnlineIndexLabel = new JLabel(""+userOnlineIndex);
		userOnlineIndexLabel.setBounds(405,15,120,20);
		userOnlineIndexLabel.setForeground(Color.BLUE);
		add(userOnlineIndexLabel);
		
		// Chatwindow label
		chatWindowLabel = new JLabel("Chat:");
		chatWindowLabel.setBounds(25,45,120,20);
		add(chatWindowLabel);
		
		// Chatwindow
		chatWindow = new JTextArea();
		chatWindow.setBounds(25,65,275,350);
		chatWindow.setBorder(blackLineBorder);
		chatWindow.setLineWrap(true);
		chatWindow.setEditable(false);
		add(chatWindow);
		
		// Users label
		usersLabel = new JLabel("Users");
		usersLabel.setBounds(325,45,120,20);
		add(usersLabel);
		
		// Users window
		usersWindow = new JTextArea();
		usersWindow.setBounds(325,65,100,300);
		usersWindow.setBorder(blackLineBorder);
		usersWindow.setEditable(false);
		usersWindow.append(global.userName);
		add(usersWindow);
		
		// Placeholder	// Quick fix for a strange bug with the layout
		placeholder = new JLabel("");
		placeholder.setBounds(0,0,0,0);
		add(placeholder);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	

}
