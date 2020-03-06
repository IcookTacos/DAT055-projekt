package clientPackage;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
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

public class ClientGUI extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// JPANEL
	private JPanel contentPane;

	// TEXTAREA
	protected static JTextArea ta_chat;

	// TEXTFIELD
	protected JTextField tf_input;

	// BUTTON
	protected JButton btnDc;
	private JButton refresh;
	protected JButton b_send;
	private static String[] colors = { "Default", "Tibia", "Matrix" };

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private JComboBox colorSelect = new JComboBox(colors);

	// LABEL
	private JLabel titleLabel;
	private JLabel jadaLabel;
	private JLabel jada2Label;
	private JLabel scrollingOn;
	private JLabel scrollingOff;

	// SCROLLPANE
	private JScrollPane scrollPane;

	// BORDERS, FONTS, INT,
	Border blackline = BorderFactory.createLineBorder(Color.BLACK);
	Border outline = BorderFactory.createTitledBorder(blackline);
	Font f1 = new Font(Font.DIALOG, Font.PLAIN, 9);
	Font f2 = new Font(Font.DIALOG, Font.PLAIN, 25);
	Font f3 = new Font(Font.DIALOG, Font.BOLD | Font.ITALIC, 40);
	private int autoScroll = 0;

	/**
	 * Consctructor for ClientGUI Responsible for all graphics after user has logged
	 * in
	 * 
	 * @param startTheme
	 */
	public ClientGUI(int startTheme) {
		windowInit(); // Initialize window frame, size, etc
		interfaceInit(); // Initialize interface
		setColorScheme(startTheme); // Selects interface theme
		setAutoScroll();
		setVisible(true);

	}

	/**
	 * Modifies the extended JFrame
	 */
	private void windowInit() {
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

	/**
	 * Initializes all elements in window
	 */
	private void interfaceInit() {

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
		btnDc.setBounds(391, 55, 81, 18);
		contentPane.add(btnDc);

		// REFRESH BUTTON
		refresh = new JButton("Scrolling:");
		refresh.setContentAreaFilled(false);
		refresh.setFont(f1);
		refresh.setBounds(382, 380, 72, 20);
		refresh.addActionListener((e) -> setAutoScroll());
		contentPane.add(refresh);

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

		contentPane.add(tf_input);

		// COMBOBOX
		colorSelect.setSelectedIndex(0);
		colorSelect.setBounds(382, 402, 72, 18);
		colorSelect.setBackground(Color.LIGHT_GRAY);
		add(colorSelect);
		colorSelect.addActionListener((e) -> setColorScheme(storeColor()));

	}

	/**
	 * Enables the user to have the chat automaticly scrolled whenver a message is
	 * recieved
	 */
	private void setAutoScroll() {
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

	/**
	 * When a string is recieved from the inputstream in client it uses this method
	 * in order to show the message to user
	 * 
	 * @param messageRecieved
	 */
	public void recieveMsg(String messageRecieved) {
		ta_chat.append(messageRecieved + "\n");
		if (autoScroll == 1) {
			ta_chat.setCaretPosition(ta_chat.getDocument().getLength());
		}
		Toolkit.getDefaultToolkit().beep();

	}

	/**
	 * Gets an integer index number for chosen theme
	 * 
	 * @return
	 */
	private int storeColor() {
		return colorSelect.getSelectedIndex();
	}

	
	/**
	 * recolors the client window
	 * @param index
	 */
	private void setColorScheme(int index) {

		if (index == 0) {

			// WINDOW COLOR
			contentPane.setBackground(new Color(232, 232, 232));
			contentPane.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
			setTitle("JadaJada Tibia");

			// SCROLLPANE COLORS
			scrollPane.setBackground(new Color(216, 216, 216));
			scrollPane.setForeground(new Color(216, 216, 216));
			scrollPane.setBorder((BorderFactory.createLineBorder(Color.DARK_GRAY)));
			scrollPane.getVerticalScrollBar().setBackground(Color.DARK_GRAY);
			scrollPane.getHorizontalScrollBar().setBackground(Color.DARK_GRAY);
			scrollPane.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
				@Override
				protected void configureScrollBarColors() {
					this.thumbColor = Color.LIGHT_GRAY;
				}
			});

			// LOGGO COLOR
			jadaLabel.setForeground(Color.red);
			jada2Label.setForeground(Color.red);
			titleLabel.setForeground(Color.black);

			// Text area backround color
			ta_chat.setBackground(Color.white);
			// Textfield background color
			tf_input.setBackground(Color.white);

			// Textarea text color
			ta_chat.setForeground(Color.black);
			// Textfield text color
			tf_input.setForeground(Color.black);

			// Colors around text area & text field
			// ta_chat.setBorder((BorderFactory.createLineBorder(Color.BLACK)));
			tf_input.setBorder((BorderFactory.createLineBorder(Color.DARK_GRAY)));

			// if u want color use the examples below
			// ta_chat.setBorder(BorderFactory.createLineBorder(Color.yellow));
			// tf_input.setBorder(BorderFactory.createLineBorder(Color.yellow));

			// BUTTON COLORS

			// Text color in buttons
			btnDc.setForeground(Color.black);
			refresh.setForeground(Color.black);
			b_send.setForeground(Color.black);
			scrollingOff.setForeground(Color.black);
			scrollingOn.setForeground(Color.black);

			// If true background color will be added
			btnDc.setContentAreaFilled(true);
			refresh.setContentAreaFilled(true);
			b_send.setContentAreaFilled(true);

			// Set background color in buttons
			btnDc.setBackground(Color.LIGHT_GRAY);
			refresh.setBackground(Color.LIGHT_GRAY);
			b_send.setBackground(Color.LIGHT_GRAY);
			scrollingOff.setBackground(Color.LIGHT_GRAY);
			scrollingOn.setBackground(Color.LIGHT_GRAY);

		}

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
			refresh.setForeground(Color.white);
			b_send.setForeground(Color.white);
			scrollingOff.setForeground(Color.white);
			scrollingOn.setForeground(Color.white);

			// If true background color will be added
			btnDc.setContentAreaFilled(true);
			refresh.setContentAreaFilled(true);
			b_send.setContentAreaFilled(true);

			// Set background color in buttons
			btnDc.setBackground(Color.GRAY);
			refresh.setBackground(Color.GRAY);
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
			refresh.setForeground(new Color(37, 37, 37));
			b_send.setForeground(new Color(37, 37, 37));
			scrollingOff.setForeground(new Color(37, 37, 37));
			scrollingOn.setForeground(new Color(37, 37, 37));

			// If true background color will be added
			btnDc.setContentAreaFilled(true);
			refresh.setContentAreaFilled(true);
			b_send.setContentAreaFilled(true);

			// Set background color in buttons
			btnDc.setBackground(new Color(48, 95, 17));
			refresh.setBackground(new Color(48, 95, 17));
			b_send.setBackground(new Color(48, 95, 17));
			scrollingOff.setBackground(new Color(48, 95, 17));
			scrollingOn.setBackground(new Color(48, 95, 17));
			scrollingOn.setBorder(null);
			b_send.setBorder(null);
			refresh.setBorder(null);
			btnDc.setBorder(null);
		}
	}

	/**
	 * Hides contentpane when disconnect is clicked
	 */
	public void hideContentPane() {
		contentPane.setVisible(false);
	}

	/**
	 * lets user know if message wasnt sent correctly
	 */
	public void messageNotSent() {
		ta_chat.append("--- Error, not sent ---");
	}

	/**
	 * gets message from textfield input
	 * @return
	 */
	public String getMessage() {
		return tf_input.getText();
	}

	/**
	 * empties the textfield to prepare for next message being sent
	 */
	public void emptyTextField() {
		tf_input.setText(null);
	}

}