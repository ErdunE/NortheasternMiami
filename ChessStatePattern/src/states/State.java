package states;

import main.*;

public abstract class State {
	
	ChessGame game;
	
	public State(ChessGame game) {
		this.game = game;
	}
	
	//here is where you write the methods you will want overridden by each
	//specific state's behavior
	
}
