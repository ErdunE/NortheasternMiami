package pieces;

import main.ChessGame;
import states.*;

/**
 * We didn't implement the {@code Logic} class, because of the lack of availability of us, and it doesn't impact the program now.
 * Will implement it in the future and this is the original design of {@code Logic} class below
 *
 * Class managing the main game logic and state transitions.
 *
 * @author Erdun E
 * @version 1.0
 * @since 2024-11-04
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