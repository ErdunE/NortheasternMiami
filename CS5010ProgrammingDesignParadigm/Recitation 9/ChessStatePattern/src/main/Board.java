package main;

import java.util.ArrayList;

public class Board {
	
	ArrayList<Node> nodes;
	int[][] matrix;
	
	public Board() {
		nodes = new ArrayList<>();
		matrix = new int[8][8]; //A board is always an 8x8 grid, so we don't need
								//a variable to represent different possible sides.
	}
	
	//Every time we add a node, we need to store what position in the array it is
	//to know what grid it will correspond to on the board.
	public void addNode(Node node) {
		nodes.add(node);
		int tileNumber = nodes.size();
		node.setTile(tileNumber);
	}
}
