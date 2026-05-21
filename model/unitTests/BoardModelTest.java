package model.unitTests;

import model.BoardModel;
import model.Tile;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class BoardModelTest {

    private BoardModel createBoard(int[][] ids) {
    BoardModel model = new BoardModel(ids.length, ids[0].length, 3);

    for (int row = 0; row < ids.length; row++) {
        for (int col = 0; col < ids[row].length; col++) {
            model.board[row][col] = new Tile(ids[row][col]);
        }
    }

    return model;
}

    private void assertBoardEquals(int[][] expected, BoardModel model) {
        for (int row = 0; row < expected.length; row++) {
            for (int col = 0; col < expected[row].length; col++) {
                assertEquals(
                        expected[row][col],
                        model.board[row][col].getColorID(),
                        "Wrong value at row " + row + ", col " + col
                );
            }
        }
    }

    @Test
    void searchConnected() {
        BoardModel model = createBoard(new int[][] {
                {1, 1, 2},
                {1, 2, 2},
                {3, 3, 2}
        });

        ArrayList<Point> result = model.searchConnected(0, 0);

        assertEquals(3, result.size());
        assertTrue(result.contains(new Point(0, 0)));
        assertTrue(result.contains(new Point(0, 1)));
        assertTrue(result.contains(new Point(1, 0)));
    }

    @Test
    void gravityFalls() {
        BoardModel model = createBoard(new int[][] {
                {0, 1},
                {2, 0},
                {3, 4}
        });

        model.gravityFalls();

        assertBoardEquals(new int[][] {
                {0, 0},
                {2, 1},
                {3, 4}
        }, model);
    }

    @Test
    void shiftLeft() {
        BoardModel model = createBoard(new int[][] {
                {1, 0, 2},
                {1, 0, 2}
        });

        model.shiftLeft();

        assertBoardEquals(new int[][] {
                {1, 2, 0},
                {1, 2, 0}
        }, model);
    }

    @Test
    void hasMovesVerticalMatch() {
        BoardModel model = createBoard(new int[][] {
                {1, 2},
                {1, 3}
        });

        assertTrue(model.hasMoves());
    }

    @Test
    void hasMovesHorizontalMatch() {
        BoardModel model = createBoard(new int[][] {
                {1, 1},
                {2, 3}
        });

        assertTrue(model.hasMoves());
    }

    @Test
    void win() {
        BoardModel model = createBoard(new int[][] {
                {1, 2},
                {0, 3}
        });

        assertTrue(model.win());
    }
}