package sequenceAlign;

import util.Cell;
import util.DynamicProgramming;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created with IntelliJ IDEA.
 * User: akin
 * Date: 2/28/13
 * Time: 5:15 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class SequenceAlign extends DynamicProgramming{
    public int getMatch() {
        return match;
    }

    public void setMatch(int match) {
        this.match = match;
    }

    public int getMismatch() {
        return mismatch;
    }

    public void setMismatch(int mismatch) {
        this.mismatch = mismatch;
    }

    public int getSpace() {
        return space;
    }

    public void setSpace(int space) {
        this.space = space;
    }

    public String[] getAlignments() {
        return alignments;
    }

    public void setAlignments(String[] alignments) {
        this.alignments = alignments;
    }
    public void setAlignments(String alignment)   {
        ArrayList<String>a;
        a = new ArrayList<String>(getAlignments().length);
        Collections.addAll(a, alignments);
        a.add(alignment);
        setAlignments((String[])a.toArray());
    }

    private int match;
    private int mismatch;
    private int space;
    private String[] alignments;


    public SequenceAlign(String sequence1, String sequence2) {
        this(sequence1, sequence2, 1, -1, -1);
    }

    public SequenceAlign(String sequence1, String sequence2, int match,
                             int mismatch, int gap) {
        super(sequence1, sequence2);

        setMatch(match);
        setMismatch(mismatch);
        setSpace(gap);
    }@Override
    protected int getInitialScore(int row, int col) {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }
    protected Object getTraceback() {
        StringBuffer align1Buf = new StringBuffer();
        StringBuffer align2Buf = new StringBuffer();
        Cell currentCell = getTracebackStartingCell();
        while (traceBackIsNotDone(currentCell)) {
            if (currentCell.getRow() - currentCell.getPrevCell().getRow() == 1) {
                align2Buf.insert(0, getSequence2().charAt(currentCell.getRow() - 1));
            } else {
                align2Buf.insert(0, '-');
            }
            if (currentCell.getColumn() - currentCell.getPrevCell().getColumn() == 1) {
                align1Buf.insert(0, getSequence1().charAt(currentCell.getColumn() - 1));
            } else {
                align1Buf.insert(0, '-');
            }
            currentCell = currentCell.getPrevCell();
        }

        String[] alignments = new String[] { align1Buf.toString(),
                align2Buf.toString() };

        return alignments;
    }

    protected abstract boolean traceBackIsNotDone(Cell currentCell);

    protected abstract Cell getTracebackStartingCell();

    public int getAlignmentScore() {
        if (alignments == null) {
            getAlignment();
        }

        int score = 0;
        for (int i = 0; i < alignments[0].length(); i++) {
            char c1 = alignments[0].charAt(i);
            char c2 = alignments[1].charAt(i);
            if (c1 == '-' || c2 == '-') {
                score += space;
            } else if (c1 == c2) {
                score += match;
            } else {
                score += mismatch;
            }
        }

        return score;
    }
    public String[] getAlignment() {
        ensureTableIsFilledIn();
        alignments = (String[]) getTraceback();
        return alignments;
    }

}
