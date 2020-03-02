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
import javax.swing.ScrollPaneConstants;
import javax.swing.border.Border;
import javax.swing.plaf.basic.BasicScrollBarUI;

import java.awt.Toolkit;

public class Client_GUI extends JFrame implements GuiInterface {

	private static final long serialVersionUID = 1L;

	// JPANEL
	private JPanel contentPane;

	// TEXTAREA
	public static JTextArea ta_chat;

	// TEXTFIELD
	public JTextField tf_input;

	// BUTTON
	public JButton btnDc;
	public JButton Refresh;
	public JButton b_send;

	// LABEL
	private JLabel titleLabel;
	private JLabel jadaLabel;
	private JLabel jada2Label;
	private JLabel scrollingOn;
	private JLabel scrollingOff;

	// SCROLLPANE
	public JScrollPane scrollPane;

	// STRING
	public String userName;
	public String received = null;

	// BORDERS, FONTS, INT,
	Border blackline = BorderFactory.createLineBorder(Color.BLACK);
	Border outline = BorderFactory.createTitledBorder(blackline);
	Font f1 = new Font(Font.DIALOG, Font.PLAIN, 9);
	Font f2 = new Font(Font.DIALOG, Font.PLAIN, 25);
	Font f3 = new Font(Font.DIALOG, Font.BOLD | Font.ITALIC, 40);
	public static int autoScroll = 0;

	public Client_GUI() {

		windowInit(); // Initialize window frame, size, etc
		interfaceInit(); // Initialize interface
		createActionListerners(); // Initialize Action Listeners
		setColorScheme(LoginGUI.colorIndex); // Selects interface theme
		setAutoScroll();
		setVisible(true);
	}

	@Override
	public void createActionListerners() {

		// SEND MESSAGE BUTTON
		b_send.addActionListener((e) -> sendMsgClicked());

		// DISCONNECT BUTTON
		btnDc.addActionListener((e) -> disconnect());

		// REFRESH BUTTON
		Refresh.addActionListener((e) -> setAutoScroll());

	}

	@Override
	public void windowInit() {
		// MAIN WINDOW CONFIGURATION
		setTitle("JadaJada Messenger");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(525, 480);
		setLocationRelativeTo(null);
		setResizable(false);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);

	}

	public void interfaceInit() {

		// MAIN TITLE
		jadaLabel = new JLabel("J");
		jadaLabel.setForeground(Color.RED);
		jadaLabel.setBounds(20, 20, 50, 50);
		jadaLabel.setFont(f3);

		jada2Label = new JLabel("J");
		jada2Label.setForeground(Color.RED);
		jada2Label.setBounds(83, 20, 50, 50);
		jada2Label.setFont(f3);

		titleLabel = new JLabel("  ada   ada");
		titleLabel.setBounds(25, 35, 300, 20);
		titleLabel.setFont(f2);

		add(titleLabel);
		add(jadaLabel);
		add(jada2Label);

		// SEND BUTTON
		b_send = new JButton("Send");
		b_send.setBounds(300, 380, 80, 20);
		b_send.setContentAreaFilled(false);
		b_send.setFont(f1);
		contentPane.add(b_send);

		// DISCONNECT BUTTON
		btnDc = new JButton("Disconnect");
		btnDc.setContentAreaFilled(false);
		btnDc.setFont(f1);
		btnDc.setBounds(390, 55, 81, 18);
		contentPane.add(btnDc);

		// REFRESH BUTTON
		Refresh = new JButton("Scrolling:");
		Refresh.setContentAreaFilled(false);
		Refresh.setFont(f1);
		Refresh.setBounds(382, 380, 72, 20);
		contentPane.add(Refresh);

		// TEXTAREA CHATT
		ta_chat = new JTextArea();
		ta_chat.setWrapStyleWord(true);
		ta_chat.setLineWrap(true);
		ta_chat.setEditable(false);

		// SCROLLPANE
		scrollPane = new JScrollPane(ta_chat, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
		scrollPane.setBounds(39, 75, 435, 300);
		contentPane.add(scrollPane);

		// LABEL OFF
		scrollingOff = new JLabel("Off");
		scrollingOff.setFont(new Font(Font.DIALOG, Font.ITALIC, 12));
		scrollingOff.setBounds(460, 370, 40, 40);
		add(scrollingOff);

		// LABEL ON
		scrollingOn = new JLabel("On");
		scrollingOn.setFont(new Font(Font.DIALOG, Font.ITALIC, 12));
		scrollingOn.setBounds(460, 370, 40, 40);
		add(scrollingOn);

		// TEXTFIELD INPUT
		tf_input = new JTextField();
		tf_input.setBounds(39, 380, 258, 20);
		tf_input.setColumns(10);
		tf_input.addActionListener(e -> sendMsgClicked());
		contentPane.add(tf_input);

	}

	public void setAutoScroll() {
		if (autoScroll == 1) {

			scrollingOff.setVisible(true);
			scrollingOn.setVisible(false);
			autoScroll = 0;

		} else {

			scrollingOff.setVisible(false);
			scrollingOn.setVisible(true);
			autoScroll = 1;
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
		if (message != " ") {
			if (message != null) {
				if (message != "") {
					if (message != "\n") {
						Client.sendMsg(message, LoginGUI.user);
					}
				}
			}
		}
		tf_input.setText(null);

	}

	public static void recieveMsg(String messageRecieved) {
		ta_chat.append(messageRecieved + "\n");
		if (autoScroll == 1) {
			ta_chat.setCaretPosition(ta_chat.getDocument().getLength());
		}
		Toolkit.getDefaultToolkit().beep();

	}

	@Override
	public void setColorScheme(int index) {

		if (index == 1) {

			// WINDOW COLOR
			contentPane.setBackground(Color.DARK_GRAY);
			contentPane.setBorder(BorderFactory.createLineBorder(Color.GRAY));
			setTitle("JadaJada Tibia");

			// SCROLLPANE COLORS
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

			// LOGGO COLOR
			jadaLabel.setForeground(Color.yellow);
			jada2Label.setForeground(Color.yellow);
			titleLabel.setForeground(Color.white);

			// Text area backround color
			ta_chat.setBackground(Color.GRAY);
			// Textfield background color
			tf_input.setBackground(Color.GRAY);

			// Textarea text color
			ta_chat.setForeground(Color.yellow);
			// Textfield text color
			tf_input.setForeground(Color.yellow);

			// Colors around text area & text field
			ta_chat.setBorder(null);
			tf_input.setBorder(null);

			// if u want color use the examples below
			// ta_chat.setBorder(BorderFactory.createLineBorder(Color.yellow));
			// tf_input.setBorder(BorderFactory.createLineBorder(Color.yellow));

			// BUTTON COLORS

			// Text color in buttons
			btnDc.setForeground(Color.white);
			Refresh.setForeground(Color.white);
			b_send.setForeground(Color.white);
			scrollingOff.setForeground(Color.white);
			scrollingOn.setForeground(Color.white);

			// If true background color will be added
			btnDc.setContentAreaFilled(true);
			Refresh.setContentAreaFilled(true);
			b_send.setContentAreaFilled(true);

			// Set background color in buttons
			btnDc.setBackground(Color.GRAY);
			Refresh.setBackground(Color.GRAY);
			b_send.setBackground(Color.GRAY);
			scrollingOff.setBackground(Color.GRAY);
			scrollingOn.setBackground(Color.GRAY);

		}

		if (index == 2) {

			// WINDOW COLOR
			contentPane.setBackground(Color.BLACK);
			contentPane.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			setTitle("JadaJada Matrix");

			// SCROLLPANE COLORS
			scrollPane.setBackground(new Color(33, 33, 33));
			scrollPane.setForeground(new Color(33, 33, 33));
			scrollPane.setBorder(null);
			scrollPane.getVerticalScrollBar().setBackground(new Color(33, 33, 33));
			scrollPane.getHorizontalScrollBar().setBackground(new Color(33, 33, 33));
			scrollPane.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
				@Override
				protected void configureScrollBarColors() {
					this.thumbColor = Color.BLACK;
				}
			});

			// LOGGO COLOR
			jadaLabel.setForeground(new Color(80, 146, 36));
			jada2Label.setForeground(new Color(80, 146, 36));
			titleLabel.setForeground(new Color(48, 95, 17));

			// Text area backround color
			ta_chat.setBackground(new Color(37, 37, 37));
			// Textfield background color
			tf_input.setBackground(new Color(37, 37, 37));

			// Textarea text color
			ta_chat.setForeground(new Color(48, 95, 17));
			// Textfield text color
			tf_input.setForeground(new Color(48, 95, 17));

			// Colors around text area & text field
			ta_chat.setBorder(null);
			tf_input.setBorder(null);

			// if u want color use the examples below
			// ta_chat.setBorder(BorderFactory.createLineBorder(Color.yellow));
			// tf_input.setBorder(BorderFactory.createLineBorder(Color.yellow));

			// BUTTON COLORS

			// Text color in buttons
			btnDc.setForeground(new Color(37, 37, 37));
			Refresh.setForeground(new Color(37, 37, 37));
			b_send.setForeground(new Color(37, 37, 37));
			scrollingOff.setForeground(new Color(37, 37, 37));
			scrollingOn.setForeground(new Color(37, 37, 37));

			// If true background color will be added
			btnDc.setContentAreaFilled(true);
			Refresh.setContentAreaFilled(true);
			b_send.setContentAreaFilled(true);

			// Set background color in buttons
			btnDc.setBackground(new Color(48, 95, 17));
			Refresh.setBackground(new Color(48, 95, 17));
			b_send.setBackground(new Color(48, 95, 17));
			scrollingOff.setBackground(new Color(48, 95, 17));
			scrollingOn.setBackground(new Color(48, 95, 17));
			scrollingOn.setBorder(null);
			b_send.setBorder(null);
			Refresh.setBorder(null);
			btnDc.setBorder(null);
		}

	}

}