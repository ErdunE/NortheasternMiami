package states;
import main.*;
/**
 *  Represent the Check state in a chess game, where one player has been checked, if player
 *     can't solve the situation, switch to the checkmated state. If player are not checked, switch
 *     to the NormalPlayState.
 *
 *     @Author: Kai
 *     @Version: 1.0
 *     @since 2024-11-6
 */
public class CheckState extends State{

    public CheckState(ChessGame game){
        super(game);
    }

    /**
     * This function will throw an exception because the game already started.
     */
    @Override
    public State startGame() {
        throw new UnsupportedOperationException("Game already started.");
    }

    /**
     * This function will throw an exception because player is checked now.
     */
    @Override
    public State normalPlay() {
        throw new UnsupportedOperationException("Game already started.");
    }

    /**
     * This function is called when transitioning from Check to checkMate.
     * Return checkMate
     * @return State.
     */
    @Override
    public State checkMate() {
        System.out.println("Player" + game.getCurrentPlayer() + "unable to resolve the current situation. ");
        System.out.println("Transitioning to Checkmate State");
        return new CheckmateState(game);

    }

    /**
     * This function is called when transitioning from CheckState to NormalPlayState.
     * Return NormalPlayState
     * @return State.
     */
    @Override
    public State check() {
        System.out.println("Player" + game.getCurrentPlayer() + "is not checked now.");
        System.out.println("Transitioning to Normal Play State.");
        return new NormalPlayState(game);
    }

    /**
     * This function will throw an exception because player is checked now.
     */
    @Override
    public State playerTurnSwitch() {
        throw new UnsupportedOperationException("Please make your move. ");
    }
}
