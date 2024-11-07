package states;

import main.ChessGame;

public class PlayerTurnSwitchState extends State {
    public PlayerTurnSwitchState(ChessGame game) {
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
        throw new UnsupportedOperationException("Cannot checkmate in PlayerTurnSwitchState.");
    }

    @Override
    public State check() {
        throw new UnsupportedOperationException("Cannot check in PlayerTurnSwitchState.");
    }

    @Override
    public State PlayerTurnSwitch() {
        System.out.println("Switching player to " + game.getCurrentPlayer() + " turn.");
        System.out.println("Transitioning to NormalPlayState.");
        return new NormalPlayState(game);
    }
}