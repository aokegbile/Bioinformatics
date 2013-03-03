package util;

/**
 * Created with IntelliJ IDEA.
 * User: akin
 * Date: 2/28/13
 * Time: 12:32 AM
 * To change this template use File | Settings | File Templates.
 */
public abstract class DynamicProgramming {

    /**
     * sequence 1
     */
    private String sequence1;
    /**
     * sequence 2
     */
    private String sequence2;
    /**
     * Table containing score
     */
    protected Cell[][] scoreTable;
    /**
     * True if table is filled in
     */
    private Boolean tableIsFilledIn = false;
    /**
     * true if table is initialized
     */
    private Boolean isInitialized= false;

    //Getters and Setters
    public String getSequence1() {
        return sequence1;
    }
    public void setSequence1(String sequence1) {
        this.sequence1 = sequence1;
    }
    public String getSequence2() {
        return sequence2;
    }
    public void setSequence2(String sequence2) {
        this.sequence2 = sequence2;
    }
    public Cell[][] getScoreTable() {
        return scoreTable;
    }
    public void setScoreTable(Cell[][] scoreTable) {
        this.scoreTable = scoreTable;
    }
    public Boolean getTableIsFilledIn() {
        return tableIsFilledIn;
    }
    public void setTableIsFilledIn(Boolean tableIsFilledIn) {
        this.tableIsFilledIn = tableIsFilledIn;
    }
    public Boolean getInitialized() {
        return isInitialized;
    }
    public void setInitialized(Boolean initialized) {
        isInitialized = initialized;
    }

    /**
     * constructor for Dynamic Programming
     * @param sequence1
     * @param sequence2
     */
    public DynamicProgramming(String sequence1, String sequence2) {
        setSequence1(sequence1);
        setSequence2(sequence2);
        setScoreTable(new Cell[sequence2.length() + 1][sequence1.length() + 1]  );
    }

    /**
     * initialize table with valid scores
     */
    protected void initialize() {
        for (int i = 0; i < scoreTable.length; i++) {
            for (int j = 0; j < scoreTable[i].length; j++) {
                scoreTable[i][j] = new Cell(i, j);
            }
        }
        initializeScores();
        initializePointers();



        setInitialized(Boolean.TRUE);
    }

    /**
     * make sure trace back happens by making sure the cells know where there previous cell is
     */
    protected void initializePointers() {
        for (int i = 0; i < scoreTable.length; i++) {
            for (int j = 0; j < scoreTable[i].length; j++) {
                scoreTable[i][j].setPrevCell(getInitialPointer(i, j));
            }
        }
    }

    /**
     * Fill in entire score table
     */
    protected void fillIn() {
        for (int row = 1; row < scoreTable.length; row++) {
            for (int col = 1; col < scoreTable[row].length; col++) {
                Cell currentCell = scoreTable[row][col];
                Cell cellAbove = scoreTable[row - 1][col];
                Cell cellToLeft = scoreTable[row][col - 1];
                Cell cellAboveLeft = scoreTable[row - 1][col - 1];
                fillInCell(currentCell, cellAbove, cellToLeft, cellAboveLeft);
            }
        }

        tableIsFilledIn = true;
    }

    /**
     * Make sure table is Valid
     */
    protected void ensureTableIsFilledIn() {
        if (!isInitialized) {
            initialize();
        }
        if (!tableIsFilledIn) {
            fillIn();
        }
    }

    /**
     * set the score of each individual cell
     */
    protected void initializeScores() {
        for (int i = 0; i < scoreTable.length; i++) {
            for (int j = 0; j < scoreTable[i].length; j++) {
                scoreTable[i][j].setScore(getInitialScore(i, j));
            }
        }
    }


    /**
     * to be implemented in child classes
     * @param row
     * @param col
     * @return
     */
    protected abstract int getInitialScore(int row, int col);

    /**
     *  to be implemented by child classes
     * @param row
     * @param col
     * @return
     */
    protected abstract Cell getInitialPointer(int row, int col);

    /**
     * to be implemented by child class
     * @param currentCell
     * @param cellAbove
     * @param cellToLeft
     * @param cellAboveLeft
     */
    protected abstract void fillInCell(Cell currentCell, Cell cellAbove,
                                       Cell cellToLeft, Cell cellAboveLeft);
    abstract protected Object getTraceback();

    /**
     * print table
     */
    public void printScoreTable() {
        ensureTableIsFilledIn();
        for (int i = 0; i < sequence2.length() + 2; i++) {
            for (int j = 0; j < sequence1.length() + 2; j++) {
                if (i == 0) {
                    if (j == 0 || j == 1) {
                        System.out.print("  ");
                    } else {
                        if (j == 2) {
                            System.out.print("     ");
                        } else {
                            System.out.print("   ");
                        }
                        System.out.print(sequence1.charAt(j - 2));
                    }
                } else if (j == 0) {
                    if (i == 1) {
                        System.out.print("  ");
                    } else {
                        System.out.print(" " + sequence2.charAt(i - 2));
                    }
                } else {
                    String toPrint;
                    Cell currentCell = scoreTable[i - 1][j - 1];
                    Cell prevCell = currentCell.getPrevCell();
                    if (prevCell != null) {
                        if (currentCell.getColumn() == prevCell.getColumn() + 1
                                && currentCell.getRow() == prevCell.getRow() + 1) {
                            toPrint = "\\";
                        } else if (currentCell.getColumn() == prevCell.getColumn() + 1) {
                            toPrint = "-";
                        } else {
                            toPrint = "|";
                        }
                    } else {
                        toPrint = " ";
                    }
                    int score = currentCell.getScore();
                    String s = String.format("%1$3d", score);
                    toPrint += s;
                    System.out.print(toPrint);
                }

                System.out.print(' ');
            }
            System.out.println();
        }
    }

























}
