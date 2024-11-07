package states;

import main.ChessGame;
import java.util.Random;

public class CheckState extends State {
    private final Random random;

    public CheckState(ChessGame game) {
        super(game);
        this.random = new Random();
    }

    @Override
    public State startGame() {
        throw new UnsupportedOperationException("Game already started.");
    }

    @Override
    public State normalPlay() {
        throw new UnsupportedOperationException("Cannot play in CheckState.");
    }

    @Override
    public State checkMate() {
        System.out.println("Player " + game.getCurrentPlayer() + " can not resolved the Check. ");
        System.out.println("Transitioning to CheckmateState.");
        return new CheckmateState(game);
    }

    @Override
    public State check() {
        System.out.println("Player " + game.getCurrentPlayer() + " resolved the Check. ");
        System.out.println("Returning to NormalPlayState.");
        return new NormalPlayState(game);

//        boolean checkResolved = random.nextDouble() > 0.3;
//        if (checkResolved) {
//            System.out.println("Player " + game.getCurrentPlayer() + " resolved the Check. ");
//            System.out.println("Returning to NormalPlayState.");
//            return new NormalPlayState(game);
//        } else {
//            System.out.println("Player " + game.getCurrentPlayer() + " can not resolved the Check. ");
//            System.out.println("Transitioning to CheckmateState.");
//            return new CheckmateState(game);
//        }
    }

    @Override
    public State PlayerTurnSwitch() {
        throw new UnsupportedOperationException("Cannot switch turns in CheckState.");
    }
}