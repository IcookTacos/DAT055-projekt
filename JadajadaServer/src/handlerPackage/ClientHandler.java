package handlerPackage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import serverPackage.*;




public class ClientHandler extends Thread{

	private DataInputStream dis = null;
	private static ArrayList<Socket> users = null;
	//private String[] serverlog;
	//private static int logtracker = 0;


	public ClientHandler(Socket clientSocket)
	{

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

		Gui.addTextToServerLog("Trying to add a new client!");
		/*
    try {
        Thread.sleep(500);
    } catch (InterruptedException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
		 */ 
		Gui.addTextToServerLog("Client added!"); 
	}

	public void tellEveryone(String message, String name)
	{
		SimpleDateFormat sdf = new SimpleDateFormat("HH.mm");
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		
		
		for (Socket socket : users) {
			Socket s = socket;

			try {
				
				
				DataOutputStream dos = new DataOutputStream(s.getOutputStream());
				dos.writeUTF(sdf.format(timestamp) + name + " " + message);

				dos.flush();
				
				//serverlog[logtracker]=(message + name);
				//logtracker++;
			} catch (IOException e1) {
				System.err.println("Skickades ej");
				e1.printStackTrace();
			}
		}
	}




	// reciving messages from a specific client and send to everyone else
	public void run()
	{   
		while(true)
		{
			String line = null;

			try {
				System.out.println("Reading client input");

				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				line = dis.readUTF();
				Gui.addTextToServerLog(line);

				if(line!=null)
				{
					
					String name = "";
					if(line.startsWith("----New client: "))
					{
						name = line.replaceFirst("----New client: ", "");
						tellEveryone("has joined room!", name);
						Gui.addTextToServerLog(name + " has joined room!");  //Skriver till server log
						
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