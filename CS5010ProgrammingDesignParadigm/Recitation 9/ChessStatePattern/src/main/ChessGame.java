package main;

import pieces.King;
import pieces.Pawn;
import pieces.Piece;
import pieces.Rook;
import states.*;

import java.util.Random;

public class ChessGame {
	private State currentState;
	private Board board;
	private Random random;
	private String currentPlayer;

	public ChessGame() {
		this.board = new Board();
		this.currentState = new GameStartState(this);
		this.random = new Random();
		this.currentPlayer = "white";
	}

	public void start() {
		System.out.println("Welcome! Initializing The Chess Game...");
		System.out.println("=============================================");

		currentState = currentState.startGame();

		while (!(currentState instanceof CheckmateState)) {

			if (currentState instanceof PlayerTurnState) {
				switchPlayer();
				currentState = currentState.PlayerTurnSwitch();
			} else if (currentState instanceof NormalPlayState) {
				System.out.println("Waiting for player " + getCurrentPlayer() + " action...");
				boolean moveSuccessful = simulateRandomMove();
				if (moveSuccessful) {
//					System.out.println("Current State : " + currentState.getClass().getSimpleName());
//					System.out.println("Current Player: " + currentPlayer);

					if (random.nextDouble() > 0.7) {
						switchPlayer();
						currentState = currentState.check();
					} else {
						currentState = currentState.normalPlay();
					}
				}
			} else if (currentState instanceof CheckState) {
				boolean checkResolved = random.nextDouble() > 0.3;
				System.out.println("Player " + getCurrentPlayer() + " is checking if check can be resolved...");
				if (checkResolved) {
					currentState = currentState.check();
				} else {
					switchPlayer();
					currentState = currentState.checkMate();
				}
			}

			System.out.println("=============================================");


			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
				System.out.println("Thread was interrupted during sleep.");
			}
		}

		currentState.checkMate();

	}

	private boolean simulateRandomMove() {
		int startX, startY, endX = -1, endY = -1;
		Piece piece;

		do {
			startX = random.nextInt(8);
			startY = random.nextInt(8);
			piece = board.getPiece(startX, startY);
		} while (piece == null || !piece.getColor().equals(currentPlayer));

		if (piece instanceof Rook) {
			if (random.nextBoolean()) {
				endX = startX;
				endY = random.nextInt(8);
			} else {  // Horizontal move
				endX = random.nextInt(8);
				endY = startY;
			}
		} else if (piece instanceof King) {
			endX = startX + random.nextInt(3) - 1;
			endY = startY + random.nextInt(3) - 1;
		} else if (piece instanceof Pawn) {
			endX = piece.getColor().equals("white") ? startX - 1 : startX + 1;
			endY = startY;
		}

		if (endX < 0 || endX >= 8 || endY < 0 || endY >= 8) {
			return false;  // Out of bounds
		}

		if (board.isValidMove(startX, startY, endX, endY)) {
			board.setPiece(endX, endY, piece);
			board.setPiece(startX, startY, null);  // Clear the original position
			System.out.println("Player " + currentPlayer + " Moved " + piece.getClass().getSimpleName() +
					" from (" + startX + ", " + startY + ") to (" + endX + ", " + endY + ")");
			return true;
		} else {
			System.out.println("Invalid move attempted for " + piece.getClass().getSimpleName() +
					" from (" + startX + ", " + startY + ") to (" + endX + ", " + endY + ")");
			return false;
		}
	}

	private void switchPlayer() {
		currentPlayer = currentPlayer.equals("white") ? "black" : "white";
	}

	public String getCurrentPlayer() {
		return currentPlayer;
	}
}