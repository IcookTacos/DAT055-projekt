package msn_GUI;
import java.awt.Component;
import java.util.concurrent.TimeUnit;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Random;

@SuppressWarnings({ "unused", "serial" })

public class DrawingComponent extends Component {

	
	
	public void paint(Graphics g) { 
		int increment = 0;
		
		
		// RED BOXES
		for(int i = 0; i<14;i++) {
			//delay5mili();
			//System.out.println(i);
			Graphics2D g2 = (Graphics2D)g;
			g.setColor(Color.RED.brighter());
			g.fillRect(i*20,2,i-i*2,7);
			}
		
		
		/*for(int i=2;i<15;i++) {
			Graphics2D g2 = (Graphics2D)g;
			g.setColor(Color.BLUE);
			g.fillRect(i*30,0,10,10);
			System.out.print("BLUE="+ i +"\n");	
		}*/
			
		}
	
	
	// Delay function (5 miliseconds) // Find other solution
	private void delay5mili() {
		try {
			Thread.sleep(1000);
		} catch(InterruptedException  ex) {
			Thread.currentThread().interrupt();
		}
	}
	
	
	
	}
	
