package needlemanWunsch;

import sequenceAlign.SequenceAlign;
import util.Cell;

/**
 * Created with IntelliJ IDEA.
 * User: akin
 * Date: 2/28/13
 * Time: 5:52 PM
 * To change this template use File | Settings | File Templates.
 */
public class NeedlemanWunsch extends SequenceAlign {
    public NeedlemanWunsch(String sequence1, String sequence2) {
        super(sequence1, sequence2);
    }

    public NeedlemanWunsch(String sequence1, String sequence2, int match,
                           int mismatch, int gap) {
        super(sequence1, sequence2, match, mismatch, gap);
    }

    protected void fillInCell(Cell currentCell, Cell cellAbove, Cell cellToLeft,
                              Cell cellAboveLeft) {
        int rowSpaceScore = cellAbove.getScore() + getSpace();
        int colSpaceScore = cellToLeft.getScore() + getSpace();
        int matchOrMismatchScore = cellAboveLeft.getScore();
        if (getSequence2().charAt(currentCell.getRow() - 1) == getSequence1()
                .charAt(currentCell.getColumn() - 1)) {
            matchOrMismatchScore += getMatch();
        } else {
            matchOrMismatchScore += getMismatch();
        }
        if (rowSpaceScore >= colSpaceScore) {
            if (matchOrMismatchScore >= rowSpaceScore) {
                currentCell.setScore(matchOrMismatchScore);
                currentCell.setPrevCell(cellAboveLeft);
            } else {
                currentCell.setScore(rowSpaceScore);
                currentCell.setPrevCell(cellAbove);
            }
        } else {
            if (matchOrMismatchScore >= colSpaceScore) {
                currentCell.setScore(matchOrMismatchScore);
                currentCell.setPrevCell(cellAboveLeft);
            } else {
                currentCell.setScore(colSpaceScore);
                currentCell.setPrevCell(cellToLeft);
            }
        }
    }

    @Override
    protected boolean traceBackIsNotDone(Cell currentCell) {
        return currentCell.getPrevCell() != null;
    }

    @Override
    protected Cell getTracebackStartingCell() {
        return scoreTable[scoreTable.length - 1][scoreTable[0].length - 1];
    }

    @Override
    public String toString() {
        return "[NeedlemanWunsch: sequence1=" + getSequence1() + ", sequence2="
                + getSequence2()+ "]";
    }

    protected Cell getInitialPointer(int row, int col) {
        if (row == 0 && col != 0) {
            return scoreTable[row][col - 1];
        } else if (col == 0 && row != 0) {
            return scoreTable[row - 1][col];
        } else {
            return null;
        }
    }

    protected int getInitialScore(int row, int col) {
        if (row == 0 && col != 0) {
            return col * getSpace();
        } else if (col == 0 && row != 0) {
            return row * getSpace();
        } else {
            return 0;
        }
    }
}



