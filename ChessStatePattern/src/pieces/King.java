package pieces;

import main.Board;


/**
 * King class that defines the movement for the King piece as well as the color.
 *
 * @author Raj
 * @version 1.0
 * @since 11/06/2024
 */
public class King extends Piece
{

	public King(boolean isWhite)
	{
		super(isWhite);
	}
	/**
	 * Checks if the King is moving in a valid way.
	 * @param startX Starting Row position
	 * @param startY Starting Column position
	 * @param endX Ending Row position
	 * @param endY Ending Column position
	 * @param board Chess board reference
	 * @return Returns whether move is valid or not
	 */
	@Override
	public boolean isValidMove(int startX, int startY, int endX, int endY, Board board) {
		return Math.abs(startX - endX) <= 1 && Math.abs (startY - endY) <= 1;
	}
}

