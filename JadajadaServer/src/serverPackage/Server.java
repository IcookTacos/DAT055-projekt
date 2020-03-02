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
import java.util.Scanner;



public class Server implements ServerInterface {

	public static int portNumber = 2309;
	private static ServerSocket ss = null;
	private static Socket socket = null;
	Scanner scanner = new Scanner(System.in);

	public Server() {
		serverInit();
		
	}

	
	private void serverInit() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
									
					System.out.println("Server Online ...");
				} catch (Exception e) {

				}
			}
		});

		try {
			ss = new ServerSocket(portNumber);
		} catch (IOException e1) {

		}
		Thread th = new Thread(() -> {

			while (true) {
				String command = scanner.nextLine();
				adminCommands(command);
				System.err.print(command);
				command = "";

			}
		});
		th.start();
		while (true) {
			
			//String command = scanner.nextLine();
			//adminCommands(command);
			//System.err.print(command);
			//command = "";
			
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
	
	
	@SuppressWarnings("unused")
	private void adminCommands(String adminInput) {
		int errorFlag = 1;
		
		
		
		if ((adminInput.compareTo("/disconnect")) == 0) {
			Server.shutDown();
			errorFlag = 0;
		}
		if ((adminInput.compareTo("/help") == 0)) {
			adminHelp();
			errorFlag = 0;

		}

		if ((adminInput.compareTo("/latencySLOW")) == 0) {
			setLatencySLOW();
			errorFlag = 0;

		}

		if ((adminInput.compareTo("/latencyFAST")) == 0) {
			setLatencyFAST();
			errorFlag = 0;

		}

		if ((adminInput.compareTo("/latencyNORMAL")) == 0) {
			setLatencyNORMAL();
			errorFlag = 0;

		}

		if ((adminInput.startsWith("/tellAll"))) {
			String line = adminInput.replaceFirst("/tellAll", "");
			ClientHandler.tellEveryone(line, " Admin:");
			errorFlag = 0;

		}

		if ((errorFlag == 1)) {
			System.out.println("INVALID COMMAND\nType /help for all commands");

		}

	}
	private void adminHelp() {

		System.out.println(
				"/disconnect = terminate the server\n/latencySLOW = slows down the server \n/latencyFAST = speeds up the server\n /tellAll <msg> sends message to all clients");
	}

	private void setLatencySLOW() {
		ClientHandler.latency = 5000;
		ClientHandler.tellEveryone("SERVER SET TO SLOW", " Admin:");
	}

	private void setLatencyFAST() {
		ClientHandler.latency = 5;
		ClientHandler.tellEveryone("SERVER SET TO FAST", " Admin:");
	}

	private void setLatencyNORMAL() {
		ClientHandler.latency = 50;
		ClientHandler.tellEveryone("SERVER SET TO FAST", " Admin:");
	}

	
}
