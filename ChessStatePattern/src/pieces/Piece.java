package pieces;

public abstract class Piece {
	
	int[] coordinates;
	String color;
	char repChar;
	
	public Piece(int[] coordinates, String color) {
		// generic constructor for a piece
		this.coordinates = coordinates;
		this.color = color;
		this.repChar = 'p';
	}

	public int getXCoordinate()
	{
		return coordinates[0];
	}

	public int getYCoordinate()
	{
		return coordinates[1];
	}

	public char getRepChar()
	{
		return repChar;
	}

	public void setCoordinates(int[] coordinates) {
		this.coordinates = coordinates;
	}
}
