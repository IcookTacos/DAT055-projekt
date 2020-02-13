package clientPackage;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

@SuppressWarnings("serial")

public class LoginGUI extends JFrame {

	
	// LABEL
	private JLabel maintitle;
	private JLabel undertitle;
	private JLabel enterName;
	
	// TEXTFIELD
	private JTextField nameField;
	
	// BUTTON
	private JButton login;
	
	// STRING
	private String[] colors = {"Red","Blue","Magenta","Yellow"};
	@SuppressWarnings({ "unchecked", "rawtypes" })
	
	// COMBOBOX
	private JComboBox colorSelect = new JComboBox(colors);
	
	// FONT
	Font f1 = new Font(Font.DIALOG,Font.PLAIN,9);

	public LoginGUI() {

		// Title window configurations
		setTitle("Jada");
		setSize(300,300);
		setLocationRelativeTo(null);
		setResizable(false);
		
		// Main title (Welcome)
		maintitle = new JLabel("Welcome to Jada!");
		maintitle.setFont(new Font(Font.DIALOG,Font.BOLD,23));
		maintitle.setBounds(60,60,200,20);
		add(maintitle);

		// Under title 
		undertitle = new JLabel("Messanger application in DAT055 ");
		undertitle.setFont(new Font(Font.DIALOG,Font.ITALIC,12));
		undertitle.setBounds(60,80,300,20);
		add(undertitle);

		// User-name text field (Text field)
		nameField = new JTextField("");
		nameField.setBounds(90,150,90,20);
		//nameField.addActionListener(e -> loginClick());
		add(nameField);

		// Enter name label (Enter name)
		enterName = new JLabel("Enter username");
		enterName.setBounds(90,130,120,20);
		add(enterName);

		// Login button
		login = new JButton("login");
		login.setBounds(90,170,90,20);
		login.setFont(f1);
		login.setContentAreaFilled(false);
		//login.addActionListener(e -> loginClick());
		add(login);	

		// Combobox
		colorSelect.setSelectedIndex(1);
		colorSelect.setBounds(90,190,90,20);
		colorSelect.setSelectedIndex(0);
		colorSelect.setBackground(Color.LIGHT_GRAY);
		add(colorSelect);

		// Decorations
		getContentPane().add(new DrawingComponent());

		// Set visible
		setVisible(true);


	}
	

		
}