import javax.swing.*;

public class Controller {
    private byte player = 0;
    private Board board;

    public void init() {
        board = new Board(this);
        board.createBoard();
        gameLoop();
    }

    private void gameLoop() {
        while (true) {
            board.repaintBoard();
        }
    }

    public void switchPlayer() {
        player = (byte) (1 - player);
        board.updateMessage("Player " + (player + 1) + " Turn");
    }

    public void resetPlayer() {
        player = 0;
    }

    public void gameEnd() {
        int result = showGameOverDialog("Game Over! Player " + (player + 1) + " loses!");

        if (result == JOptionPane.YES_OPTION) {
            board.startNewGame();
        } else if (result == JOptionPane.NO_OPTION) {
            System.exit(0);
        }
    }

    private int showGameOverDialog(String message) {
        Object[] options = {"New Game", "OK"};

        return JOptionPane.showOptionDialog(
                null,
                message,
                "Game Over",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                options,
                options[0]
        );
    }
}