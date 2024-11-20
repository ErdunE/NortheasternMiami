package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * The KeyIn class implements the KeyListener interface to handle key events within the application.
 * It is responsible for processing key-typed, key-pressed, and key-released events, and relaying specific
 * key actions ('a', 'b', 'c') to the KeyBinder class for further processing.
 *
 * @author Erdun E
 * @version 1.0
 * @since 2024-11-20
 */
public class KeyIn implements KeyListener{
	Main main;
	KeyBinder keyBinder;

	// Constructors
	public KeyIn(Main main, KeyBinder keyBinder) {
		this.main = main;
		this.keyBinder = keyBinder;
	}

	/**
	 * Handles key-typed events. If the key is 'a', 'b', or 'c', it relays the event to KeyBinder.
	 * Otherwise, it updates the text field to indicate the key is unbound.
	 */
	@Override
	public void keyTyped(KeyEvent e) {
		System.out.println("Key typed: " + e.getKeyChar());
		char keyChar = e.getKeyChar();
		// If the input matched, forward the event to KeyBinder for processing, otherwise display unbound key message
		if (keyChar == 'a' || keyChar == 'b' || keyChar == 'c') {
			keyBinder.keyTyped(e);
		} else {
			main.textField.setText("Unbound key: " + keyChar);
		}

	}

	/**
	 * Handles key-pressed events. Updates the text field if the pressed key is not bound ('a', 'b', 'c').
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyChar() != 'a' && e.getKeyChar() != 'b' && e.getKeyChar() != 'c') {
			main.textField.setText("Key Pressed: " + e.getKeyChar());
		}
	}

	/**
	 * Handles key-released events. Updates the text field if the released key is not bound ('a', 'b', 'c').
	 */
	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyChar() != 'a' && e.getKeyChar() != 'b' && e.getKeyChar() != 'c') {
			main.textField.setText("Key Released: " + e.getKeyChar());
		}
	}
}
