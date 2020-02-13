package serverPackage;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Server extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tf_input;

	private static int portNumber = 2309;
	private static JTextArea ta_ServerLog;
	private JTextField tf_IP;
	private JButton btnRefresh;
	private JTextField textField;
	private JLabel lblPort;
	private static ServerSocket ss = null;

	/**
	 * Launch the application.
	 */

	public static void addTextToServerLog(String text)
	{
		if(text.equals(null))
			return;
		else
			ta_ServerLog.setText(ta_ServerLog.getText().trim() + "\n" + text.trim());
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Server frame = new Server();
					frame.setVisible(true);
				} catch (Exception e) {

				}
			}
		});


		try {
			ss = new ServerSocket(portNumber);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
		}


		while(true)
		{
			Socket s = null;
			try {
				s = ss.accept();
			} catch (IOException e) {
				// TODO Auto-generated catch block

			}

			System.out.println("Creating new ClientListener!");
			new ClientHandler(s).start();
		}

	}

	/**
	 * Create the frame.
	 */
	public Server() {
		setTitle("Server");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 377);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		tf_input = new JTextField();
		tf_input.setBounds(125, 209, 109, 20);
		contentPane.add(tf_input);
		tf_input.setColumns(10);

		JButton b_send = new JButton("Send");
		b_send.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		b_send.setBounds(278, 209, 86, 20);
		contentPane.add(b_send);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(49, 26, 294, 136);
		contentPane.add(scrollPane);

		ta_ServerLog = new JTextArea();
		ta_ServerLog.setEditable(false);
		scrollPane.setViewportView(ta_ServerLog);

		JLabel lblYouAreHosting = new JLabel("Your IP:");
		lblYouAreHosting.setBounds(49, 277, 44, 14);
		contentPane.add(lblYouAreHosting);

		tf_IP = new JTextField();
		tf_IP.setEditable(false);
		tf_IP.setBounds(96, 274, 103, 20);
		contentPane.add(tf_IP);
		tf_IP.setColumns(10);

		btnRefresh = new JButton("Refresh IP");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tf_IP.setText(getIP());
			}
		});
		btnRefresh.setBounds(212, 273, 89, 23);
		contentPane.add(btnRefresh);

		textField = new JTextField();
		textField.setText(Integer.toString(portNumber));
		textField.setColumns(10);
		textField.setBounds(96, 305, 103, 20);
		contentPane.add(textField);

		lblPort = new JLabel("Port:");
		lblPort.setBounds(49, 308, 44, 14);
		contentPane.add(lblPort);

		JButton btnNewButton = new JButton("dc");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ss = null;
				System.exit(0);
			}
		});
		btnNewButton.setBounds(10, 185, 51, 23);
		contentPane.add(btnNewButton);
	}

	private String getIP()
	{
		URL site = null;
		try {
			site = new URL("http://checkip.amazonaws.com");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		@SuppressWarnings("unused")
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(site.openStream()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try
		{
			return InetAddress.getLocalHost().getHostAddress().toString();
			//return reader.readLine();
		}
		catch(Exception e)
		{

		}

		return null;
	}
}
