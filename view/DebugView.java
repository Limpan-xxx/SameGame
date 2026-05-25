package view;

import model.observers.DebugObserver;
import model.Tile;

import java.awt.*;
import java.util.ArrayList;

public class DebugView implements DebugObserver {

    public static void printSectionStart(String title) {
        System.out.println();
        System.out.println("============== " + title + " ==============");
    }

    public static void printSectionEnd(String title) {
        System.out.println("============== " + title + " ==============");
    }

    public static void printLine(String message) {
        System.out.println(message);
    }

    public static void printHorizontalLine(String message) {
        System.out.print(message + " ");
    }

    public static void seperator() {
        System.out.println();
    }

    /**
     * prints the current gameboard
     * @param board the gameboard
     */
    @Override
    public void currentBoard(Tile[][] board) {
        printSectionStart("GAMEBOARD");
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                printHorizontalLine(String.valueOf(board[i][j].getColorID()));
            }
            seperator();
        }
        printSectionEnd("GAMEBOARD");
    }

    /**
     * prints the index of the tile that was clicked
     * @param row the row of the clicked tile
     * @param column the column of the clicked tile
     * @param colorID the color id of the tile
     */
    @Override
    public void tileClicked(int row, int column, int colorID) {
        printSectionStart("DEBUG START");

        printSectionStart("CLICK EVENT");
        printLine("Clicked tile: row=" + row +
                " column=" + column +
                " color=" + colorID);
        printSectionEnd("CLICK EVENT");
    }

    /**
     * prints the index of the removed tiles
     * @param neighbors the array filled with index of the different neighbors
     * @param board for usage of getColorID()
     */
    @Override
    public void tilesRemoved(ArrayList<Point> neighbors, Tile[][] board) {
        printSectionStart("REMOVED TILES");
        for (Point p : neighbors) {
            printLine("Removed tile: row=" + p.x + " column=" + p.y + " color="
                    + board[p.x][p.y].getColorID());
        }
        printSectionEnd("REMOVED TILES");
    }

    /**
     * tellse which columns that have fallen down
     * @param fallenTilesInColumn the list of collapsed columns
     */
    @Override
    public void gravityApplied(ArrayList<Integer> fallenTilesInColumn) {
        printSectionStart("GRAVITY FALLS");
        for (Integer i: fallenTilesInColumn){
            printLine("Tiles in column " + i + " shifted down");
        }
        printSectionEnd("GRAVITY FALLS");
    }

    /**
     * prints the columns that has moved
     * @param XmovedToY the list with a point where the point represents: x=startingColumn, y=endColumn
     */
    @Override
    public void Shiftedleft(ArrayList<Point> XmovedToY) {
        printSectionStart("SHIFT LEFT");
        for(Point pair : XmovedToY){
            printLine("Shifting column " + pair.x + " to " + pair.y);
        }
        printSectionEnd("SHIFT LEFT");
    }

    /**
     * prints the score that has updated
     * @param lastMoveScore the score from the last move
     * @param currentScore the score right now
     */
    @Override
    public void scoreUpdated(int lastMoveScore, int currentScore) {
        printSectionStart("SCORE UPDATES");
        printLine("Score increased by: " + lastMoveScore);
        printLine("Current Score: " + currentScore);
        printSectionEnd("SCORE UPDATES");
        printSectionStart("DEBUG END");
    }
}
