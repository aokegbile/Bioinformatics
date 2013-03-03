package util;

/**
 * Created with IntelliJ IDEA.
 * User: akin
 * Date: 2/15/13
 * Time: 12:24 AM
 * To change this template use File | Settings | File Templates.
 */

/**
 * Cell that contains row column and traces back to prev Cell
 */
public class Cell {
    private Cell prevCell;
    private int score;
    private int row;
    private int column;

    /**
     * Creates cell object with  int row and int column
     * @param row
     * @param column
     */
    public Cell(int row, int column) {
        setRow(row);
        setColumn(column);
    }

    /**
     * setter
     * @param score the score to set
     */
    public void setScore(int score) {
        this.score = score;
    }
    /**
     * getter
     * @return the score
     */
    public int getScore() {
        return score;
    }

    /**
     * @param prevCell
     *           the prevCell to set
     */
    public void setPrevCell(Cell prevCell) {
        this.prevCell = prevCell;
    }

    /**
     * getter
     * @return the row
     */
    public int getRow() {
        return row;
    }

    /**
     * getter
     * @return the col
     */
    public int getColumn() {
        return column;
    }

    /**
     * setter
     * @param row
     */
    public void setRow(int row){
        this.row = row;
    }

    /**
     * setter for column
     * @param column
     */
    public void setColumn(int column){this.column = column;}

    /**
     * getter for prevCell
     * @return the prevCell
     */
    public Cell getPrevCell() {
        return prevCell;
    }
    @Override
    public String toString() {
        return "Cell(" + row + ", " + column + "): score=" + score + ", prevCell="
                + prevCell + "]";
    }
}
