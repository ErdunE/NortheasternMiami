package states;

import main.ChessGame;

public class GameStartState extends State {

    public GameStartState(ChessGame game) {
        super(game);
    }

    @Override
    public State startGame() {
        System.out.println("Game is starting.");
        System.out.println("Transitioning to NormalPlayState.");
        System.out.println("=============================================");
        return new NormalPlayState(game);
    }

    @Override
    public State normalPlay() {
        throw new UnsupportedOperationException("Cannot play in GameStartState.");
    }

    @Override
    public State PlayerTurnSwitch() {
        throw new UnsupportedOperationException("Cannot switch turns in GameStartState.");
    }

    @Override
    public State check() {
        throw new UnsupportedOperationException("Cannot check in GameStartState.");
    }

    @Override
    public State checkMate() {
        throw new UnsupportedOperationException("Cannot checkmate in GameStartState.");
    }
}