package main;

/**
 * Represents a node on the chess board.
 */
public class Node {
	private int row;
	private int col;

	public Node(int row, int col) {
		this.row = row;
		this.col = col;
	}

	public int getRow() {
		return row;
	}

	public int getCol() {
		return col;
	}
}