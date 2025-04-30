package states;

import main.ChessGame;

/**
 * Represents the initial state of the chess game, where the game is just starting.
 * This state transitions to the NormalPlayState after the game setup.
 *
 * @author Erdun E
 * @version 1.0
 * @since 2024-11-04
 */
public class GameStartState extends State {

    public GameStartState(ChessGame game) {
        super(game);
    }

    /**
     * Initiates the game and transitions to the NormalPlayState.
     * Displays messages indicating the game's start and the transition to the next state.
     *
     * @return NormalPlayState, indicating the game is now ready for normal play
     */
    @Override
    public State startGame() {
        System.out.println("Game is starting.");
        System.out.println("Transitioning to NormalPlayState.");
        System.out.println("=============================================");
        return new NormalPlayState(game);
    }

    /**
     * This function is not intend to implement here.
     */
    @Override
    public State normalPlay() {
        throw new UnsupportedOperationException("Cannot play in GameStartState.");
    }

    /**
     * This function is not intend to implement here.
     */
    @Override
    public State playerTurnSwitch() {
        throw new UnsupportedOperationException("Cannot switch turns in GameStartState.");
    }

    /**
     * This function is not intend to implement here.
     */
    @Override
    public State check() {
        throw new UnsupportedOperationException("Cannot check in GameStartState.");
    }

    /**
     * This function is not intend to implement here.
     */
    @Override
    public State checkMate() {
        throw new UnsupportedOperationException("Cannot checkmate in GameStartState.");
    }
}