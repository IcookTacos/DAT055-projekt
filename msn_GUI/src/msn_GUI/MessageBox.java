package msn_GUI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import javax.swing.*;

@SuppressWarnings("serial")

public class MessageBox extends JTextField implements ActionListener, KeyListener {
	
	public MessageBox() {
		setBounds(25,419,300,20);
		addKeyListener(this);

	}
	
	public void sendMSG() {
		
		global.userOUTmessages = getText();
		System.out.println(global.userOUTmessages);
		setText("");
		
		
	}
	
	public void recieveMSG() {
		SimpleDateFormat sdf = new SimpleDateFormat("HH.mm");
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		GUIChat.chatWindow.append(sdf.format(timestamp) + " " + global.userName + ": " + global.userOUTmessages + "\n");
	}
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
		if(code == KeyEvent.VK_ENTER) { sendMSG(); recieveMSG(); }
	}
	
	@Override
	public void keyTyped(KeyEvent e) {}
	@Override
	public void keyReleased(KeyEvent e) {}

}
