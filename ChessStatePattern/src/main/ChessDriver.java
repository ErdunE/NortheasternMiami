package main;

/**
 * The {@code ChessDriver} class serves as the entry point for the Chess Game program.
 * It initials the {@code ChessGame} instance and starts the game loop.
 *
 * @author Erdun E
 * @version 1.0
 * @since 2024-10-30
 */
public class ChessDriver {
	public static void main(String[] args) {
		// Initial the game
		ChessGame chessGame = new ChessGame();
		// Kick off the game
		chessGame.startGame();
	}
}
