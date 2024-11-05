package states;
import main.*;
/**
 * @author Xinyu Wang
 * @version 10/31/2024
 * The {@code State} class is the abstract superclass representing the state of the game.
 */
public abstract class State {
	
	ChessGame game;
	private boolean isCheck;
	private boolean isCheckMate;
	private String currentState;

	public State(ChessGame game) {
		this.game = game;
	}

	/**
	 * Set the state to check.
	 */
	public abstract void setCheck();

	/**
	 * Set the state to normal play state.
	 * Set isCheck to false.
	 */
	public abstract void setNormalPlayState();

	/**
	 * Set the state to checkmate.
	 */
	public abstract void setCheckMate();

	/**
	 * Return if it is in check state.
	 * @return boolean.
	 */
	public abstract boolean isCheck();

	/**
	 * Return if it is in checkmate state.
	 * @return boolean.
	 */
	public abstract boolean isCheckMate();

	/**
	 * Return the current state.
	 * @return String.
	 */
	public String getCurrentState() {
		return this.currentState;
	}

	/**
	 * Set the current state.
	 * @param currentState a string that represent the current state
	 */
	public void setCurrentState(String currentState) {
		this.currentState = currentState;
	}
}
