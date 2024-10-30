package pieces;

public class Rook extends Piece {
	
	/*
	 * for the rook piece, x and y movement can be any given length,
	 * but if x has a value, y cannot, and the opposite is true for y.
	 * The logic to control this behavior should probably be wrapped up
	 * in another class.
	 */
	int xMovement;
	int yMovement;
	
	public Rook(int[] coordinates2, String color2) {
		super(coordinates2, color2);
		
	}

}
