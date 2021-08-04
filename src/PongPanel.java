/**
 * 
 */

/**
 * @author nkhal
 *
 */


import javax.swing.JPanel;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.Timer;

//import the graphics classes
import java.awt.Graphics;
import java.awt.Graphics2D;


public class PongPanel extends JPanel implements ActionListener, KeyListener {

	public static final Color BACKGROUND_COLOUR = Color.BLACK;
	public static final int TIMER_DELAY = 5;
	
	@Override
	public void keyTyped(KeyEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		update();
		
	}
	
	// Constructor
	public PongPanel() {
			setBackground(BACKGROUND_COLOUR);
			Timer timer = new Timer(TIMER_DELAY, this);
			timer.start();
	} // closes constructor
	
	// method for updating the object positions and run our game logic
	private void update() {
		
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	    g.setColor(Color.WHITE);
	    g.fillRect(20, 20, 100, 100);
	}
	
}// closes PongPanel class

