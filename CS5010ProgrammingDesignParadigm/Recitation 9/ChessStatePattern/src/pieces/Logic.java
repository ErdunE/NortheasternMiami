package pieces;

import main.ChessGame;
import states.*;

/**
 * Class managing the main game logic and state transitions.
 */
public class Logic {
    private State currentState;

    public Logic() {
        this.currentState = new GameStartState(new ChessGame());
    }

    public void executeMove() {
        currentState = currentState.normalPlay();
    }

    public void checkForCheck() {
        currentState = currentState.check();
    }
}