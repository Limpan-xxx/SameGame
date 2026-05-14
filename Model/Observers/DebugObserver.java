package Model.Observers;

import Model.Tile;

import java.awt.*;
import java.util.ArrayList;

public interface DebugObserver {
    void currentBoard(Tile[][] board);

    void tileClicked(int row, int column, int colorID);

    void tilesRemoved(ArrayList<Point> neighbors, Tile[][] board);

    void gravityApplied(ArrayList<Integer> fallenTilesInColumn);

    void Shiftedleft(ArrayList<Point> XmovedToY);

    void scoreUpdated(int lastMoveScore, int currentScore);
}
