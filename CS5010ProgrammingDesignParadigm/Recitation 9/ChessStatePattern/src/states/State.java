package states;

import main.ChessGame;

/**
 * Abstract base class representing different states in a chess game.
 * Each state defines different transitions for game phases and scenarios.
 */
public abstract class State {
	protected ChessGame game;

	public State(ChessGame game) {
		this.game = game;
	}

	public abstract State startGame();
	public abstract State normalPlay();
	public abstract State checkMate();
	public abstract State check();
	public abstract State playerTurnSwitch();
}