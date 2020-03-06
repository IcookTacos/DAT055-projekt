package clientPackage;

import java.awt.Component;
import java.awt.Color;
import java.awt.Graphics;



public class DrawingComponent extends Component {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void paint(Graphics g) {
		
		// RED BOXES
		for (int i = 0; i < 14; i++) {
			g.setColor(Color.RED.brighter());
			g.fillRect(i * 20, 2, i - i * 2, 7);
		}

	}
}