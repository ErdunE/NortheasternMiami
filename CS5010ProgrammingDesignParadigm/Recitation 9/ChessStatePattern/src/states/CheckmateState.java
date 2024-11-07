package states;

import main.ChessGame;

public class CheckmateState extends State {
    public CheckmateState(ChessGame game) {
        super(game);
    }

    @Override
    public State startGame() {
        throw new UnsupportedOperationException("Game already started.");
    }

    @Override
    public State normalPlay() {
        throw new UnsupportedOperationException("Cannot play in CheckmateState.");
    }

    @Override
    public State checkMate() {
        System.out.println("Game Over. Checkmate!");
        System.out.println("Winner is player " + game.getCurrentPlayer() + ". Congrats!!!!");
        System.out.println("Exiting the game...");
        return this;
    }

    @Override
    public State check() {
        throw new UnsupportedOperationException("Cannot check in CheckmateState.");
    }

    @Override
    public State PlayerTurnSwitch() {
        throw new UnsupportedOperationException("Cannot switch turns in CheckmateState.");
    }
}