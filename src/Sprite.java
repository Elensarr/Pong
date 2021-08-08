import java.awt.Color;
import java.awt.Rectangle;

public class Sprite {

	//variables
	private int xPosition, yPosition, xVelocity, yVelocity, width, height;
	private Color colour;
	private int initial_xPosition, initial_yPosition;
	
	
	//constructor
	public Sprite() {
		
	}
	
	//=== SETTERS METHODS ====
	
	public void set_xPosition(int new_xPosition) {
		this.xPosition = new_xPosition;
	}
	public void set_yPosition(int new_yPosition) {
		this.yPosition = new_yPosition;
	}
	
	public void set_initialPosition(int initialX, int initialY) {
		initial_xPosition = initialX;
		initial_yPosition = initialY;
	}
			
	public void set_xPosition(int new_xPosition, int panelWidth) {
		
		this.xPosition = new_xPosition;
		
		// check if new position is inside the panel		
		if ((xPosition + get_width()) > panelWidth) {
			this.xPosition = panelWidth - get_width();
		}
		else if (xPosition < 0) {
			this.xPosition = 0;
		}
	}
	
	public void set_yPosition(int new_yPosition, int panelHeight) {
		this.yPosition = new_yPosition;
		
		// check if new position is inside the panel		
		if ((yPosition + get_height()) > panelHeight) {
			this.yPosition = panelHeight - get_height();
		}
		else if (yPosition < 0) {
			this.yPosition = 0;
		}
	}
	
	// set the x and y position of the object to the initial x and y positions.
			public void resetToInitialPosition() {
				set_xPosition(initial_xPosition);
				set_yPosition(initial_yPosition);			
			}
			
	public void set_xVelocity(int new_xVelocity) {
		this.xVelocity = new_xVelocity;
	}
	public void set_yVelocity(int new_yVelocity) {
		this.yVelocity = new_yVelocity;
	}
	public void set_width(int new_width) {
		this.width = new_width;
	}
	public void set_height(int new_height) {
		this.height = new_height;
	}
	public void set_colour(Color new_color) {
		this.colour = new_color;
	}
	
	// === GETTERS ====
	public int get_xPosition() {
		return xPosition;
	}
	public int get_yPosition() {
		return  yPosition;
	}
	public int get_xVelocity() {
		return  xVelocity;
	}
	public int get_yVelocity() {
		return  yVelocity;
	}
	public int get_width() {
		return  width;
	}
	public int get_height() {
		return height;
	}
	public Color get_color() {
		return colour;
	}
	
	// ===========================
	
	// create rectangle object
	public Rectangle getRectangle() {
        return new Rectangle(get_xPosition(), get_yPosition(), get_width(), get_height());
    }	
	
}
