package states;

import main.ChessGame;

/**
 * Represents the Checkmate state in a chess game, where one player has been checkmated, ending the game.
 * In this state, no further actions are allowed, as the game is concluded.
 *
 * @author Erdun E
 * @version 1.0
 * @since 2024-11-04
 */
public class CheckmateState extends State {
    public CheckmateState(ChessGame game) {
        super(game);
    }

    /**
     * This function is not intend to implement here.
     */
    @Override
    public State startGame() {
        throw new UnsupportedOperationException("Game already started.");
    }

    /**
     * This function is not intend to implement here.
     */
    @Override
    public State normalPlay() {
        throw new UnsupportedOperationException("Cannot play in CheckmateState.");
    }

    /**
     * Displays the end-of-game message indicating that the game has concluded with checkmate.
     * This method also announces the winner based on the current player in the ChessGame instance.
     * Since this is the terminal state, no further state transitions occur.
     *
     * @return the current instance of CheckmateState, as no further transitions are allowed
     */
    @Override
    public State checkMate() {
        System.out.println("Checkmate! Game Over.");
        System.out.println("Winner is player " + game.getCurrentPlayer() + ". Congrats!!!!");
        System.out.println("Exiting the game...");
        return this;
    }

    /**
     * This function is not intend to implement here.
     */
    @Override
    public State check() {
        throw new UnsupportedOperationException("Cannot check in CheckmateState.");
    }

    /**
     * This function is not intend to implement here.
     */
    @Override
    public State playerTurnSwitch() {
        throw new UnsupportedOperationException("Cannot switch turns in CheckmateState.");
    }
}