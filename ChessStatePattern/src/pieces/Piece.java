package pieces;

public abstract class Piece {
	
	int[] coordinates;
	String color;
	
	public Piece(int[] coordinates, String color) {
		// generic constructor for a piece
		this.coordinates = coordinates;
		this.color = color;
	}
}
