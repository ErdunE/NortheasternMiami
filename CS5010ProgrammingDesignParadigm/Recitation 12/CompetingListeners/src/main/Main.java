package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import javax.swing.*;

/**
 * The Main class represents the entry point and GUI setup for the Key and Mouse Binding Demo application.
 * This application demonstrates the use of key bindings and mouse events to trigger specific actions.
 * Users can reset key bindings to their default state using a button.
 *
 * @author Erdun E
 * @version 1.0
 * @since 2024-11-20
 */
public class Main implements ActionListener{

	// GUI components
	JFrame frame;
	JPanel panel;
	JLabel label;
	JButton keyReBinding;
	JTextField textField;

	// Event listeners
	MouseIn mouseInputs;
	KeyIn keyInputs;
	KeyBinder key;

	/**
	 * Constructs and initializes the Main application GUI and event handling.
	 */
	public Main() {
		// Initialize mouse and key listeners
		mouseInputs = new MouseIn(this);
		key = new KeyBinder(this, mouseInputs);
		keyInputs = new KeyIn(this, key);

		// Initialize frame and panel
		frame = new JFrame();
		panel = new JPanel();

		// Set up label and button
		label = new JLabel("Starter Text:");
		keyReBinding = new JButton("Set Key Bindings");
		keyReBinding.addActionListener(this);

		// Set up text field
		textField = new JTextField("Key binding results will appear here...");
		textField.setEditable(false);
		textField.setColumns(30);

		// Add user instructions
		JLabel instructionLabel = new JLabel("<html>Instructions:<br>"
				+ "- Press 'a', 'b', 'c' to trigger bound actions.<br>"
				+ "- Click anywhere to see mouse coordinates.<br>"
				+ "- Reset bindings using the button below.</html>");
		instructionLabel.setBounds(10, 10, 480, 70);

		// Configure layout and component positions
		panel.setLayout(null);
		label.setBounds(10, 90, 300, 20);
		textField.setBounds(10, 120, 400, 30);
		keyReBinding.setBounds(10, 160, 150, 30);

		// Add components to panel
		panel.add(instructionLabel);
		panel.add(label);
		panel.add(textField);
		panel.add(keyReBinding);

		// Add listeners to panel
		panel.addKeyListener(keyInputs);
		panel.addMouseListener(mouseInputs);

		// Configure frame
		frame.add(panel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500, 500);
		frame.setLocationRelativeTo(null);
		frame.setTitle("Key and Mouse Binding Demo");
		frame.setVisible(true);

		// Ensure the panel can capture focus for key events
		SwingUtilities.invokeLater(() -> {
			panel.setFocusable(true);
			panel.requestFocusInWindow();
		});
	}

	/**
	 * The main entry point for the application. Creates a new instance of the Main class.
	 */
	public static void main(String[] args) {
		new Main();
	}

	/**
	 * Handles the action performed when the "Set Key Bindings" button is pressed.
	 * Resets key and mouse bindings to their default state.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == keyReBinding) {
			System.out.println("Pressed button. Key Bindings Reset to Default");

			// Create new listeners
			key = new KeyBinder(this, mouseInputs);
			keyInputs = new KeyIn(this, key);
			mouseInputs = new MouseIn(this);

			// Remove all old key and mouse listeners
			for (KeyListener kl : panel.getKeyListeners()) {
				panel.removeKeyListener(kl);
			}
			for (MouseListener ml : panel.getMouseListeners()) {
				panel.removeMouseListener(ml);
			}

			// Add new listeners to panel
			panel.addKeyListener(keyInputs);
			panel.addMouseListener(mouseInputs);

			// Update GUI components
			textField.setText("Key Bindings Reset to Default");
			label.setText("Starter Text");

			// Ensure the panel can capture focus for key events
			panel.setFocusable(true);
			panel.requestFocusInWindow();
		}
	}
}
