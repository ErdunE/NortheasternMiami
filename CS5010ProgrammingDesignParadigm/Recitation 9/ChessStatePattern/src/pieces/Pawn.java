package pieces;

public class Pawn extends Piece {

	int xMovement;
	int yMovement;
	
	public Pawn(int[] coordinates, String color) {
		super(coordinates, color);
		
		//Pawns have special conditions for their x and y movement.
		//Those conditions need to be stored somewhere, like a logic class.
		xMovement = 1;
		yMovement = 1;
		
	}

}
