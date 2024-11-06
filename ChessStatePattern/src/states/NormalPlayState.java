package states;

import main.*;
/**
 * Here is a single state class. You will need to add
 * more for this assignment. The states are arguably the most
 * important part of the assignment to understand, so please
 * work on implementing the states as reliably as possible.
 */

public class NormalPlayState extends State {
	
	public NormalPlayState(ChessGame game) {
		super(game);
	}

	@Override
	public State startGame() {
		return null;
	}

	@Override
	public State normalPlay() {
		return null;
	}

	@Override
	public State checkMate() {
		return null;
	}

	@Override
	public State check() {
		return null;
	}

	@Override
	public State playerTurnSwitch() {
		return null;
	}
}
