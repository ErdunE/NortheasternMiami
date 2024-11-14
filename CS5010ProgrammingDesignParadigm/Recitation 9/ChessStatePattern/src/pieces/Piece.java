package pieces;

import main.Board;

public abstract class Piece {

	protected boolean isWhite;

	public Piece(boolean isWhite)
	{
		this.isWhite = isWhite;
	}

	public boolean getIsWhite()
	{
		return isWhite;
	}

	public abstract boolean isValidMove(int startX, int startY, int endX, int endY, Board board);

}

