package pieces;

import main.Board;

/**
 * Class representing the Rook piece.
 */
public class Rook extends Piece {

	public Rook(String color) {
		super(color);
	}

	@Override
	public boolean isValidMove(int startX, int startY, int endX, int endY, Board board) {
		return startX == endX || startY == endY;
	}
}