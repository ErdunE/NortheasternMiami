package main;

import pieces.*;

/**
 * We didn't implement the {@code Node} class, because of the lack of availability of us, and it doesn't impact the program now.
 * Will implement it in the future and this is the original design of {@code Node} class below
 *
 * The {@code Node} class represents a single tile on the chessboard in a chess game.
 * Each {@code Node} can hold a reference to a {@code Piece}, allowing the board to track which piece is located on each tile.
 * <p>
 * Each node has a unique tile number (0 through 63) representing its position on an 8x8 chessboard. A node can either hold a piece or be empty.
 * </p>
 * <p>
 * This class includes methods to set and get the tile number and piece, as well as to check if a node is empty.
 * </p>
 *
 * @author Erdun E
 * @version 1.0
 * @since 2024-10-31
 */
public class Node {
	int tile;
	Piece piece;

	/**
	 * Sets the tile number for this node, representing its unique position on the board.
	 * The tile number should be between 0 and 63 for a standard 8x8 board.
	 *
	 * @param tileNumber the tile number to set, expected to be between 0 and 63
	 * @throws IllegalArgumentException if tileNumber is outside the 0-63 range
	 */
	public void setTile(int tileNumber) {
		if(tileNumber < 0 || tileNumber > 63){
			throw new IllegalArgumentException("Tile number must be between 0 and 63");
		}
		this.tile = tileNumber;
	}

	/**
	 * Returns the tile number of this node.
	 *
	 * @return the tile number, an integer between 0 and 63
	 */
	public int getTile() {
		return this.tile;
	}

	/**
	 * Places a piece on this node.
	 *
	 * @param piece the piece to place on this node; can be any object that inherits from {@code Piece}
	 */
	public void setPiece(Piece piece) {
		this.piece = piece;
	}

	/**
	 * Returns the piece currently on this node.
	 *
	 * @return the piece on this node, or {@code null} if the node is empty
	 */
	public Piece getPiece() {
		return this.piece;
	}

	/**
	 * Checks if this node is empty.
	 *
	 * @return {@code true} if this node has no piece, {@code false} otherwise
	 */
	public boolean isEmpty() {
		return this.piece == null;
	}
}
