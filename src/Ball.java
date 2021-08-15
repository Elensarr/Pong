import java.awt.Color;

public class Ball extends Sprite {
	private static final Color BALL_COLOUR = Color.BLACK;
	private static final int BALL_WIDTH = 25;
	private static final int BALL_HEIGHT = 25;
	
	
	
	// ==== CONSTRUCTOR ====
	public Ball(int panelWidth, int panelHeight) {
		set_colour(BALL_COLOUR);
		set_width(BALL_WIDTH);
		set_height(BALL_HEIGHT);
		set_initialPosition(panelWidth / 2 - (get_width() / 2), panelHeight / 2 - (get_height() / 2));		
		resetToInitialPosition();
	}	
}

