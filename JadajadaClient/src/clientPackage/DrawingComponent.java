package clientPackage;

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
		for (int i = 0; i < 14; i++) {
			Graphics2D g2 = (Graphics2D) g;
			g.setColor(Color.RED.brighter());
			g.fillRect(i * 20, 2, i - i * 2, 7);
		}

	}
}