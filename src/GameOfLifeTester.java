import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * NOTE TO TESTERS
 * Do not run all tests at once, as boards are static AND dependent on previous values.
 * Run each test individually.
 */
class GameOfLifeTester {
    //previous for GOL
    static int[][] previous = {
            {-1,-1,-1,-1,-1},
            {-1,-1,-1,-1,-1},
            {-1,1,1,1,-1},
            {-1,-1,-1,-1,-1},
            {-1,-1,-1,-1,-1}};
    //expected for GOL oneStep
    static int[][] board = {
            {-1,-1,-1,-1,-1},
            {-1,-1,1,-1,-1},
            {-1,-1,1,-1,-1},
            {-1,-1,1,-1,-1},
            {-1,-1,-1,-1,-1}};
    //expected for GOL after 2 evolutions
    static int[][] board2 = {
            {-1,-1,-1,-1,-1},
            {-1,-1,-1,-1,-1},
            {-1,1,1,1,-1},
            {-1,-1,-1,-1,-1},
            {-1,-1,-1,-1,-1}};
    //previous for TGOL; stays same for oneStep and 2 evolutions
    static int[][] previousT = {
            {1,-1,-1,-1,-1},
            {-1,-1,-1,-1,-1},
            {-1,-1,-1,-1,-1},
            {-1,-1,-1,-1,-1},
            {-1,-1,-1,-1,1}};
    static int[][] boardT = {
            {-1,-1,-1,-1,-1},
            {-1,-1,-1,-1,-1},
            {-1,-1,-1,-1,-1},
            {-1,-1,-1,-1,-1},
            {-1,-1,-1,-1,-1}};
    static GameOfLife GOL;
    static TorusGameOfLife TGOL;

    @BeforeAll
    static void assign() {
        GOL = new GameOfLife(previous);
        TGOL = new TorusGameOfLife(previousT);
    }

    /* ******************** GameOfLife tests ******************** */
    @Test
    void GOLoneStep() {
        GOL.oneStep();
        assertArrayEquals(board,GOL.getBoard());
    }

    @Test
    void GOLneighbors() {
        assertEquals(2,GOL.neighbors(2,2));
    }

    @Test
    void GOLevolution() {
        GOL.evolution(2);
        assertArrayEquals(board2,GOL.getBoard());
    }

    /* ******************** TorusGameOfLife tests ******************** */
    @Test
    void TGOLoneStep() {
        TGOL.oneStep();
        assertArrayEquals(boardT,TGOL.getBoard());
    }

    @Test
    void TGOLneighbors() {
        assertEquals(1,TGOL.neighbors(4,4));
    }

    @Test
    void TGOLevolution() {
        TGOL.evolution(2);
        assertArrayEquals(boardT,TGOL.getBoard());
    }
}