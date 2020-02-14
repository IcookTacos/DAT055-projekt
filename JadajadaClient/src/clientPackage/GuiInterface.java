package clientPackage;

public interface GuiInterface {
	
	
	//Methods for Gui
	public void createMainWindow();
	public void createClientName();
	public void createBtns();
	public void createTxtArea();
	public void createTxtFields();
	public void createActionListerners();
	
	public void setColorScheme(int x1);
	
	static void recieveMsg(String[] s1) {}
	
	
	//Methods for buttons
	public void disconnect();
	public void setAutoScroll();
	//public void logInClicked();
	public void sendMsgClicked();
	void scrollingLabel();
	

}
