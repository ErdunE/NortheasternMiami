package pieces;

import main.Board;

public class King extends Piece
{

	public King(boolean isWhite)
	{
		super(isWhite);
	}
	/**
	 * @param startX
	 * @param endX
	 * @param endY
	 * @param board
	 * @return
	 */
	@Override
	public boolean isValidMove(int startX, int startY, int endX, int endY, Board board) {
		return Math.abs(startX - endX) <= 1 && Math.abs (startY - endY) <= 1;
	}
}

