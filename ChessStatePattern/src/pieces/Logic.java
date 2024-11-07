package pieces;
import main.ChessGame;
import states.*;
/**
 * This class can control the logic of how each chess piece is allowed to move.
 * It is not necessary to fully or accurately implement the chess logic,
 * but it's here if you want to try it out.
 */

public class Logic {
    private State currentState;

    public Logic()
    {
        this.currentState = new GameStart(new ChessGame());
    }

    public void executeMove()
    {
        currentState = currentState.normalPlay();
    }

    public void checkStateConfirmation()
    {
        currentState = currentState.check();
    }

}
