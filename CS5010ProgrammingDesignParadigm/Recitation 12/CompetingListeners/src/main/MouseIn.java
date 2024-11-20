package main;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * The MouseIn class implements the MouseListener interface to handle mouse events within the application.
 * It captures mouse actions and updates the GUI to display relevant information, such as the mouse click
 * coordinates. This class provides a simple mechanism to respond to mouse interactions in the application.
 *
 * @author Erdun
 * @version 1.0
 * @since 2024-11-20
 */
public class MouseIn implements MouseListener{

	Main main;

	// Constructor
	public MouseIn(Main main) {
		this.main = main;
	}

	/**
	 * Handles mouse click events. Logs the coordinates of the mouse click to the console
	 * and updates the application's text field to display the coordinates.
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println("Mouse clicked at: (" + e.getX() + ", " + e.getY() + ")");
		main.textField.setText("Mouse clicked at (" + e.getX() + ", " + e.getY() + ")");
	}

	// Not implemented
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	// Not implemented
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	// Not implemented
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	// Not implemented
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
	}
}
