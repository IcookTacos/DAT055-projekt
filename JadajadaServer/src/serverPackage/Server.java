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

public class Server implements ServerInterface{

	public static int portNumber = 2309;

	private static ServerSocket ss = null;
	private static Socket socket = null;



	public Server() 
	{
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new Gui();
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
	
	


	@Override
	public void run() {

		try {
			ss = new ServerSocket(portNumber);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
		}


	}

	public static  String getIp() {
		URL site = null;
		try {

			site = new URL("http://checkip.amazonaws.com");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {

			new BufferedReader(new InputStreamReader(site.openStream()));
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







	public static void createSocket(int port) {
		try {

			ss = new ServerSocket(port);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
		}

	}



	public static void runClientHandler() {

		while(true)
		{

			try {

				socket = ss.accept();
			} catch (IOException e) {
				// TODO Auto-generated catch block

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
