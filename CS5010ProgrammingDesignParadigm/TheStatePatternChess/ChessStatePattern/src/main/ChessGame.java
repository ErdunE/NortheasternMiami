package main;

import pieces.King;
import pieces.Pawn;
import pieces.Piece;
import pieces.Rook;
import states.*;
import java.util.Random;

/**
 * The ChessGame class represents a chess game that manages game flow, state transitions, and player moves.
 * This class utilizes the State pattern to handle different stages of the game,
 * including gameStart, playerTurnSwitch, normalPlay, check, and checkmate.
 *
 * @author Erdun E
 * @version 1.0
 * @since 2024-11-04
 */
public class ChessGame {
	private State currentState;
	private Board board;
	private Random random;
	private String currentPlayer;

	public ChessGame() {
		this.board = new Board();
		// Set initial state as GameStartState
		this.currentState = new GameStartState(this);
		this.random = new Random();
		// Player white move first
		this.currentPlayer = "white";
	}

	/**
	 * Starts the chess game and manages the main game loop until a checkmate state is reached.
	 * This method handles state transitions and conditions based on the game logic and player moves.
	 */
	public void start() {
		System.out.println("Welcome! Initializing The Chess Game...");
		System.out.println("Transitioning to GameStartState.");
		System.out.println("=============================================");
		// Start the game, and will transit to NormalPlayState
		currentState = currentState.startGame();
		// Main game loop that continues until a checkmate state is reached
		while (!(currentState instanceof CheckmateState)) {
			// Handle player turn switch state, swap players and transition to the next state
			if (currentState instanceof PlayerTurnSwitchState) {
				switchPlayer();
				// Transition to the NormalPlayState
				currentState = currentState.playerTurnSwitch();
			// Handle normal play state, prompt player for action and simulate a move
			} else if (currentState instanceof NormalPlayState) {
				System.out.println("Waiting for player " + getCurrentPlayer() + " action...");
				// Simulate a random move for the player
				boolean moveSuccessful = simulateRandomMove();
				// Only proceed if the move is valid
				if (moveSuccessful) {
					// Randomly determine if a check condition should be triggered
					if (random.nextDouble() > 0.7) {
						// Switch to the other player to handle check
						switchPlayer();
						// Transition to CheckState
						currentState = currentState.check();
					} else {
						// Continue normal play if no check, will transition to PlayerTurnSwitchState
						currentState = currentState.normalPlay();
					}
				}
			// Handle check state, check if the condition can be resolved or leads to checkmate
			} else if (currentState instanceof CheckState) {
				boolean checkResolved = random.nextDouble() > 0.3;
				System.out.println("Player " + getCurrentPlayer() + " is checking if check can be resolved...");
				if (checkResolved) {
					// Return to normal play if check is resolved
					currentState = currentState.check();
				} else {
					switchPlayer();
					// Transition to checkmate if unresolved
					currentState = currentState.checkMate();
				}
			}
			System.out.println("=============================================");
			// Pause briefly between state transitions to simulate real-time gameplay
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
				System.out.println("Thread was interrupted during sleep.");
			}
		}
		// Call checkMate() on the final state to conclude the game
		currentState.checkMate();
	}

	/**
	 * Simulates a random move by selecting a piece at a random position and attempting to move it.
	 * The move is only valid if it meets the movement rules of the selected piece.
	 *
	 * @return true if the move was successful, false if the move was invalid
	 */
	private boolean simulateRandomMove() {
		// Coordinates for starting and ending positions
		int startX, startY, endX = -1, endY = -1;
		Piece piece;
		// Randomly select a starting position with a piece belonging to the current player
		do {
			startX = random.nextInt(8);
			startY = random.nextInt(8);
			piece = board.getPiece(startX, startY);
		// Ensure it's the current player's piece
		} while (piece == null || (piece.getIsWhite() && !currentPlayer.equals("white")) || (!piece.getIsWhite() && !currentPlayer.equals("black")));
		// Determine a potential end position based on the type of piece selected
		if (piece instanceof Rook) {
			// Rook moves vertically or horizontally
			if (random.nextBoolean()) {
				endX = startX;
				endY = random.nextInt(8);
			} else {  // Horizontal move
				endX = random.nextInt(8);
				endY = startY;
			}
		// King moves one square in any direction
		} else if (piece instanceof King) {
			endX = startX + random.nextInt(3) - 1;
			endY = startY + random.nextInt(3) - 1;
		// Pawn moves forward one square
		} else if (piece instanceof Pawn) {
			endX = piece.getIsWhite() ? startX - 1 : startX + 1;
			endY = startY;
		}
		// Check if the end position is within board bounds
		if (endX < 0 || endX >= 8 || endY < 0 || endY >= 8) {
			return false;  // Out of bounds
		}
		// Validate the move according to the piece's specific movement rules
		if (board.isValidMove(startX, startY, endX, endY)) {
			board.setPiece(endX, endY, piece);
			board.setPiece(startX, startY, null);  // Clear the original position
			System.out.println("Player " + currentPlayer + " Moved " + piece.getClass().getSimpleName() +
					" from (" + startX + ", " + startY + ") to (" + endX + ", " + endY + ")");
			return true;
		} else {
			System.out.println("Invalid move attempted for " + piece.getClass().getSimpleName() +
					" from (" + startX + ", " + startY + ") to (" + endX + ", " + endY + ")");
			return false;
		}
	}

	/**
	 * Switches the current player between white and black.
	 */
	private void switchPlayer() {
		currentPlayer = currentPlayer.equals("white") ? "black" : "white";
	}

	/**
	 * Retrieves the current player (either "white" or "black").
	 *
	 * @return the current player as a string
	 */
	public String getCurrentPlayer() {
		return currentPlayer;
	}
}