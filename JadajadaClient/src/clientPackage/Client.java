package clientPackage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client implements ClientInterface {

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
				Thread.sleep(5);
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
					Thread.sleep(500);
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
				Client_GUI.ta_chat.append("dis init failed\n");
			}

			try {
				dos = new DataOutputStream(s.getOutputStream());
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("DataOutputStraem object init failed!");
				Client_GUI.ta_chat.append("dos init failed\n");
			}

			try {
				dos.writeUTF("----New client:  " + userName);
				System.err.println("----New client:  " + userName);
			} catch (Exception e) {
				Client_GUI.ta_chat.append("dos.writeUTF() failed \n");
			}
		}

	}

	public static void sendMsg(String message, String userName) {

		try {
			// ta_chat.append("writing UTF" + '\n');
			dos.writeUTF(userName + ": " + message);
			// ta_chat.append(ta_chat.getText().trim() + "writing UTF succeded" + '\n');
		} catch (IOException e) {
			Client_GUI.ta_chat.append(Client_GUI.ta_chat.getText().trim() + "writing UTF failed" + '\n');
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

}