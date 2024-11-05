package main;

import pieces.Piece;

import java.util.ArrayList;
import java.util.List;

public class Board {
	
	ArrayList<Node> nodes;
	int[][] matrix;
	char[][] tile;
	List<Piece> pieces;
	
	public Board() {
		nodes = new ArrayList<>();
		matrix = new int[8][8];
		tile = new char[8][8];
		for (int i = 0; i < 8; i ++)
		{
			for (int j = 0; j < 8; j ++)
			{
				tile[i][j] = '■';//A board is always an 8x8 grid, so we don't need
			}
		}

		pieces = new ArrayList<>();//a variable to represent different possible sides.
	}

	public void addPiece(Piece piece)
	{
		pieces.add(piece);
		tile[piece.getXCoordinate()][piece.getYCoordinate()] = piece.getRepChar();
	}

	public void updatePiecePosition(Piece piece, int x, int y)
	{
		tile[piece.getXCoordinate()][piece.getYCoordinate()] = '■';
		piece.setCoordinates(new int[]{x,y});
	}
	
	//Every time we add a node, we need to store what position in the array it is
	//to know what grid it will correspond to on the board.
	public void addNode(Node node) {
		nodes.add(node);
		int tileNumber = nodes.size();
		node.setTile(tileNumber);
	}
}
