import java.awt.Color;

public class Sprite {

	//variables
	private int xPosition, yPosition, xVelocity, yVelocity, width, height;
	private Color colour;
	
	
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
	
}
