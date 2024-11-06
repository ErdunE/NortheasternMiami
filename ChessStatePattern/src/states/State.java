package states;
import main.*;
/**
 * @author Xinyu Wang
 * @version 10/31/2024
 * The {@code State} class is the abstract superclass representing the state of the game.
 */
public abstract class State {
	
	private ChessGame game;

	public State(ChessGame game) {
		this.game = game;
	}

	/**
	 * Return the StartGame state.
	 * @return State.
	 */
	public abstract State startGame();

	/**
	 * Return the NormalPlayState.
	 * @return State.
	 */
	public abstract State normalPlay();

	/**
	 * Return the CheckMate state.
	 * @return State.
	 */
	public abstract State checkMate();

	/**
	 * Return the Check state.
	 * @return State
	 */
	public abstract State check();

	/**
	 * Return the PlayerTurnSwitch.
	 * @return State.
	 */
	public abstract State PlayerTurnSwitch();
}
