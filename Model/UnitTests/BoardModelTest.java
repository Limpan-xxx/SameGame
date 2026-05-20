package Model.UnitTests;

import Model.BoardModel;
import Model.Tile;
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

    @Test
    void searchConnectedShouldFindConnectedTilesWithSameColor() {
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
}