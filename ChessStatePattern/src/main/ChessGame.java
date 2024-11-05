package main;

import states.*;

/**
 * The {@code ChessGame} class manages the flow of the chess game.
 * It maintains the current {@code State} of the game and the {@code Board}.
 * This class controls the game loop and handles state transitions.
 *
 * @author Erdun E
 * @version 1.0
 * @since 2024-10-30
 */
public class ChessGame {
	private State state;
	private Board board;

	/**
	 * Constructs a new {@code ChessGame} instance.
	 * Initial the game with {@code NormalPlayState} as the starting state
	 * and creates a new {@code Board}.
	 */
	public ChessGame() {
		state = new NormalPlayState(this);
		board = new Board();
	}

	/**
	 * Set the current state of the game.
	 *
	 * @param state the new state to set
	 */
	public void setState(State state) {
		this.state = state;
	}

	/**
	 * Get the board.
	 *
	 * @return the {@code Board} to represent the chessboard
	 */
	public Board getBoard() {
		return board;
	}

	/**
	 * Starts the game loop. The loop continues until the current state
	 * indicates that the game is over.
	 *
	 * <p>
	 * Placeholder: Currently only simulates the structure of
	 * the game loop without actual state transitions or checks.
	 * </p>
	 */
	public void startGame(){
		// Placeholder: Start of game loop
		System.out.println("Starting game loop...");

		// Placeholder: Simulate game loop condition
		// Expected behavior is replace this with `while (!state.isGameOver())` in final version
		boolean gameInProgress = true;  // Placeholder variable to simulate loop condition

		// Placeholder: Simulate a single iteration of the game loop
		if (gameInProgress) {
			// Placeholder for state actions in the loop
			System.out.println("Taking turn in the current state...");
			// Uncomment this when actual state logic is ready
			// Placeholder: End loop after one iteration
			gameInProgress = false;
		}

		// Placeholder: Game over message
		System.out.println("Game Over. Thanks for playing!");
	}
}
