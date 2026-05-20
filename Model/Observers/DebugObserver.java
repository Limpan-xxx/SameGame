package Model.Observers;

import Model.Tile;

import java.awt.*;
import java.util.ArrayList;

public interface DebugObserver {
    /**
     * the method that displays the current board to the console, for debug purposes
     * @param board the gameboard
     */
    void currentBoard(Tile[][] board);

    /**
     * tells which tile is clicked to the console, for debug purposes
     * @param row the row of the clicked tile
     * @param column the column of the clicked tile
     * @param colorID the color id of the tile
     */
    void tileClicked(int row, int column, int colorID);

    /**
     * tells which tiles are removed, for debug purposes
     * @param neighbors the array filled with index of the different neighbors
     * @param board for usage of getColorID()
     */
    void tilesRemoved(ArrayList<Point> neighbors, Tile[][] board);

    /**
     * tells which column that has fallen down, for debug purposes
     * @param fallenTilesInColumn the list of collapsed columns
     */
    void gravityApplied(ArrayList<Integer> fallenTilesInColumn);

    /**
     * tells which columns has moved to where, for debug purposes
     * @param XmovedToY the list with a point where the point represents: x=startingColumn, y=endColumn
     */
    void Shiftedleft(ArrayList<Point> XmovedToY);

    /**
     * tells scores, for debug purposes
     * @param lastMoveScore the score from the last move
     * @param currentScore the score right now
     */
    void scoreUpdated(int lastMoveScore, int currentScore);
}
