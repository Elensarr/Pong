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
import java.awt.Stroke;
import java.awt.BasicStroke;


public class PongPanel extends JPanel implements ActionListener, KeyListener {
// ==== CONTATNTS ====
	public static final Color BACKGROUND_COLOUR = Color.BLACK;
	public static final int TIMER_DELAY = 5;

// === VARIABLES ====
	// ball variable
	Ball ball;
	// This variable will be set to true when the game has finished 
	// Initialization and the objects have been successfully created.
	boolean gameInitialised = false;
	
	
	
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
		repaint();
		
	}
	
	// Constructor
	public PongPanel() {
			setBackground(BACKGROUND_COLOUR);
			Timer timer = new Timer(TIMER_DELAY, this);
			timer.start();
	} // closes constructor
	
	// method for updating the object positions and run our game logic
	
	// The reason we use the createObjects() method and call it from update()
	// rather than creating the objects inside the constructor is that we do not 
	// have access to the width and height of the panel yet. These two values
	// are needed to correctly create the ball and paddles. It is not until the 
	// constructor completes that the panel has been properly initialised and has 
	// a width and height. To avoid this, we instead initialise our objects on our first update() call.
	private void update() {
		if (gameInitialised == false) {
			createObjects();
			gameInitialised = true;
		}
		
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		paintDottedLine(g);
		if (gameInitialised) {
			paintSprite(g, ball);
		}
	}
	
	private void paintDottedLine(Graphics g) {
		Graphics2D g2d = (Graphics2D) g.create();
		Stroke dashed = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[] {9},0);
		g2d.setStroke(dashed);
		g2d.setPaint(Color.WHITE);
		g2d.drawLine(getWidth() / 2, 0, getWidth() / 2, getHeight());
		g2d.dispose();
		
	}
	
	// creates Ball object
	private void createObjects() {
		ball = new Ball(getWidth(), getHeight());
	}
	
	private void paintSprite(Graphics g, Sprite sprite) {
		g.setColor(sprite.get_color());
		g.fillRect(sprite.get_xPosition(), sprite.get_yPosition(), sprite.get_width(), sprite.get_height());
	}
	
}// closes PongPanel class

