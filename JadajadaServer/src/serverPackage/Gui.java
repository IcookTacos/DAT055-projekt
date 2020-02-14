package serverPackage;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Gui extends JFrame implements GuiInterface{

	private static final long serialVersionUID = 1L;
	
	// TEXTAREA
	private static JTextArea textArea;
	
	// TEXTFIELD
	private JTextField inputField;
	
	// BORDER
	
	
	public Gui(){		
		
		windowConfiguration();
		textArea();
		userInputField();
		setVisible(true);

	}


	@Override
	public void createActionListeners() {

	}


	@Override
	public void windowConfiguration() {
		
		setSize(400,400);
		setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}

	@Override
	public void textArea() {	
		textArea = new JTextArea();
		textArea.setBounds(0,0,400,345);
		textArea.setEditable(false);
		textArea.setBackground(new Color(33,33,33));
		textArea.setText(Server.getIp());
		textArea.setForeground(Color.YELLOW);
		add(textArea);
	}

	@Override
	public void userInputField() {
		
		inputField = new JTextField();
		inputField.setBounds(0,345,400,18);
		add(inputField);
		
		
	}

	public static void addTextToServerLog(String text) {

			if(text.equals(null))
				return;
			else
				textArea.setText(textArea.getText().trim() + "\n" + text.trim());
		}
}