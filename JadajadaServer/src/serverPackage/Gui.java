package serverPackage;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Gui extends JFrame implements GuiInterface {

	private static final long serialVersionUID = 1L;

	// TEXTAREA
	public static JTextArea textArea;

	// TEXTFIELD
	public static JTextField inputField;

	// BORDER

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

		add(inputField);

	}

	private void adminCommands() {
		String adminInput = "";
		adminInput = Gui.inputField.getText();
		Gui.inputField.setText("");
		if ((adminInput.compareTo("disconnect")) == 0) { Server.shutDown(); }
		if ((adminInput.compareTo("help")==0)) {addTextToServerLog("disconnect = terminate the server\nlatencySLOW = slows down the server \nlatencyFAST = speeds up the server"); }
	}

	public static void addTextToServerLog(String text) {

		if (text.equals(null))
			return;
		else
			textArea.setText(textArea.getText().trim() + "\n" + text.trim());
	}
}