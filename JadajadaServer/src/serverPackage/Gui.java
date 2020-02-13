package serverPackage;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Gui extends JFrame implements GuiInterface{


	/**
	 * Default ftw
	 */
	private static final long serialVersionUID = 1L;

	public JFrame frame;
	
	private JTextField tf_input;
	public JPanel contentPane;
	
	private static JTextArea ta_ServerLog;
	private JTextField tf_IP;
	public static JButton btnRefresh;
	public static JButton btnDc;
	private JTextField textField;
	private JLabel lblPort;


	public Gui(){
		
		
		setTitle("Server");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 377);
		
		
		createFrame();
		creatTextArea();
		createIpPanel();
		createPortPanel();
		createDcBtn();
		createActionListeners();
		setVisible(true);	
		
		//guiStart();

	}


	public void guiStart() {
		
			
	}




	@Override
	public void createFrame() {
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		tf_input = new JTextField();
		tf_input.setBounds(125, 209, 109, 20);
		contentPane.add(tf_input);
		tf_input.setColumns(10);

	}


	@Override
	public void creatTextArea() {

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(49, 26, 294, 136);
		contentPane.add(scrollPane);

		ta_ServerLog = new JTextArea();
		ta_ServerLog.setEditable(false);
		scrollPane.setViewportView(ta_ServerLog);

	}


	@Override
	public void createIpPanel() {

		JLabel lblYouAreHosting = new JLabel("Your IP:");
		lblYouAreHosting.setBounds(49, 277, 44, 14);
		contentPane.add(lblYouAreHosting);

		tf_IP = new JTextField();
		tf_IP.setEditable(false);
		tf_IP.setBounds(96, 274, 103, 20);
		contentPane.add(tf_IP);
		tf_IP.setColumns(10);

		btnRefresh = new JButton("Refresh IP");
		btnRefresh.setBounds(212, 273, 89, 23);
		contentPane.add(btnRefresh);

	}

	public void createPortPanel() {

		textField = new JTextField();
		textField.setText(Integer.toString(Server.portNumber));
		textField.setColumns(10);
		textField.setBounds(96, 305, 103, 20);
		contentPane.add(textField);

		lblPort = new JLabel("Port:");
		lblPort.setBounds(49, 308, 44, 14);
		contentPane.add(lblPort);

	}

	public void createDcBtn() {
		btnDc = new JButton("Dc");
		btnDc.setBounds(10, 185, 51, 23);
		contentPane.add(btnDc);
	}


	public static void addTextToServerLog(String text) {

			if(text.equals(null))
				return;
			else
				ta_ServerLog.setText(ta_ServerLog.getText().trim() + "\n" + text.trim());
		}


	@Override
	public void createActionListeners() {
		Gui.btnDc.addActionListener((e) -> Server.shutDown());			
		Gui.btnRefresh.addActionListener((e) -> tf_IP.setText(Server.getIp()));
		
	}
		
	/*

	public static void main (String args[])
	{
		Gui w1 = new Gui();
	}
	
	*/

}
