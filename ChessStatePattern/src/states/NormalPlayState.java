package states;

import main.*;
/**
 * @author Xinyu Wang
 * @version 10/31/2024
 * The {@code NormalPlayState} class representing the normal play state of the game.
 */
public class NormalPlayState extends State {

	private ChessGame game;

	public NormalPlayState(ChessGame game) {
		super(game);
	}

	/**
	 * This function is not intend to implement here.
	 */
	@Override
	public State startGame() {
		throw new UnsupportedOperationException("Please continue to play.");
	}

	/**
	 * This function is called after a check action is resolved from CheckState.
	 * Return PlayerTurnSwitchState
	 * @return State.
	 */
	@Override
	public State normalPlay() {
		String currPlayer = game.getCurrentPlayer();
		System.out.println("Player " + currPlayer + " resolved the check.");
		System.out.println("Transitioning to PlayerTurnSwitchState.");
		return new PlayerTurnSwitchState;
	}

	/**
	 * This function is not intend to implement here.
	 */
	@Override
	public State checkMate() {
		throw new UnsupportedOperationException("Please continue to play.");
	}

	/**
	 * This function is called when transitioning from NormalPlayState to CheckState.
	 * Return CheckState
	 * @return State.
	 */
	@Override
	public State check() {
		String currPlayer = game.getCurrentPlayer();
		System.out.println("Check!");
		System.out.println(currPlayer + ", Please take actions to escape.");
		System.out.println("Transitioning to CheckState.");
		return new CheckState;
	}

	/**
	 * This function is not intend to implement here.
	 */
	@Override
	public State playerTurnSwitch() {
		throw new UnsupportedOperationException("Please make your move. ");
	}
}
