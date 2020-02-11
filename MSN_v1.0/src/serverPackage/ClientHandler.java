package serverPackage;

import java.awt.HeadlessException;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.JOptionPane;




public class ClientHandler extends Thread{

	private Socket clientSocket = null;
	private DataInputStream dis = null;
	private static ArrayList<Socket> users = null;



	private String getIP()
	{

		URL website = null;
		try {
			website = new URL("localhost"); //// http://checkip.amazonaws.com
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(website.openStream()));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			return reader.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}


	public ClientHandler(Socket clientSocket)
	{

		this.clientSocket = clientSocket;

		if(users==null)
			users = new ArrayList<Socket>();

		try
		{
			users.add(clientSocket);
		}
		catch(Exception ex)
		{
			System.out.println("Adding client's socket to socket list failed!");
		}


		try
		{
			dis = new DataInputStream(clientSocket.getInputStream());
		}
		catch(Exception e)
		{

		}

		Server.addTextToServerLog("Trying to add a new client!");
		/*
    try {
        Thread.sleep(5000);
    } catch (InterruptedException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
		 */
		Server.addTextToServerLog("Client added!"); 
	}

	public void tellEveryone(String message, String name)
	{
		for (Socket socket : users) {
			Socket s = socket;

			try {
				DataOutputStream dos = new DataOutputStream(s.getOutputStream());
				dos.writeUTF(name + " " + message);

				//dos.flush();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				System.err.println("Skickades ej");
				e1.printStackTrace();
			}
		}
	}

	public void disconnect()
	{

	}


	// reciving messages from a specific client and send to everyone else
	public void run()
	{   
		while(true)
		{
			String line = null;

			try {
				System.out.println("Reading client input");

				//Server.addTextToServerLog("-line-");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				line = dis.readUTF();
				Server.addTextToServerLog(line);

				if(line!=null)
				{
					String name = "";
					if(line.startsWith("/name "))
					{
						name = line.replaceFirst("/name", "");
						tellEveryone(" has joined room!", name);
						Server.addTextToServerLog(name + " has joined room!");  //Skriver till server log
					}
					else
					{
						
						tellEveryone(line, name);
						System.err.println("Tell Everyone!");
						//Server.addTextToServerLog(line);
					}

				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
			}   
			


		}



	}
}