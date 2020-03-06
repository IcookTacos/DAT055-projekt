package clientPackage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client extends ClientGUI {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private boolean connected = false;
	private boolean streams = true;

//	INPUT & OUTPUT STREAM
	private static DataOutputStream dos = null;
	private static DataInputStream dis = null;

//	Field for username
	private String userName;

//	SOCKET
	private static Socket s;

	public Client(String internetProtocol, int port, String name, int startTheme) {
		super(startTheme);
		userName = name;

		createActionListeners();
		logIn(internetProtocol, port, userName);
		clientInit();

	}

	/**
	 * Establishers out- putstreams with server
	 */
	private void clientInit() {
		System.out.println("Client is running!");

		// Listening to server
		while (true) {
			if (connected) {
				System.out.println("Connected=" + connected);
				break;
			}
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}

		/**
		 * Threads the inputstream so that messages are always ready to be recieved
		 */
		Thread th = new Thread(() -> {

			while (true) {
				System.out.println("Listening started!");
				String line = null;

				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				try {
					line = dis.readUTF();
				} catch (IOException e) {
					e.printStackTrace();
				}

				if (line != null) {
					this.recieveMsg(line);
				}

			}
		});
		th.start();
	}

	
	/**
	 * Establishes a connection with server
	 */
	private void logIn(String ip, int portNumber, String userName) {
		{

			try {
				s = new Socket(ip, portNumber);
				connected = true;
			} catch (IOException e1) {
				new LoginGUI(true);
				dispose();
				this.dispose();
				e1.printStackTrace();
			}

			System.err.println(s);

			try {
				dis = new DataInputStream(s.getInputStream());
			} catch (IOException e) {
				streams = false;
				e.printStackTrace();
			}

			try {
				dos = new DataOutputStream(s.getOutputStream());
			} catch (IOException e) {
				streams = false;
				e.printStackTrace();
			}

			try {
				dos.writeUTF("----New client:  " + userName);
				System.err.println("----New client:  " + userName);
			} catch (Exception e) {
				streams = false;
			}

			if (!streams) {
				System.err.println("Failed to establish streams");
			}

		}

	}

	/**
	 * Sends message to all users that user has left
	 * @param userName
	 */
	
	private void disconnectMsg(String userName) {
		try {
			dos.writeUTF(userName + " has left the room!");
		} catch (IOException e) {
		}
	}

	/**
	 * Closes socket and streams
	 */
	
	private void killConnection() {

		try {
			dis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			dos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			s.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	/**
	 * sends the message to the server
	 */
	private void sendMsgClicked() {
		String message = this.getMessage();
		if (!message.isEmpty()) {
			try {
				dos.writeUTF(userName + ": " + message);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		this.emptyTextField();
	}

	/**
	 * Turns off the programm
	 */
	private void disconnect() {
		disconnectMsg(userName);
		killConnection();
		this.hideContentPane();
		dispose();
		System.exit(0);
	}

	
	/**
	 * Creates actionlisteners for ClientGUI
	 */
	private void createActionListeners() {

		this.b_send.addActionListener((e) -> sendMsgClicked());
		this.btnDc.addActionListener((e) -> disconnect());
		this.tf_input.addActionListener(e -> sendMsgClicked());

	}
}
