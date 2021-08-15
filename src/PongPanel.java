/**
 * 
 */

/**
 * @author nkhal
 *
 */


import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Font;
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
	public static final Color BACKGROUND_COLOUR = Color.WHITE;
	public static final int TIMER_DELAY = 5;
	public static final int BALL_MOVEMENT_SPEED = 2;
	public static final int PADDLE_MOVEMENT_SPEED = 3;
	//how many points a player must have to win the game
	public static final int POINTS_TO_WIN = 11;
	public static final int SCORE_X_PADDING = 100;
	public static final int SCORE_Y_PADDING = 100;
	public static final int SCORE_FONT_SIZE = 50;
	public static final String SCORE_FONT_TYPE = "Serif";
	// message 'You won'
	public static final String WINNING_MESSAGE = "YOU WON!";
	public static final int WIN_X_PADDING = 100;
	public static final int WIN_Y_PADDING = 150;
	public static final int WIN_FONT_SIZE = 50;
	

	
	
	
	
	//hold the current score for each player
	int player1Score = 0, player2Score = 0;
	//hold the player who has won
	Player gameWinner;

// === VARIABLES ====
	// ball variable
	Ball ball;
	// This variable will be set to true when the game has finished 
	// Initialization and the objects have been successfully created.
	Paddle paddle1, paddle2;
	
	GameState gameState = GameState.Initialising;
	
	
	
	@Override
	public void keyTyped(KeyEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent event) {
		if (event.getKeyCode() == KeyEvent.VK_UP) {
			paddle2.set_yVelocity(-PADDLE_MOVEMENT_SPEED);
		}
		else if (event.getKeyCode() == KeyEvent.VK_DOWN) {
			paddle2.set_yVelocity(PADDLE_MOVEMENT_SPEED);
		}
		if (event.getKeyCode() == KeyEvent.VK_W) {
			paddle1.set_yVelocity(-PADDLE_MOVEMENT_SPEED);
		}
		else if (event.getKeyCode() == KeyEvent.VK_S) {
			paddle1.set_yVelocity(PADDLE_MOVEMENT_SPEED);
		}
		
	}

	@Override
	public void keyReleased(KeyEvent event) {
		if(event.getKeyCode() == KeyEvent.VK_UP || event.getKeyCode() == KeyEvent.VK_DOWN) {
			paddle2.set_yVelocity(0);
		}
		if(event.getKeyCode() == KeyEvent.VK_W || event.getKeyCode() == KeyEvent.VK_S) {
			paddle1.set_yVelocity(0);
		}
		
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
			// add the KeyListener to the panel 
			addKeyListener(this);
			//set the focusable variable
			//because the JPanel (PongPanel) must have focus to receive keyboard events 
			//Doing this will call the keyPressed() method when a key is pressed 
			// and the keyReleased()method when a key is released.
			setFocusable(true);
	} // closes constructor
	
	// method for updating the object positions and run our game logic
	
	// The reason we use the createObjects() method and call it from update()
	// rather than creating the objects inside the constructor is that we do not 
	// have access to the width and height of the panel yet. These two values
	// are needed to correctly create the ball and paddles. It is not until the 
	// constructor completes that the panel has been properly initialised and has 
	// a width and height. To avoid this, we instead initialise our objects on our first update() call.
	private void update() {
		switch (gameState) {
			case Initialising: {
				createObjects();
				gameState = GameState.Playing;
				// setting ball speed
				ball.set_xVelocity(BALL_MOVEMENT_SPEED);
				ball.set_yVelocity(BALL_MOVEMENT_SPEED);
				
				break;
			}
			case Playing: {
				moveObject(paddle1);
				moveObject(paddle2);
				moveObject(ball);
				checkWallBounce();
				checkPaddleBounce();
				checkWin();
				break;
			}
			case GameOver: {
				break;
			}
		}
		
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		paintDottedLine(g);
		if (gameState != GameState.Initialising) {
			paintSprite(g, ball);
			paintSprite(g, paddle1);
			paintSprite(g, paddle2);
			paintScores(g);
		}
		if (gameState == GameState.GameOver) {
			
			paintWinner(g);
		}
		
	}
	
	private void paintDottedLine(Graphics g) {
		Graphics2D g2d = (Graphics2D) g.create();
		Stroke dashed = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[] {9},0);
		g2d.setStroke(dashed);
		g2d.setPaint(Color.BLACK);
		g2d.drawLine(getWidth() / 2, 0, getWidth() / 2, getHeight());
		g2d.dispose();
		
	}
	
	// creates Ball and Paddles object
	private void createObjects() {
		ball = new Ball(getWidth(), getHeight());
		paddle1 = new Paddle(Player.One, getWidth(), getHeight());
		paddle2 = new Paddle(Player.Two, getWidth(), getHeight());

	}
	
	private void paintSprite(Graphics g, Sprite sprite) {
		g.setColor(sprite.get_color());
		g.fillRect(sprite.get_xPosition(), sprite.get_yPosition(), sprite.get_width(), sprite.get_height());
	}
	
	//takes a Sprite as a parameter and increases the x and y position of the given object by the x and y velocity of the object.
	private void moveObject(Sprite sprite) {	
		sprite.set_xPosition((sprite.get_xPosition() + sprite.get_xVelocity()), getWidth());
		sprite.set_yPosition((sprite.get_yPosition() + sprite.get_yVelocity()), getHeight());		
	}
	
	//test whether the ball is at the edge of the screen. If it is, reverse the velocity
	private void checkWallBounce() {
		// hits left or right side
		if (ball.get_xPosition() <=0) {
			ball.set_xVelocity(-ball.get_xVelocity());
			resetBall();
			addScore(Player.Two);
		}
		// hits right side
		else if (ball.get_xPosition() >= (getWidth() - ball.get_width())) {
			ball.set_xVelocity(-ball.get_xVelocity());
			resetBall();	
			addScore(Player.One);
		}
		// hits top or bottom 
		if ((ball.get_yPosition()<=0) || (ball.get_yPosition() >=(getHeight()-ball.get_height()))) {
			ball.set_yVelocity(-ball.get_yVelocity());
		}
	}
	// check if the ball and paddle rectangles intersect. If they do, then reverse the x velocity of the ball.
	private void checkPaddleBounce() {
		/*if ((ball.get_xPosition() - ball.get_width()/2) == paddle1.get_xPosition() && 
				((ball.get_yPosition() - ball.get_height()/2) <= (paddle1.get_yPosition() + paddle1.get_height()/2) && 
				((ball.get_yPosition() + ball.get_height()/2) >= (paddle1.get_yPosition() - paddle1.get_height()/2)))) {
			ball.set_xVelocity(-ball.get_xVelocity());
		}
		if ((ball.get_xPosition() + ball.get_width()/2)== paddle2.get_xPosition() && 
				((ball.get_yPosition() - ball.get_height()/2) <= (paddle2.get_yPosition() + paddle2.get_height()/2) && 
				((ball.get_yPosition() + ball.get_height()/2) >= (paddle2.get_yPosition() - paddle2.get_height()/2)))) {
			ball.set_xVelocity(-ball.get_xVelocity());
		}*/
		
		 if(ball.get_xVelocity() < 0 && ball.getRectangle().intersects(paddle1.getRectangle())) {
	          ball.set_xVelocity(BALL_MOVEMENT_SPEED);
	      }
	      if(ball.get_xVelocity() > 0 && ball.getRectangle().intersects(paddle2.getRectangle())) {
	          ball.set_xVelocity(-BALL_MOVEMENT_SPEED);
	      }
		
	}
	
	// resets Ball to initial position
	private void resetBall() {
		ball.resetToInitialPosition();
	}
	
	//takes Player as a parameter. 
	//Increase either player1Score or player2Score depending on the parameter.
	private void addScore(Player player) {
		if (player == Player.One) {
			player1Score ++;
		}
		else if (player == Player.Two) {
			player2Score ++;
		}
	}
	
	//check if either player has scored enough points to win. 
	//If they have, set the gameWinner and then change the gameState to GameOver.
	private void checkWin() {
		if (player1Score >=POINTS_TO_WIN) {
			gameWinner = Player.One;
			gameState = GameState.GameOver;
		}
		else if (player2Score >=POINTS_TO_WIN) {
			gameWinner = Player.Two;
			gameState = GameState.GameOver;
		}		
	}
	
	private void paintScores(Graphics g) {
		 Font scoreFont = new Font(SCORE_FONT_TYPE, Font.BOLD, SCORE_FONT_SIZE);
         String leftScore = Integer.toString(player1Score);
         String rightScore = Integer.toString(player2Score);
         g.setFont(scoreFont);
         g.drawString(leftScore, SCORE_X_PADDING, SCORE_Y_PADDING);
         g.drawString(rightScore, getWidth()-SCORE_X_PADDING, SCORE_Y_PADDING);
	}
	
	private void paintWinner(Graphics g) {
		int win_X_padding = 0;
		if (gameWinner == Player.One) {
			win_X_padding = WIN_X_PADDING;
		}
		else if (gameWinner == Player.Two) {
			win_X_padding = getWidth() - WIN_X_PADDING;
		}
		
		Font winnerFont = new Font(SCORE_FONT_TYPE, Font.BOLD, WIN_FONT_SIZE);
        
        g.setFont(winnerFont);
        g.drawString(WINNING_MESSAGE, win_X_padding, WIN_Y_PADDING);
        
	}
	
	
}// closes PongPanel class

