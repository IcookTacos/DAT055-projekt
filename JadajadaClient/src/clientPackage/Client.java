package clientPackage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

public class Client {

	// INPUT & OUTPUT STREAM

	public static boolean connected = false;
	private static DataOutputStream dos = null;
	private static DataInputStream dis = null;

	// SCOKET
	public static Socket s = null;

	public Client() {

		new Client_GUI();
		clientInit();

	}

	public void clientInit() {
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

		// Connected to server
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
					Client_GUI.recieveMsg(line);
				}

			}
		});
		th.start();
	}

	public static void logIn(String ip, int portNumber, String userName) {
		{

			connected = true;
			LoginGUI.error.setVisible(true);
			try {
				s = new Socket(ip, portNumber);

				System.err.println(s);

			} catch (UnknownHostException e) {

				e.printStackTrace();
			} catch (IOException e) {

				e.printStackTrace();
			}

			try {
				dis = new DataInputStream(s.getInputStream());
			} catch (IOException e) {
				e.printStackTrace();

			}

			try {
				dos = new DataOutputStream(s.getOutputStream());
			} catch (IOException e) {
				e.printStackTrace();

			}

			try {
				dos.writeUTF("----New client:  " + userName);
				System.err.println("----New client:  " + userName);
			} catch (Exception e) {

			}
		}

	}

	public static void sendMsg(String message, String userName) {

		try {
			dos.writeUTF(userName + ": " + message);
		} catch (IOException e) {

		}

	}

	public static void disconnectMsg(String userName) {
		try {
			dos.writeUTF(userName + ": " + " left the room!");
		} catch (IOException e) {
		}
	}

	public static void killConnection() {

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

	public static boolean authenticate(String userName) {

		// CONTROLLS USER IS NOT NAMED ADMIN
		String check = User.name;
		System.out.println(check);
		if (check.equalsIgnoreCase("ADMIN")) {
			User.validName = false;
			JOptionPane.showMessageDialog(null, "Cannot be named " + User.name, "Bad username",
					JOptionPane.ERROR_MESSAGE);
		}

		// CONTROLLS USENAME IS NOT EMPTY
		if (check.isEmpty()) {
			User.validName = false;
			JOptionPane.showMessageDialog(null, "Username cannot be empty", "Bad username", JOptionPane.ERROR_MESSAGE);
		}

		// CONTROLLS WHITESPACE
		int length = check.length();
		char[] x = check.toCharArray();
		for (int i = 0; i < length; i++) {
			if (x[i] == ' ') {
				User.validName = false;
				JOptionPane.showMessageDialog(null, "Username cannot contain spaces", "Bad username",
						JOptionPane.ERROR_MESSAGE);
			}
		}
		return User.validName;
	}
}
