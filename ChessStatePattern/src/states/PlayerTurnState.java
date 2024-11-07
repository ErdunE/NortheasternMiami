package states;

public class PlayerTurnState extends State{

    /**
     * @return
     */
    @Override
    public State startGame() {
        throw new UnsupportedOperationException("Game already in progress.");
    }

    /**
     * @return
     */
    @Override
    public State normalPlay() {
        System.out.println("Player turn ended.");
        System.out.println("Switching to Normal Play State.");
        return new NormalPlayState(game);
    }

    /**
     * @return
     */
    @Override
    public State checkMate() {
        throw new UnsupportedOperationException("Cannot Checkmate, game in progress.");
    }

    /**
     * @return
     */
    @Override
    public State check() {
        throw new UnsupportedOperationException("Cannot Check, game in progress.");
    }

    /**
     * @return
     */
    @Override
    public State playerTurnSwitch() {
        //
        return null;
    }
}
