package serverPackage;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.plaf.basic.BasicScrollBarUI;

import handlerPackage.ClientHandler;

public class Gui extends JFrame implements GuiInterface {

	private static final long serialVersionUID = 1L;

	// TEXTAREA
	public static JTextArea textArea;
	private JScrollPane scrollPane;

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

		setSize(405, 400);
		setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	@Override
	public void textAreaInit() {

		textArea = new JTextArea();
		scrollPane = new JScrollPane(textArea);
		textArea.setEditable(false);
		textArea.setBackground(new Color(33, 33, 33));
		textArea.setText(Server.getIp());
		textArea.setForeground(Color.YELLOW);
		scrollPaneColor();
		scrollPane.setBounds(0, 0, 390, 345);
		scrollPane.setBorder(null);
		add(scrollPane);

	}

	private void scrollPaneColor() {
		scrollPane.setBackground(Color.DARK_GRAY);
		scrollPane.setForeground(Color.DARK_GRAY);
		scrollPane.setBorder(null);
		scrollPane.getVerticalScrollBar().setBackground(Color.DARK_GRAY);
		scrollPane.getHorizontalScrollBar().setBackground(Color.DARK_GRAY);
		scrollPane.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
			@Override
			protected void configureScrollBarColors() {
				this.thumbColor = Color.GRAY;
			}
		});

	}

	@Override
	public void userInputField() {

		inputField = new JTextField();
		inputField.setBounds(0, 345, 400, 18);
		inputField.setBackground(new Color(33, 33, 33));
		inputField.setForeground(Color.YELLOW);
		inputField.setBorder(null);
		inputField.setCaretColor(Color.YELLOW);
		add(inputField);

	}

	private void adminCommands() {
		int errorFlag = 1;
		String adminInput = "";
		adminInput = Gui.inputField.getText();
		Gui.inputField.setText("");
		if ((adminInput.compareTo("/disconnect")) == 0) {
			Server.shutDown();
			errorFlag = 0;
		}
		if ((adminInput.compareTo("/help") == 0)) {
			adminHelp();
			errorFlag = 0;

		}

		if ((adminInput.compareTo("/latencySLOW")) == 0) {
			setLatencySLOW();
			errorFlag = 0;

		}

		if ((adminInput.compareTo("/latencyFAST")) == 0) {
			setLatencyFAST();
			errorFlag = 0;

		}

		if ((adminInput.compareTo("/latencyNORMAL")) == 0) {
			setLatencyNORMAL();
			errorFlag = 0;

		}

		if ((adminInput.startsWith("/tellAll"))) {
			String line = adminInput.replaceFirst("/tellAll", "");
			ClientHandler.tellEveryone(line, " Admin:");
			errorFlag = 0;

		}

		if ((errorFlag == 1)) {
			addTextToServerLog("INVALID COMMAND\nType /help for all commands");

		}

	}

	public static void addTextToServerLog(String text) {

		if (text.equals(null))
			return;
		else
			textArea.setText(textArea.getText().trim() + "\n" + text.trim());

	}

	private void adminHelp() {

		addTextToServerLog(
				"/disconnect = terminate the server\n/latencySLOW = slows down the server \n/latencyFAST = speeds up the server\n /tellAll <msg> sends message to all clients");
	}

	private void setLatencySLOW() {
		ClientHandler.latency = 5000;
		ClientHandler.tellEveryone("SERVER SET TO SLOW", " Admin:");
	}

	private void setLatencyFAST() {
		ClientHandler.latency = 5;
		ClientHandler.tellEveryone("SERVER SET TO FAST", " Admin:");
	}

	private void setLatencyNORMAL() {
		ClientHandler.latency = 50;
		ClientHandler.tellEveryone("SERVER SET TO FAST", " Admin:");
	}

}