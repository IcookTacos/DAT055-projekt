package msn_GUI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import ServerClient.Client;

import java.awt.geom.Rectangle2D;

@SuppressWarnings({ "serial", "unused" })


public class GUIwelcome extends JFrame implements ActionListener {

	// Labels, TextFields, Buttons
	private JLabel maintitle;
	private JLabel undertitle;
	private JLabel enterName;
	private JTextField nameField;
	private JButton login;
	private String[] colors = {"Red","Blue","Magenta","Yellow"};
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private JComboBox colorSelect = new JComboBox(colors);
	
	// Error messages
	public String errorSpace = "INVALID USERNAME!\ncannot containt  '_' or spaces ";
	
	public GUIwelcome() {
		
		// Title window configurations
		setTitle("MSN");
		setSize(300,300);
		setLocationRelativeTo(null);
		setResizable(false);
		//setLayout(null);
		
		
		// Main title (Welcome)
		maintitle = new JLabel("Welcome!");
		maintitle.setFont(new Font("Arial",Font.BOLD,23));
		maintitle.setBounds(80,50,120,20);
		add(maintitle);
		
		// Under title (This is stupid)
		undertitle = new JLabel("This is stupid");
		undertitle.setFont(new Font("Arial",Font.ITALIC,12));
		undertitle.setBounds(80,68,120,20);
		add(undertitle);
			
		// User-name text field (Text field)
		nameField = new JTextField("");
		nameField.setBounds(90,150,120,20);
		nameField.addActionListener(e -> loginClick());
		add(nameField);
		
		// Enter name label (Enter name)
		enterName = new JLabel("Enter username");
		enterName.setBounds(90,130,120,20);
		add(enterName);
		
		// Login button
		login = new JButton("login");
		login.setBounds(90,170,120,20);
		login.addActionListener(e -> loginClick());
		add(login);	
		
		// Combobox
		colorSelect.setSelectedIndex(1);
		colorSelect.setBounds(90,190,90,20);
		colorSelect.setSelectedIndex(0);
		add(colorSelect);
		
		// Decorations
		getContentPane().add(new DrawingComponent());

		// Set visible
		setVisible(true);
		
		
	}
	
	// Better login function, works better
	public void loginClick() {
		String tempName = nameField.getText();
		int length = tempName.length();
	
		CharSequence empty = "";				// Don't work, if implemented blocks all usernames, don't know why
		CharSequence space = " ";
		CharSequence underscore = "_";
		
		if(	(tempName.contains(space)) || ((tempName.contains(underscore)))) {
		global.loggedIn=false; 
		JOptionPane.showMessageDialog(null, errorSpace);
		System.out.println("LOGGED IN =" +global.loggedIn);		
		}
		else{
		global.loggedIn=true; 
		global.userName=tempName;	
		System.out.println("LOGGED IN =" +global.loggedIn);		
		}

		if(global.loggedIn==true) {
			JOptionPane.showMessageDialog(null, "Welcome " + global.userName); 
			setVisible(false);
			storeColor();	
			GUIChat w2 = new GUIChat();
			Client client = new Client("127.0.0.1", 5000); 			// CLIENT CONNECTS TO SERVER
		}
	}
	
	public void storeColor() {
		int colorIndex = colorSelect.getSelectedIndex();
		if(colorIndex==0) {	global.userColor=Color.RED;			}		// Red
		if(colorIndex==1) {	global.userColor=Color.BLUE;		}		// Blue
		if(colorIndex==2) {	global.userColor=Color.MAGENTA;		}		// Magenta
		if(colorIndex==3) {	global.userColor=Color.YELLOW;		}		// Yellow
		
		System.out.println(global.userColorIndex);
	}

	
	// Old logic for actionPerformed 
	/*
	@Override
	public void actionPerformed(ActionEvent e) {
	if(e.getSource()==login) {
		global.userName = nameField.getText();
		for (int i =0; i< global.userName.length(); i++) {
			char c = global.userName.charAt(i);
			if(c==' ') {
				JOptionPane.showMessageDialog(null, errorSpace);
				System.exit(1);
			}
			if(c=='s') {
				JOptionPane.showMessageDialog(null, errorS);
				System.exit(1);
			}
			if(c=='S') {
				JOptionPane.showMessageDialog(null, errorS);
			}			
		}	
		JOptionPane.showMessageDialog(null, "Welcome " + global.userName);
		global.loggedIn=true;
		System.out.println("LOGGEDIN = " + global.loggedIn);
		
		} 
	}*/
	
	// Old login function, disregard
	/*
	public void loginClickOLD() {
		global.userName = nameField.getText();
		if(global.loggedIn==false) {
		for (int i =0; i< global.userName.length(); i++) {
			char c = global.userName.charAt(i);
			// Not logged in		// Needs to be fixed
			if(c==' ') { JOptionPane.showMessageDialog(null, errorSpace); global.loggedIn=false; break;  	}	// Error !
			//if(c=='s') { JOptionPane.showMessageDialog(null, errorS); global.loggedIn=false;	 break; 	}	// Error !
			//if(c=='S') { JOptionPane.showMessageDialog(null, errorS); global.loggedIn=false;	 break;		}	// Error !
			
			// Logged in
			JOptionPane.showMessageDialog(null, "Welcome " + global.userName);
			global.loggedIn=true;
			System.out.println("LOGGEDIN = " + global.loggedIn);
			setVisible(false);
			storeColor();
			GUIChat w2 = new GUIChat();
			break;
			}
		}
	}*/
	
	@Override
	public void actionPerformed(ActionEvent e) {} 
}
