package serverPackage;

public interface ServerInterface {

	public void run();
	
	static String getIp() {return null;}	
	
	static void serverStart() {}		
	static void createSocket() {}		
	static void runClientHandler() {}	
	static void shutDown() {}
	
	
}
