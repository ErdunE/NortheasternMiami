package main;

import pieces.King;
import pieces.Pawn;
import pieces.Piece;
import pieces.Rook;

/**
 * Represents the chess board, managing the placement and movement of
 * pieces in a standard 8x8 chess grid.
 * It provides functionality to initialize the board with a standard
 * chess setup, retrieve and set pieces at specific locations, and
 * validate moves according to the rules of each piece.
 *
 * @author Erdun E
 * @version 1.0
 * @since 2024-11-04
 */
public class Board {
	private Piece[][] board;

	public Board() {
		this.board = new Piece[8][8];
		initializeBoard();
	}

	/**
	 * Initializes the chess board with a standard setup of pieces for each player.
	 * This includes placing rooks, knights, bishops, a king, and pawns for both
	 * black and white players in their designated starting positions.
	 */
	private void initializeBoard() {
		// Place black rooks at initial positions
		board[0][0] = new Rook("black");
		board[0][7] = new Rook("black");

		// Place white rooks at initial positions
		board[7][0] = new Rook("white");
		board[7][7] = new Rook("white");

		// Place kings for black and white players
		board[0][4] = new King("black");
		board[7][4] = new King("white");

		// Place black pawns at initial positions
		board[1][0] = new Pawn("black");
		board[1][1] = new Pawn("black");

		// Place white pawns at initial positions
		board[6][0] = new Pawn("white");
		board[6][1] = new Pawn("white");
	}

	/**
	 * Retrieves the piece located at a specific position on the board.
	 *
	 * @param row the row index on the board (0-7)
	 * @param col the column index on the board (0-7)
	 * @return the Piece located at the specified position, or null if no piece is present
	 */
	public Piece getPiece(int row, int col) {
		return board[row][col];
	}

	/**
	 * Places a specific piece at a given position on the board.
	 *
	 * @param row the row index on the board (0-7) where the piece will be placed
	 * @param col the column index on the board (0-7) where the piece will be placed
	 * @param piece the Piece to place at the specified position; null if clearing the position
	 */
	public void setPiece(int row, int col, Piece piece) {
		board[row][col] = piece;
	}

	/**
	 * Validates a move for a piece from its starting position to a target position.
	 * This method checks if the piece located at the start coordinates can legally move
	 * to the end coordinates based on its movement rules.
	 *
	 * @param startX the starting row index of the piece (0-7)
	 * @param startY the starting column index of the piece (0-7)
	 * @param endX the target row index for the piece (0-7)
	 * @param endY the target column index for the piece (0-7)
	 * @return true if the move is valid according to the piece's movement rules, false otherwise
	 */
	public boolean isValidMove(int startX, int startY, int endX, int endY) {
		Piece piece = getPiece(startX, startY);
		// No piece at the start position
		if (piece == null) return false;
		// Check move validity with piece's logic
		return piece.isValidMove(startX, startY, endX, endY, this);
	}
}