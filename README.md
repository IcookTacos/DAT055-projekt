Samuel


package msn_GUI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.geom.Rectangle2D;

@SuppressWarnings({ "serial", "unused" })


public class GUIwelcome extends JFrame implements ActionListener {

	// Labels, TextFields, Buttons
	private JLabel maintitle;
	private JLabel undertitle;
	private JLabel enterName;
	private JTextField nameField;
	private JButton login;
	
	// Error messages
	public String errorS = "INVALID USERNAME!\n(cannot containt the letter 's'";
	public String errorSpace = "INVALID USERNAME!\n(cannot be empty or contain spaces";
	
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
		
		// Decorations
		getContentPane().add(new DrawingComponent());
				
		// Set visible
		setVisible(true);
		
		
	}
	public void loginClick() {
		global.userName = nameField.getText();
		if(global.loggedIn==false) {
		for (int i =0; i< global.userName.length(); i++) {
			char c = global.userName.charAt(i);
			
			// Not logged in
			if(c==' ') { JOptionPane.showMessageDialog(null, errorSpace); global.loggedIn=false; break;  	}	// Error !
			if(c=='s') { JOptionPane.showMessageDialog(null, errorS); global.loggedIn=false;	 break; 	}	// Error !
			if(c=='S') { JOptionPane.showMessageDialog(null, errorS); global.loggedIn=false;	 break;		}	// Error !
			
			// Logged in
			JOptionPane.showMessageDialog(null, "Welcome " + global.userName);
			global.loggedIn=true;
			System.out.println("LOGGEDIN = " + global.loggedIn);
			break;
			}
		}
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
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	} 
}
