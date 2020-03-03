package serverPackage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;


public class ClientHandler extends Thread {

	private DataInputStream dis = null;
	private static ArrayList<Socket> users = null;
	private static ArrayList<String> serverlog = new ArrayList<String>();
	public static int latency=1;

	public ClientHandler(Socket clientSocket) {

		if (users == null)
			users = new ArrayList<Socket>();

		try {
			users.add(clientSocket);
		} catch (Exception ex) {
			System.out.println("Adding client's socket to socket list failed!");
		}

		try {
			dis = new DataInputStream(clientSocket.getInputStream());
		} catch (Exception e) {

		}

		System.out.println("Trying to add a new client!");
		System.out.println("Client added!");
	}

	public static void tellEveryone(String message, String name) {
		SimpleDateFormat sdf = new SimpleDateFormat("HH.mm");
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		serverlog.add(sdf.format(timestamp) + name + " " + message);

		for (Socket socket : users) {
			Socket s = socket;

			try {

				DataOutputStream dos = new DataOutputStream(s.getOutputStream());
				dos.writeUTF(sdf.format(timestamp) + name + " " + message);
				dos.flush();
			} catch (IOException e1) {
				System.err.println("Skickades ej");
				e1.printStackTrace();
			}
		}
	}

	// Receive message from client and send to all clients
	public void run() {
		
		while (true) {
			String line = null;

			try {
				
				try {
					Thread.sleep(latency);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				line = dis.readUTF();
				System.out.println(line);

				if (line != null) {

					String name = "";
					if (line.startsWith("----New client: ")) {
						name = line.replaceFirst("----New client: ", "");
						sendMsgHistory();
						tellEveryone("has joined room!", name);
						System.out.println(name + " has joined room!"); // Skriver till server log

					} else {

						tellEveryone(line, name);
						
					}

				}
			} catch (IOException e) {
			}

		}

	}

	public void sendMsgHistory() {

		Socket index = users.get(users.size() - 1);

		for (String s1 : serverlog) {

			try {

				DataOutputStream dos = new DataOutputStream(index.getOutputStream());
				dos.writeUTF(s1);
				dos.flush();

			} catch (IOException e1) {
				e1.printStackTrace();

			}

		}
	}

}