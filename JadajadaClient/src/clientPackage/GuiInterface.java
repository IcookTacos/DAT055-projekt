package clientPackage;

public interface GuiInterface {
	
	
	//Methods for Gui
	public void windowInit();
	public void interfaceInit();
	public void createActionListerners();
	
	public void setColorScheme(int x1);
	
	static void recieveMsg(String[] s1) {}
	
	
	//Methods for buttons
	public void disconnect();
	public void setAutoScroll();
	public void sendMsgClicked();
	

}
