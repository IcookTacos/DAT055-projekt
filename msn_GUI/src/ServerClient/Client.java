package ServerClient;
import msn_GUI.*;

import java.net.*;
import java.io.*; 
  
@SuppressWarnings("unused")
public class Client
{ 
    // initialize socket and input output streams 
    private Socket socket            = null; 
    private DataInputStream  input   = null; 
    private DataOutputStream out     = null; 
  
    
    // constructor to put ip address and port 
  
	@SuppressWarnings("deprecation")
	public Client(String address, int port) 
    { 
        // establish a connection 
        try
        { 
            socket = new Socket(address, port); 
            System.out.println("Connected"); 
  
            // takes input from terminal 
            input  = new DataInputStream(System.in); 
  
            // sends output to the socket 
            out    = new DataOutputStream(socket.getOutputStream()); 
        } 
        catch(UnknownHostException u) 
        { 
            System.out.println(u); 
        } 
        catch(IOException i) 
        { 
            System.out.println(i); 
        }
       
        /*
        
        while(global.loggedIn==true) {
        	if(global.messageFlag==1) {
        	System.out.println(global.userOUTmessages);
       		global.messageFlag=0;
        	}
        }
    	
    	*/
    
        // string to read message from input 
  
        // keep reading until "Over" is input 
        Thread th = new Thread(() -> {
        	String line = "";
        	while (!line.equals("Over")) 
            { 
            	
                try
                { 
                    line = input.readLine();
                    out.writeUTF(line); 
                } 
                catch(IOException i) 
                { 
                    System.out.println(i); 
                }
            } 
        	try
            { 
                input.close(); 
                out.close(); 
                socket.close(); 
            } 
            catch(IOException i) 
            { 
                System.out.println(i); 
            } 
        });
        
        th.start();
        
  
        // close the connection 
        
    } 
  
    public static void main(String args[]) 
    { 
    	Client client = new Client("127.0.0.1", 5000); 
    }
} 

