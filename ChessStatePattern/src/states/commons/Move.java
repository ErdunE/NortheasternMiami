package states.commons;
/**
 * @author Xinyu Wang
 * @version 9/15/2024
 * Move class:
 * The {@code Move} class stores the data of each move, which include the color, row, and column.
 */
public class Move {
    String color;
    private int row;
    private int col;
    public Move(String color, int row, int col) {
        this.color = color;
        this.row = row;
        this.col = col;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
}
