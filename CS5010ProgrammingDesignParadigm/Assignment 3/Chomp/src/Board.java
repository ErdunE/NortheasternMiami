import javax.swing.*;
import java.awt.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * The Board class represents the game view for the Chomp game.
 * It displays the grid of pieces, controls game information display, and allows
 * for grid customization with a slider.
 *
 * <p><b>Expected Design:</b> According to the provided class diagram, the Board
 * class is responsible for displaying the game board, managing piece states, and
 * updating player turns.</p>
 *
 * <p><b>Deviations and Extensions:</b> Additional features, such as the slider for
 * board size customization and a rules label, were added to enhance user experience.
 * The `eat` method was implemented with row and column parameters instead of an array,
 * simplifying usage. Methods such as `startNewGame` and `initializeBoardPanel` were also
 * added to handle game reset and board setup modular.</p>
 *
 * @author Erdun E
 * @since 2024-11-12
 * @version 1.1
 */
public class Board {
    private JFrame boardFrame; // Main window containing the game interface
    private Piece[][] grid; // Grid of game pieces
    private JLabel output; // Label to display the current player turn or game messages
    private Controller controller; // Reference to the game controller
    private JPanel boardPanel; // Panel holding the grid of pieces
    private JSlider slider; // Slider to control the number of columns in the grid
    private JLabel rulesLabel; // Label displaying game rules

    // Default number of rows and cols
    private int rows = 4;
    private int cols = 7;

    /**
     * Constructor for the Board class.
     * Initializes the controller reference and player output label.
     *
     * @param controller The controller that manages game logic.
     */
    public Board(Controller controller) {
        this.controller = controller;
        this.output = new JLabel("Player 1 Turn");
    }

    /**
     * Resets the game board and player turn for a new game.
     * <p>
     * <b>Reason for Addition:</b> This method was added to allow users to restart
     * the game without closing and reopening the application.
     */
    public void startNewGame() {
        output.setText("Player 1 Turn");
        initializeBoardPanel();
        controller.resetPlayer();
    }

    /**
     * Creates the main game window and sets up the board layout.
     * <p>
     * <b>Expected Design:</b> This method sets up the board layout including grid,
     * slider, and rule displays. Adheres to expected design with added enhancements
     * like a rules label and slider.
     */
    public void createBoard() {
        boardFrame = new JFrame("Chomp Game");
        boardFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        boardFrame.setLayout(new BorderLayout());

        // Create rules label and add to the top of the frame
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

        // Game panel to hold output label and board grid
        JPanel gamePanel = new JPanel(new BorderLayout());
        gamePanel.add(output, BorderLayout.NORTH);
        initializeBoardPanel();
        gamePanel.add(boardPanel, BorderLayout.CENTER);

        // Slider for adjusting the number of columns
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

    /**
     * Initializes or refreshes the game board grid based on the current row and column values.
     * <p>
     * <b>Reason for Addition:</b> This method encapsulates board setup logic, making it
     * easy to reset the board or adjust its size based on the slider value.
     */
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

    /**
     * Updates the output label with a specified message.
     *
     * @param message The message to display.
     */
    public void updateMessage(String message) {
        output.setText(message);
    }

    /**
     * Processes a player's move, marking pieces as eaten and updating the board.
     * <p>
     * <b>Expected Design:</b> This method was expected to disable buttons to the right
     * and below the selected piece. Implemented as expected, but uses separate row and
     * column parameters instead of an array.
     *
     * @param row The row index of the selected piece.
     * @param col The column index of the selected piece.
     */
    public void eat(int row, int col) {
        for (int r = row; r < grid.length; r++) {
            for (int c = col; c < grid[r].length; c++) {
                grid[r][c].setEaten(true);
            }
        }
        controller.switchPlayer();
    }

    /**
     * Ends the game by calling the controller's `gameEnd` method.
     */
    public void gameOver() {
        controller.gameEnd();
    }

    /**
     * Inner class to handle slider changes, allowing real-time adjustment of the grid columns.
     */
    private class SliderChangeListener implements ChangeListener {
        @Override
        public void stateChanged(ChangeEvent e) {
            cols = slider.getValue();
            initializeBoardPanel();
        }
    }
}
