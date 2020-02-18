package serverPackage;

import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;

import handlerPackage.ClientHandler;

public class Server implements ServerInterface {

	public static int portNumber = 2309;
	private static ServerSocket ss = null;
	private static Socket socket = null;

	public Server() {
		serverInit();
	} 

	public static void main(String args[]) {
		
		new Server();
	}

	private static void serverInit() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new Gui();
					Gui.addTextToServerLog("\nServer Online ...");
				} catch (Exception e) {

				}
			}
		});

		try {
			ss = new ServerSocket(portNumber);
		} catch (IOException e1) {

		}
		while (true) {
			Socket s = null;
			try {
				s = ss.accept();
			} catch (IOException e) {

			}

			System.out.println("Creating new ClientListener!");
			new ClientHandler(s).start();
		}
	}

	@Override
	public void run() {

		try {
			ss = new ServerSocket(portNumber);
		} catch (IOException e1) {

		}

	}

	public static String getIp() {
		URL site = null;
		try {

			site = new URL("http://checkip.amazonaws.com");
		} catch (MalformedURLException e) {

			e.printStackTrace();
		}

		try {

			new BufferedReader(new InputStreamReader(site.openStream()));
		} catch (IOException e) {

			e.printStackTrace();
		}

		try {

			return InetAddress.getLocalHost().getHostAddress().toString();
		} catch (Exception e) {

		}

		return null;

	}

	public static void createSocket(int port) {
		try {

			ss = new ServerSocket(port);
		} catch (IOException e1) {

		}

	}

	public static void runClientHandler() {

		while (true) {

			try {

				socket = ss.accept();
			} catch (IOException e) {

			}

			System.out.println("Creating new ClientListener!");

			new ClientHandler(socket).start();

		}

	}

	public static void shutDown() {

		ss = null;
		System.exit(0);

	}

}
