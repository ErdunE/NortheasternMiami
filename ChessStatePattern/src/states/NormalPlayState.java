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
		System.out.println("Transitioning to PlayerTurnSwitchState.");
		return new PlayerTurnSwitchState;
	}

	@Override
	public State checkMate() {
		return null;
	}

	@Override
	public State check() {
		String currPlayer = game.getCurrentPlayer();
		System.out.println("Check!");
		System.out.println(currPlayer + ", Please take actions to escape.");
		System.out.println("Transitioning to CheckState.");
		return new CheckState;
	}

	@Override
	public State playerTurnSwitch() {
		throw new UnsupportedOperationException("Please make your move. ");
	}
}
