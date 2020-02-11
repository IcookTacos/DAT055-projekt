package clientPackage;
import javax.swing.JFrame;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.DefaultCaret;

import serverPackage.Server;

import javax.swing.JTextArea;

import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import java.net.Socket;
import java.net.UnknownHostException;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;


public class Client extends JFrame{

	private JPanel contentPane;
	private JTextField tf_input;
	private static JTextArea ta_chat;
	private JButton b_connect;


	private JScrollPane scrollPane;
	private JTextField tf_ip;


	private Socket s = null;
	private DataOutputStream dos = null;
	private DataInputStream dis = null;
	private JTextField tf_port;


	private boolean connected = false;
	private JLabel lblIp;
	private JLabel lblPort;
	private JLabel lblName;
	private JTextField tf_name;
	private JButton btnDc;
	private JButton Refresh;
	private static int autoScroll = 0;

	private String userName;

	String received = null;


	/**
	 * Launch the application.
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) {

		Client frame = new Client();
		frame.setVisible(true);


		System.out.println("Client is running!");


		// TODO Auto-generated method stub



		//Listening to server

		while(!frame.connected)
		{

			System.err.println("Waiting...!");

		}

		while(true)
		{
			System.out.println("Listening started!");
			String line = null;


			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {
				line = frame.dis.readUTF();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


			if(line!=null)
			{
				ta_chat.append(line + "\n");
				if(autoScroll == 1) {
					ta_chat.setCaretPosition(ta_chat.getDocument().getLength());
				}
			}

		}
	}

	//


	/**
	 * Create the frame.
	 */
	public Client() {
		setTitle("Client");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 530, 458);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton b_send = new JButton("Send");
		b_send.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!connected)
				{
					JOptionPane.showMessageDialog(null, "You have to connect first!");
					return;
				}

				String message = tf_input.getText();

				try {
					//ta_chat.append("writing UTF" + '\n');
					dos.writeUTF(userName + ": "+ message);
					//ta_chat.append(ta_chat.getText().trim() + "writing UTF succeded" + '\n');
				} catch (IOException e) {
					// TODO Auto-generated catch block
					ta_chat.append(ta_chat.getText().trim() + "writing UTF failed" + '\n');
				}

				tf_input.setText(null);
			}
		});
		b_send.setBounds(375, 204, 89, 23);
		contentPane.add(b_send);

		tf_input = new JTextField();
		tf_input.setBounds(89, 205, 258, 20);
		contentPane.add(tf_input);
		tf_input.setColumns(10);

		/*
		 * Test
		 */



		scrollPane = new JScrollPane();
		scrollPane.setBounds(39, 11, 452, 175);
		contentPane.add(scrollPane);

		ta_chat = new JTextArea();
		scrollPane.setViewportView(ta_chat);
		ta_chat.setEditable(false);

		b_connect = new JButton("Connect");
		b_connect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {

				connected = true;

				String ip = tf_ip.getText();
				userName = tf_name.getText();
				int portNumber = Integer.parseInt(tf_port.getText());

				try {
					s = new Socket(ip, portNumber);
					ta_chat.append("Connected!");
					ta_chat.append("s=" + s + "\n");  // Skrivs till chatten [Socket-Information]
					System.err.println(s);

				} catch (UnknownHostException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				try {
					dis = new DataInputStream(s.getInputStream());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					ta_chat.append("dis init failed\n");
				}

				try {
					dos = new DataOutputStream(s.getOutputStream());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("DataOutputStraem object init failed!");
					ta_chat.append("dos init failed\n");
				}

				try
				{
					dos.writeUTF("/name " + userName);
					System.err.println("/name " + userName);
				}
				catch(Exception e)
				{
					ta_chat.append("dos.writeUTF() failed \n");
				}
			}
		});
		b_connect.setBounds(89, 373, 89, 23);
		contentPane.add(b_connect);

		tf_ip = new JTextField();
		tf_ip.setBounds(83, 269, 123, 20);
		contentPane.add(tf_ip);
		tf_ip.setColumns(10);

		tf_port = new JTextField();
		tf_port.setColumns(10);
		tf_port.setBounds(83, 300, 123, 20);
		contentPane.add(tf_port);

		lblIp = new JLabel("IP:");
		lblIp.setBounds(39, 272, 46, 14);
		contentPane.add(lblIp);

		lblPort = new JLabel("Port");
		lblPort.setBounds(39, 303, 46, 14);
		contentPane.add(lblPort);

		lblName = new JLabel("Name:");
		lblName.setBounds(39, 335, 46, 14);
		contentPane.add(lblName);

		tf_name = new JTextField();
		tf_name.setColumns(10);
		tf_name.setBounds(83, 332, 123, 20);
		contentPane.add(tf_name);


		tf_ip.setText("localhost");
		tf_port.setText("2309");
		tf_name.setText("Axel");


		btnDc = new JButton("dc");
		btnDc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					dis.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					dos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				try {
					s.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				contentPane.setVisible(false);
				dispose();
				System.exit(0);

			}
		});
		btnDc.setBounds(290, 373, 89, 23);
		contentPane.add(btnDc);


		Refresh = new JButton("Refresh Chat");
		Refresh.setBounds(290, 340, 89, 23);
		Refresh.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				if(autoScroll == 1)
				{
					autoScroll = 0;
				} else
				{
					autoScroll = 1;
				}
			}
		});

		contentPane.add(Refresh);
		/*
		Thread th = new Thread(() -> {
				while(true)
				{
					String line = null;

					try {
						System.out.println("Reading server output");

						//Server.addTextToServerLog("-line-");
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						line = dis.readUTF();
						ta_chat.append(line + "\n");
/*
						if(line!=null)
						{
							String name = "";
							if(line.startsWith("/name "))
							{
								//name = line.replaceFirst("/name", "");
								//tellEveryone(" has joined room!", name);
								//Server.addTextToServerLog(name + " has joined room!");  //Skriver till server log
							}
							else
							{

								//tellEveryone(line, name + " ");
								//System.err.println("Tell Everyone!");
								//Server.addTextToServerLog(line);
							}

						}
///////////////
					} catch (IOException e) {
						// TODO Auto-generated catch block
					} 
				}
				});

				th.start();
		 */




	}
}
