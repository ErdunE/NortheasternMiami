package states;
import main.*;
import states.commons.Move;
import java.util.ArrayList;
import java.util.List;
/**
 * @author Xinyu Wang
 * @version 9/15/2024
 * Parent class for state:
 *
 */
public abstract class State {
	
	ChessGame game;
	String activePlayer;
	List<Move> moveHistory;

	public State(ChessGame game) {
		this.game = game;
		moveHistory = new ArrayList<>();
	}

	public void setActivePlayer(String playerName) {
		this.activePlayer = playerName;
	}

	public String getActivePlayer() {
		return this.activePlayer;
	}

	public void addMove(Move move) {
		moveHistory.add(move);
	}

	public List<Move> getMoveHistory() {
		return moveHistory;
	}
	
}
