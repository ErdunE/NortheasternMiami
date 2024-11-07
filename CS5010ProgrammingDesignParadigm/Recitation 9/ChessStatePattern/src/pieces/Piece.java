package pieces;

import main.Board;

/**
 * Abstract class representing a chess piece.
 */
public abstract class Piece {
	protected String color;

	public Piece(String color) {
		this.color = color;
	}

	public String getColor() {
		return color;
	}

	public abstract boolean isValidMove(int startX, int startY, int endX, int endY, Board board);
}