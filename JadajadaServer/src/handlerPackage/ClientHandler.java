package handlerPackage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import serverPackage.*;




public class ClientHandler extends Thread implements HandlerInterface{

	private DataInputStream dis = null;
	private static ArrayList<Socket> users;
	private static ArrayList<String> serverlog = new ArrayList<String>();


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
		Gui.addTextToServerLog("Client added!"); 
	}

	public void tellEveryone(String message, String name)
	{
		SimpleDateFormat sdf = new SimpleDateFormat("HH.mm");
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		
		serverlog.add(sdf.format(timestamp) + name + " " + message);
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
					Thread.sleep(1000);
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
						sendMsgHistory();
						tellEveryone("has joined room!", name);
						

					}
					else
					{

						tellEveryone(line, name);
						//System.err.println("Tell Everyone!");
						//Server.addTextToServerLog(line);
					}

				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
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
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}


		}


	}
}