package clientPackage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client{
	

	public static void main(String[] args) {
		Client_GUI frame = new Client_GUI();	
		frame.setVisible(true);
		
		System.out.println("Client is running!");
		
		
		// CONNECT BUTTON
		frame.b_connect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {

				frame.connected = true;
				String ip = frame.tf_ip.getText();
				frame.userName = frame.tf_name.getText();
				int portNumber = Integer.parseInt(frame.tf_port.getText());

				try {
					frame.s = new Socket(ip, portNumber);
					Client_GUI.ta_chat.append("Connected!");
					Client_GUI.ta_chat.append("s=" + frame.s + "\n");  // Skrivs till chatten [Socket-Information]
					System.err.println(frame.s);

				} catch (UnknownHostException e) {
					
					e.printStackTrace();
				} catch (IOException e) {
					
					e.printStackTrace();
				}

				try {
					frame.dis = new DataInputStream(frame.s.getInputStream());
				} catch (IOException e) {
					e.printStackTrace();
					Client_GUI.ta_chat.append("dis init failed\n");
				}

				try {
					frame.dos = new DataOutputStream(frame.s.getOutputStream());
				} catch (IOException e) {
					e.printStackTrace();
					System.out.println("DataOutputStraem object init failed!");
					Client_GUI.ta_chat.append("dos init failed\n");
				}

				try
				{
					frame.dos.writeUTF("----New client:  " + frame.userName);
					System.err.println("----New client:  " + frame.userName);
				}
				catch(Exception e)
				{
					Client_GUI.ta_chat.append("dos.writeUTF() failed \n");
				}
			}
		});
		
		// SEND MESSAGE BUTTON
		frame.b_send.addActionListener(new ActionListener() {
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent arg0) {
				if(!frame.connected)
				{
					frame.ta_chat.append("Not connected \n");
					return;
				}

				String message = frame.tf_input.getText();

				try {
					//ta_chat.append("writing UTF" + '\n');
					frame.dos.writeUTF(frame.userName + ": "+ message);
					//ta_chat.append(ta_chat.getText().trim() + "writing UTF succeded" + '\n');
				} catch (IOException e) {
					Client_GUI.ta_chat.append(Client_GUI.ta_chat.getText().trim() + "writing UTF failed" + '\n');
				}

				frame.tf_input.setText(null);
			}
		});
		
		// DISCONNECT BUTTON
		frame.btnDc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					frame.dis.close();
				} catch (IOException e) {
					
					e.printStackTrace();
				}
				try {
					frame.dos.close();
				} catch (IOException e) {
					
					e.printStackTrace();
				}

				try {
					frame.s.close();
				} catch (IOException e) {
					
					e.printStackTrace();
				}
				frame.contentPane.setVisible(false);
				frame.dispose();
				System.exit(0);

			}
		});
		
		// REFRESH BUTTON
		frame.Refresh.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				if(Client_GUI.autoScroll == 1)
				{
					Client_GUI.autoScroll = 0;
				} else
				{
					Client_GUI.autoScroll = 1;
				}
			}
		});

		
		
		//Listening to server
		while(true)
		{
			if(frame.connected)
				break;
			try {
				Thread.sleep(150);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		//Connected to server
		while(true)
		{
			System.out.println("Listening started!");
			String line = null;


			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			try {
				line = frame.dis.readUTF();
			} catch (IOException e) {
				e.printStackTrace();
			}


			if(line!=null)
			{
				Client_GUI.ta_chat.append(line + "\n");
				if(Client_GUI.autoScroll == 1) {
					Client_GUI.ta_chat.setCaretPosition(Client_GUI.ta_chat.getDocument().getLength());
				}
			}
			
		}
	}
}