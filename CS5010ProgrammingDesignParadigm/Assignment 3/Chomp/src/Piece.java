import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Piece {
    private JPanel panel;
    private JButton button;
    private ImageIcon chocolate, empty, poison;
    private boolean eaten = false;
    private int row, col;
    private Board board;

    public Piece(int row, int col, Board board) {
        this.row = row;
        this.col = col;
        this.board = board;

        panel = new JPanel();
        button = new JButton();

        chocolate = new ImageIcon(getClass().getResource("/resources/chocolate.jpg")); // 初始显示巧克力
        empty = new ImageIcon(getClass().getResource("/resources/empty.jpg"));
        poison = new ImageIcon(getClass().getResource("/resources/poison.jpg"));

        if (row == 0 && col == 0) {
            button.setIcon(poison);
        } else {
            button.setIcon(chocolate);
        }

        button.addActionListener(new ButtonClickListener());
        panel.add(button);
    }

    public JPanel getPanel() {
        return panel;
    }

    public void setEaten(boolean eaten) {
        this.eaten = eaten;
        button.setEnabled(false);
        button.setIcon(empty);
    }

    private class ButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (!eaten) {
                board.eat(row, col);
                if (row == 0 && col == 0) {
                    board.gameOver();
                }
            }
        }
    }
}