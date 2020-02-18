package serverPackage;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import handlerPackage.ClientHandler;

public class Gui extends JFrame implements GuiInterface {

	private static final long serialVersionUID = 1L;

	// TEXTAREA
	public static JTextArea textArea;

	// TEXTFIELD
	public static JTextField inputField;

	public Gui() {

		windowInit();
		textAreaInit();
		userInputField();
		createActionListeners();
		setVisible(true);

	}

	@Override
	public void createActionListeners() {
		inputField.addActionListener((e) -> adminCommands());
	}

	@Override
	public void windowInit() {

		setSize(400, 400);
		setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	@Override
	public void textAreaInit() {
		textArea = new JTextArea();
		textArea.setBounds(0, 0, 390, 345);
		textArea.setEditable(false);
		textArea.setBackground(new Color(33, 33, 33));
		textArea.setText(Server.getIp());
		textArea.setForeground(Color.YELLOW);
		textArea.setLineWrap(true);
		add(textArea);
	}

	@Override
	public void userInputField() {

		inputField = new JTextField();
		inputField.setBounds(0, 345, 400, 18);
		inputField.setBackground(new Color(33,33,33));
		inputField.setForeground(Color.YELLOW);
		inputField.setBorder(null);
		inputField.setCaretColor(Color.YELLOW);
		add(inputField);

	}

	private void adminCommands() {
		String adminInput = "";
		adminInput = Gui.inputField.getText();
		Gui.inputField.setText("");
		if ((adminInput.compareTo("/disconnect")) == 0) {
			Server.shutDown();
		}
		if ((adminInput.compareTo("/help") == 0)) {
			adminHelp();
		}
		
		if( (adminInput.compareTo("/latencySLOW"))==0) {
			setLatencySLOW();
		}
		
		if( (adminInput.compareTo("/latencyFAST"))==0) {
			setLatencyFAST();
		}
		
		if( (adminInput.compareTo("/latencyNORMAL"))==0) {
			setLatencyNORMAL();
		}
		
		if ((adminInput.startsWith("/tellAll"))) {
			String line = adminInput.replaceFirst("/tellAll", "");
			ClientHandler.tellEveryone(line, " Admin:");
		}
		

	}

	public static void addTextToServerLog(String text) {

		if (text.equals(null))
			return;
		else
			textArea.setText(textArea.getText().trim() + "\n" + text.trim());

	}

	public void adminHelp() {

		addTextToServerLog("/disconnect = terminate the server\n/latencySLOW = slows down the server \n/latencyFAST = speeds up the server\n /tellAll <msg> sends message to all clients");
	}
	
	public void setLatencySLOW() {
		ClientHandler.latency=5000;
		ClientHandler.tellEveryone("SERVER SET TO SLOW", " Admin:");
	}
	
	public void setLatencyFAST() {
		ClientHandler.latency=5;
		ClientHandler.tellEveryone("SERVER SET TO FAST", " Admin:");
	}
	
	public void setLatencyNORMAL() {
		ClientHandler.latency=50;
		ClientHandler.tellEveryone("SERVER SET TO FAST", " Admin:");
	}
	
}