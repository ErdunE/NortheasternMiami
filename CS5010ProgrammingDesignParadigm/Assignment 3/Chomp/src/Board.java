import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class Board {
    private JFrame boardFrame;
    private Piece[][] grid;
    private JLabel output;
    private Controller controller;
    private JPanel boardPanel;
    private JSlider slider;

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

        slider = new JSlider(3, 10, cols);
        slider.setMajorTickSpacing(1);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.addChangeListener(e -> {
            cols = slider.getValue();
            initializeBoardPanel();
        });

        initializeBoardPanel();

        boardFrame.add(output, BorderLayout.NORTH);
        boardFrame.add(boardPanel, BorderLayout.CENTER);
        boardFrame.add(slider, BorderLayout.SOUTH);
        boardFrame.pack();
        boardFrame.setLocationRelativeTo(null);
        boardFrame.setResizable(true);
        boardFrame.setVisible(true);
    }

    private void initializeBoardPanel() {
        if (boardPanel != null) {
            boardFrame.remove(boardPanel);
        }

        boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(rows, cols, 2, 2));
        boardPanel.setPreferredSize(new Dimension(896, 400));

        grid = new Piece[rows][cols];
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                grid[row][col] = new Piece(row, col, this);
                boardPanel.add(grid[row][col].getPanel());
            }
        }

        boardFrame.add(boardPanel, BorderLayout.CENTER);
        boardFrame.revalidate();
        boardFrame.repaint();
    }

    public void updateMessage(String message) {
        output.setText(message);
    }

    public void repaintBoard() {
        boardFrame.repaint();
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