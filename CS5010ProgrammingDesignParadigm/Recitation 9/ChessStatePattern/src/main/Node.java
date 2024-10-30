package main;

import pieces.*;

/**
 * This code should only be used if you plan on implementing a graph
 * structure to track piece movement. Each node will be held in the Board
 * class, which will function as a Graph Data Structure. There is some starter
 * code to tell you what information should be on each node, but there is 
 * more you can provide if you like.
 */

public class Node {
	
	/**
	 * Each node will represent a tile numbered 0 through 63.
	 * Each tile will also have a corresponding Row, Column coordinate
	 * contained in the Board class. Whether or not that needs to be
	 * represented in here is up to you.
	 * 
	 * We use Dynamic Dispatch to create a Piece attribute that can be null,
	 * but also can contain any chess piece that inherits the Piece class.
	 */
	int tile;
	Piece piece;
	
	public void setTile(int tileNumber) {
		this.tile = tileNumber;
	}
	public int getTile() {
		return this.tile;
	}
	
	public void setPiece(Piece piece) {
		this.piece = piece;
	}
	public Piece getPiece() {
		return this.piece;
	}
	
}
