package states;

import main.ChessGame;

public class NormalPlayState extends State {

	public NormalPlayState(ChessGame game) {
		super(game);
	}

	@Override
	public State startGame() {
		throw new UnsupportedOperationException("Game already started.");
	}

	@Override
	public State normalPlay() {
		System.out.println("Player " + game.getCurrentPlayer() + " completed action");
		System.out.println("Transitioning to PlayerTurnState");
		return new PlayerTurnState(game);
	}

	@Override
	public State PlayerTurnSwitch() {
		throw new UnsupportedOperationException("Cannot switch turns in NormalPlayState.");
	}

	@Override
	public State check() {
		System.out.println("Player " + game.getCurrentPlayer() + " is in Check.");
		System.out.println("Transitioning to CheckState.");
		return new CheckState(game);
	}

	@Override
	public State checkMate() {
		throw new UnsupportedOperationException("Cannot checkmate in NormalPlayState.");
	}
}