package pieces;

import main.Board;

public class Pawn extends Piece
{

	public Pawn(boolean isWhite)
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
		if (isWhite == true)
		{
			return startX - endX == 1 && startY == endY;
		}
		else
		{
			return endX - startX == 1 && startY == endY;
		}
	}
}
