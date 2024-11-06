package states;

import main.*;
/**
 * Here is a single state class. You will need to add
 * more for this assignment. The states are arguably the most
 * important part of the assignment to understand, so please
 * work on implementing the states as reliably as possible.
 */

public class NormalPlayState extends State {

	private ChessGame game;
	private String currentPlayer;

	public NormalPlayState(ChessGame game) {
		super(game);
	}

	@Override
	public State startGame() {
		throw new UnsupportedOperationException("Please continue to play.");
	}

	@Override
	public State normalPlay() {
		System.out.println("Nice try! Now it's your turn.");
		return new PlayerTurnSwitchState;
	}

	@Override
	public State checkMate() {
		return null;
	}

	@Override
	public State check() {
		System.out.println("Check! Please take actions to escape.");
		return new CheckState;
	}

	@Override
	public State playerTurnSwitch() {
		throw new UnsupportedOperationException("Please make your move. ");
	}
}
