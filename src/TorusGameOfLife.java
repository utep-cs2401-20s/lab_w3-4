public class TorusGameOfLife extends GameOfLife {
    /* ****************************** Constructors ****************************** */
    public TorusGameOfLife() {
        super();
    }

    public TorusGameOfLife(int s) {
        super(s);
    }

    public TorusGameOfLife(int[][] b) {
        super(b);
    }

    @Override
    public int neighbors(int r, int c) {
        //get previous and size from previous
        int[][] b = getPrevious();
        int s = getSize();
        int n = 0;
        //count for each cell
        for ( int i=r-1 ; i<=r+1 ; i++ )
            for ( int j=c-1 ; j<=c+1 ; j++ )
                if ( i!=r && j!=c )
                    n += (b[(i+s)%s][(j+s)%s]==getAlive()) ? 1 : 0; //increment if alive
        return n;
    }
}
