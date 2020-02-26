public class GameOfLife {
    private int size;
    private int[][] board;
    private int[][] previous;
    //universal values for DEAD or ALIVE cells
    private final int DEAD = -1;
    private final int ALIVE = 1;

    /* ****************************** Constructors ****************************** */
    public GameOfLife() {
        size = 1;
        init();
    }

    public GameOfLife(int s) {
        size = s;
        init();
    }

    public GameOfLife(int[][] b) {
        size = b.length;
        init();
        //copy each element/cell in b into previous
        for ( int i=0 ; i<size ; i++ )
            for ( int j=0 ; j<size ; j++ )
                previous[i][j] = b[i][j];
    }

    /* ****************************** Getters/Setters ****************************** */
    public int[][] getBoard() {
        return board;
    }

    public int[][] getPrevious() {
        return previous;
    }

    public int getAlive() {
        return ALIVE;
    }

    public int getSize() {
        return size;
    }

    /* ****************************** Operations ****************************** */
    /**
     * Performs one state change on the board.
     * Implements the rules of Conway's Game of life:
     *  https://en.wikipedia.org/wiki/Conway%27s_Game_of_Life
     */
    public void oneStep() {
        //update each cell to their new state
        for ( int i=0 ; i<size ; i++ )
            for ( int j=0 ; j<size ; j++ )
                board[i][j] = getState(previous[i][j], neighbors(i,j));
        prepareForNextState();
    }

    /**
     * Counts the number of neighbors around cell (r,c) that are alive.
     * @param r the row of the cell
     * @param c the column of the cell
     * @return the number of live neighbors surrounding cell (r,c)
     */
    public int neighbors(int r, int c) {
        int n = 0;
        //count for each cell
        for ( int i=r-1 ; i<=r+1 ; i++ )
            for ( int j=c-1 ; j<=c+1 ; j++ )
                if ( isValidCell(i,j,r,c) )
                    n += (previous[i][j]==ALIVE) ? 1 : 0; //increment if alive
        return n;
    }

    /**
     * Calls oneStep e times to perform an evolution.
     * @param e the number of oneStep calls to perform
     */
    public void evolution(int e) {
        while ( e-->0 )
            oneStep();
    }

    /* ****************************** Helper Methods ****************************** */
    /**
     * Initializes the previous board to random states for each cell.
     * Initializes the board to empty cells.
     */
    private void init() {
        java.util.Random rng = new java.util.Random();
        previous = new int[size][size];
        board = new int[size][size];
        //randomly set each cell to be ALIVE or DEAD
        for ( int i=0 ; i<size ; i++ )
            for ( int j=0 ; j<size ; j++ )
                previous[i][j] = rng.nextBoolean() ? DEAD : ALIVE;
    }

    /**
     * Determines whether the cell to check is out of bounds or not itself.
     * @param i the cell to check, row
     * @param j the cell to check, col
     * @param r the cell whose neighbors we want to check, r
     * @param c the cell whose neighbors we want to check, c
     * @return true if the cell can be checked
     */
    private boolean isValidCell(int i, int j, int r, int c) {
        boolean isValidCol = j>=0 && j<size;
        boolean isValidRow = i>=0 && i<size;
        boolean isSameCell = i==r && j==c;
        return isValidRow && isValidCol && !isSameCell;
    }

    /**
     * Checks the next state value given the current state and the live neighbors
     * @param prev the previous state (DEAD or ALIVE)
     * @param n the number of live neighbors
     * @return the next state (DEAD or ALIVE) of this cell
     */
    private int getState(int prev, int n) {
        switch ( n ) {
            case 2:
                //Any live cell with two or three neighbors survives.
                return prev;
            case 3:
                //Any live cell with two or three neighbors survives
                //Any dead cell with three live neighbors becomes a live cell
                return ALIVE;
            default:
                //All other live cells die in the next generation
                //Similarly, all other dead cells stay dead
                return DEAD;
        }
    }

    /**
     * Prepares previous for next state to be ready for evaluation.
     */
    private void prepareForNextState() {
        //copy from board to previous for next state
        for ( int i=0 ; i<size ; i++ )
            for ( int j=0 ; j<size ; j++ )
                previous[i][j] = board[i][j];
    }
}
