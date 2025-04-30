

package pieces;

import main.Board;

/**
 * Rook class that defines the movement for the Rook piece as well as the color.
 *
 * @author Raj
 * @version 1.0
 * @since 11/06/2024
 */
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
	 * Checks if the Rook is moving in a valid way.
	 * @param startX Starting Row position
	 * @param startY Starting Column position
	 * @param endX Ending Row position
	 * @param endY Ending Column position
	 * @param board Chess board reference
	 * @return Returns whether move is valid or not
	 */
	@Override
	public boolean isValidMove(int startX, int startY, int endX, int endY, Board board) {
		return startX == endX || startY == endY;
	}

}
