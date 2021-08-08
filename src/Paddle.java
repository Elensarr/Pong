import java.awt.Color;

public class Paddle extends Sprite {
	
	// === CONSTANTS ====	
	private static final Color PADDLE_COLOUR = Color.WHITE;
	private static final int PADDLE_WIDTH = 10;
	private static final int PADDLE_HEIGHT = 100;
	private static final int DISTANCE_FROM_EDGE = 40;
	
	// === CONSTRUCTOR ===
	Paddle(Player player, int panelWidth, int panelHeight) {
		// set width, height, colour
		set_width(PADDLE_WIDTH);
		set_height(PADDLE_HEIGHT);
		set_colour(PADDLE_COLOUR);
		int xPos;
		// set initial starting position
		// setting paddle for player One
		if (player == Player.One) {
			xPos = DISTANCE_FROM_EDGE;	
		}
		
		// setting paddle for player Two
		else {
			xPos = panelWidth - get_width() - DISTANCE_FROM_EDGE;
		}
		set_initialPosition(xPos, panelHeight / 2 - (get_height() / 2));		
		// reset to initial position
		resetToInitialPosition();
		
	}// closes constructor
}// closes Paddle class
