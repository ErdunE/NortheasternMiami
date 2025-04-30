package states;

import main.ChessGame;

/**
 * The state that switches the turn of the player from black to white or vice versa.
 *
 * @author Raj
 * @version 1.0
 * @Since 11/06/2024
 */
public class PlayerTurnSwitchState extends State {
    public PlayerTurnSwitchState(ChessGame game) {
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
     * Transitions back to normal play state after turn of player is switched
     * @return an instance of NormalPlayState
     */
    @Override
    public State normalPlay() {
        System.out.println("Player turn ended. ");
        System.out.println("Transitioning to NormalPlayState.");
        return new NormalPlayState(game);
    }

    /**
     * This function is not intend to implement here.
     */
    @Override
    public State checkMate() {
        throw new UnsupportedOperationException("Cannot checkmate in PlayerTurnSwitchState.");
    }

    /**
     * This function is not intend to implement here.
     */
    @Override
    public State check() {
        throw new UnsupportedOperationException("Cannot check in PlayerTurnSwitchState.");
    }

    /**
     * Prints out the switch of player and turn beginning, to go back to Normal Play State
     * @return an instance of NormalPlayState for the game to go back to normal.
     */
    @Override
    public State playerTurnSwitch() {
        System.out.println("Switching player to " + game.getCurrentPlayer() + " turn.");
        System.out.println("Transitioning to NormalPlayState.");
        return new NormalPlayState(game);
    }
}