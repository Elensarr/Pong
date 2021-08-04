/**
 * 
 */

/**
 * @author Elensarr
 *
 */


import javax.swing.JFrame;

public class Pong extends JFrame {
	
	//============== CONSTANTS =========================
		public static final String WINDOW_TITLE = "Pong";		
		public static final int WINDOW_WIDTH = 800;
		public static final int WINDOW_HEIGHT = 600;
		

		public Pong() {
			setTitle(WINDOW_TITLE);
			setSize(WINDOW_WIDTH,WINDOW_HEIGHT);
			setResizable(false);
			
			// adds panel to the frame
			add(new PongPanel());
			setVisible(true);
			setDefaultCloseOperation(EXIT_ON_CLOSE);
		}
		
		public static void main(String[] args) {
			
			//delays the GUI creation task until until the initial thread's tasks have been completed
			// can be called from any thread
			javax.swing.SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					new Pong();
				}
			}); // closes Runnable
				
		}// closes main method

} // closes Pong class
