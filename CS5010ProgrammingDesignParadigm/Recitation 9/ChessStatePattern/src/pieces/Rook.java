package pieces;

import main.Board;

public class Rook extends Piece {
	
	/*
	 * for the rook piece, x and y movement can be any given length,
	 * but if x has a value, y cannot, and the opposite is true for y.
	 * The logic to control this behavior should probably be wrapped up
	 * in another class.
	 */
	public Rook(boolean isWhite)
	{
		super(isWhite);
	}

	/**
	 * @param startX
	 * @param startY
	 * @param endX
	 * @param endY
	 * @param board
	 * @return
	 */
	@Override
	public boolean isValidMove(int startX, int startY, int endX, int endY, Board board) {
		return startX == endX || startY == endY;
	}

}
