package pieces;

import main.Board;

/**
 * Class representing the Pawn piece.
 */
public class Pawn extends Piece {

	public Pawn(String color) {
		super(color);
	}

	@Override
	public boolean isValidMove(int startX, int startY, int endX, int endY, Board board) {
		if (color.equals("white")) {
			return startX - endX == 1 && startY == endY;
		} else {
			return endX - startX == 1 && startY == endY;
		}
	}
}