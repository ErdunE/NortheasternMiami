package states;

import main.ChessGame;

public class PlayerTurnState extends State {
    public PlayerTurnState(ChessGame game) {
        super(game);
    }

    @Override
    public State startGame() {
        throw new UnsupportedOperationException("Game already started.");
    }

    @Override
    public State normalPlay() {
        System.out.println("Player turn ended. ");
        System.out.println("Transitioning to NormalPlayState.");
        return new NormalPlayState(game);
    }

    @Override
    public State checkMate() {
        throw new UnsupportedOperationException("Cannot checkmate in PlayerTurnState.");
    }

    @Override
    public State check() {
        throw new UnsupportedOperationException("Cannot check in PlayerTurnState.");
    }

    @Override
    public State PlayerTurnSwitch() {
        System.out.println("Switching player to " + game.getCurrentPlayer() + " turn.");
        System.out.println("Transitioning to NormalPlayState.");
        return new NormalPlayState(game);
    }
}