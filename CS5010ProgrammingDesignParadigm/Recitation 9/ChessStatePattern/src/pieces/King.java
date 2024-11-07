package pieces;

import main.Board;

/**
 * Class representing the King piece.
 */
public class King extends Piece {

	public King(String color) {
		super(color);
	}

	@Override
	public boolean isValidMove(int startX, int startY, int endX, int endY, Board board) {
		// Check if the move is within one square in any direction
		return Math.abs(startX - endX) <= 1 && Math.abs(startY - endY) <= 1;
	}
}