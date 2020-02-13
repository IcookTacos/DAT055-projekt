package clientPackage;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

@SuppressWarnings("serial")


public class Client extends JFrame{


	public static void main(String[] args) {
		GUI frame = new GUI();
		frame.setVisible(true);
		System.out.println("Client is running!");

		frame.Refresh.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				if(GUI.autoScroll == 1)
				{
					GUI.autoScroll = 0;
				} else
				{
					GUI.autoScroll = 1;
				}
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

		// SEND BUTTON
		frame.b_send.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!frame.connected)
				{
					GUI.ta_chat.append("Not connected\n");		
					return;
				}
				
				String message = frame.tf_input.getText();
				try {
					//ta_chat.append("writing UTF" + '\n');
					frame.dos.writeUTF(frame.userName + ": "+ message);
					//ta_chat.append(ta_chat.getText().trim() + "writing UTF succeded" + '\n');
				} catch (IOException e) {
					GUI.ta_chat.append(GUI.ta_chat.getText().trim() + "writing UTF failed" + '\n');
				}

				frame.tf_input.setText(null);
			}
		});
		
		// CONNECT ACTION
		frame.b_connect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {

				frame.connected = true;

				String ip = frame.tf_ip.getText();
				frame.userName = frame.tf_name.getText();
				int portNumber = Integer.parseInt(frame.tf_port.getText());

				try {
					GUI.ta_chat.append("");
					frame.s = new Socket(ip, portNumber);
					GUI.ta_chat.append("Connected!");
					GUI.ta_chat.append("s=" + frame.s + "\n");  // Skrivs till chatten [Socket-Information]
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
					GUI.ta_chat.append("dis init failed\n");
				}

				try {
					frame.dos = new DataOutputStream(frame.s.getOutputStream());
				} catch (IOException e) {
					e.printStackTrace();
					System.out.println("DataOutputStraem object init failed!");
					GUI.ta_chat.append("dos init failed\n");
				}

				try
				{
					frame.dos.writeUTF("/name " + frame.userName);
					System.err.println("/name " + frame.userName);
				}
				catch(Exception e)
				{
					GUI.ta_chat.append("dos.writeUTF() failed \n");
				}
			}
		});

		// LISTENING TO SERVER
		while(!frame.connected)
		{
			//System.err.println("Waiting...!");
		}

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
				GUI.ta_chat.append(line + "\n");
				if(GUI.autoScroll == 1) {
					GUI.ta_chat.setCaretPosition(GUI.ta_chat.getDocument().getLength());
				}
			}

		}
	}


}
