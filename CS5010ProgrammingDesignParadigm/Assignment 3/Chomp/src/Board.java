import javax.swing.*;
import java.awt.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Board {
    private JFrame boardFrame;
    private Piece[][] grid;
    private JLabel output;
    private Controller controller;
    private JPanel boardPanel;
    private JSlider slider;
    private JLabel rulesLabel;

    private int rows = 4;
    private int cols = 7;

    public Board(Controller controller) {
        this.controller = controller;
        this.output = new JLabel("Player 1 Turn");
    }

    public void startNewGame() {
        output.setText("Player 1 Turn");
        initializeBoardPanel();
        controller.resetPlayer();
    }

    public void createBoard() {
        boardFrame = new JFrame("Chomp Game");
        boardFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        boardFrame.setLayout(new BorderLayout());

        JPanel rulesPanel = new JPanel();
        rulesLabel = new JLabel("<html><div style='text-align: center;'>"
                + "The game of Chomp is like Russian Roulette for chocolate lovers.<br><br>"
                + "A move consists of chomping a square out of the chocolate bar<br>"
                + "along with any squares to the right and below.<br><br>"
                + "The upper left square is poisoned though and the player forced to chomp it loses.<br><br>"
                + "Player 1 move first then player 2. <br>"
                + "Players alternate moves. <br><br>"
                + "You can adjust the size of the board via the slider in the bottom.<br><br>"
                + "Try your luck against your friend.<br>"
                + "You chomp on a square by clicking on the square with the mouse.<br>"
                + "And have fun!<br>"
                + "</div></html>");
        rulesLabel.setHorizontalAlignment(SwingConstants.CENTER);
        rulesPanel.add(rulesLabel);
        boardFrame.add(rulesPanel, BorderLayout.NORTH);

        JPanel gamePanel = new JPanel(new BorderLayout());
        gamePanel.add(output, BorderLayout.NORTH);
        initializeBoardPanel();
        gamePanel.add(boardPanel, BorderLayout.CENTER);

        slider = new JSlider(3, 10, cols);
        slider.setMajorTickSpacing(1);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.addChangeListener(new SliderChangeListener());

        boardFrame.add(gamePanel, BorderLayout.CENTER);
        boardFrame.add(slider, BorderLayout.SOUTH);
        boardFrame.pack();
        boardFrame.setLocationRelativeTo(null);
        boardFrame.setResizable(true);
        boardFrame.setVisible(true);
    }

    private void initializeBoardPanel() {
        if (boardPanel != null) {
            boardPanel.removeAll();
        } else {
            boardPanel = new JPanel();
        }

        boardPanel.setLayout(new GridLayout(rows, cols, 2, 2));
        boardPanel.setPreferredSize(new Dimension(896, 400));

        grid = new Piece[rows][cols];
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                grid[row][col] = new Piece(row, col, this);
                boardPanel.add(grid[row][col].getPanel());
            }
        }

        boardPanel.revalidate();
        boardPanel.repaint();

        boardFrame.revalidate();
        boardFrame.repaint();
    }

    public void updateMessage(String message) {
        output.setText(message);
    }

    public void eat(int row, int col) {
        for (int r = row; r < grid.length; r++) {
            for (int c = col; c < grid[r].length; c++) {
                grid[r][c].setEaten(true);
            }
        }
        controller.switchPlayer();
    }

    public void gameOver() {
        controller.gameEnd();
    }

    private class SliderChangeListener implements ChangeListener {
        @Override
        public void stateChanged(ChangeEvent e) {
            cols = slider.getValue();
            initializeBoardPanel();
        }
    }
}
