package pieces;

import main.Board;

/**
 * Pawn class that defines the movement for the Pawn piece as well as the color.
 *
 * @author Raj
 * @version 1.0
 * @since 11/06/2024
 */
public class Pawn extends Piece
{

	public Pawn(boolean isWhite)
	{
		super(isWhite);
	}
	/**
	 * Checks if the Pawn is moving in a valid way.
	 * @param startX Starting Row position
	 * @param startY Starting Column position
	 * @param endX Ending Row position
	 * @param endY Ending Column position
	 * @param board Chess board reference
	 * @return Returns whether move is valid or not
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
