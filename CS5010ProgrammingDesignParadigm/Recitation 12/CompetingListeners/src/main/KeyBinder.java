package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;

/**
 * The KeyBinder class implements the KeyListener interface to handle specific key actions
 * a, b, c and simulate corresponding mouse events. This allows key events to trigger
 * actions as if they were mouse clicks, demonstrating the use of an adapter-like pattern.
 *
 * @author Erdun
 * @version 1.0
 * @since 2024-11-20
 */
public class KeyBinder implements KeyListener{
	
	Main main;
	MouseEvent event; 
	MouseIn adaptee;

	// Constructor
	public KeyBinder(Main main, MouseIn adaptee) {
		this.main = main;
		this.adaptee = adaptee;
	}

	/**
	 * Handles key-typed events. For specific keys a, b, c, simulates mouse clicks
	 * at predefined coordinates and updates the application's text field with the triggered action.
	 */
	@Override
	public void keyTyped(KeyEvent e) {
		int keyChar = e.getKeyChar();
		if (keyChar == 'a') {
			// Simulate a mouse click at (50, 50) for key a
			event = new MouseEvent(e.getComponent(), MouseEvent.MOUSE_CLICKED, 0, 0, 50, 50, 1, false);
			adaptee.mouseClicked(event);
			main.textField.setText("Key A triggered (via key binding)");
		} else if (keyChar == 'b') {
			// Simulate a mouse click at (100, 100) for key b
			event = new MouseEvent(e.getComponent(), MouseEvent.MOUSE_CLICKED, 0, 0, 100, 100, 1, false);
			adaptee.mouseClicked(event);
			main.textField.setText("Key B triggered (via key binding)");
		} else if (keyChar == 'c') {
			// Simulate a mouse click at (150, 150) for key c
			event = new MouseEvent(e.getComponent(), MouseEvent.MOUSE_CLICKED, 0, 0, 150, 150, 1, false);
			adaptee.mouseClicked(event);
			main.textField.setText("Key C triggered (via key binding)");
		} else {
			// Display message for unbound keys
			main.textField.setText("Unbound key: " + e.getKeyChar());
		}
	}

	// Not implemented
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	// Not implemented
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
	}
}
