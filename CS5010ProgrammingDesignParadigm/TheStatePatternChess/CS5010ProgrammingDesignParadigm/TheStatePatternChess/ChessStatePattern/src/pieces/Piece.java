package pieces;

import main.Board;

/**
 * Piece class is the parent class for all pieces and holds common functionality.
 *
 * @author Raj
 * @version 1.0
 * @since 11/06/2024
 */
public abstract class Piece {

	protected boolean isWhite;

	public Piece(boolean isWhite)
	{
		this.isWhite = isWhite;
	}

	/**
	 * @return Returns whether Piece is white.
	 */
	public boolean getIsWhite()
	{
		return isWhite;
	}

	/**
	 * Abstract function to check every different piece's valid movement.
	 * @param startX Starting Row position
	 * @param startY Starting Column position
	 * @param endX Ending Row position
	 * @param endY Ending Column position
	 * @param board Chess board reference
	 * @return Returns whether move is valid or not
	 */
	public abstract boolean isValidMove(int startX, int startY, int endX, int endY, Board board);

}

