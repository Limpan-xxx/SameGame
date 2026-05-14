package View;

import Model.Observers.DebugObserver;
import Model.Tile;

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

    @Override
    public void tileClicked(int row, int column, int colorID) {
        printSectionStart("DEBUG START");

        printSectionStart("CLICK EVENT");
        printLine("Clicked tile: row=" + row +
                " column=" + column +
                " color=" + colorID);
        printSectionEnd("CLICK EVENT");
    }

    @Override
    public void tilesRemoved(ArrayList<Point> neighbors, Tile[][] board) {
        printSectionStart("REMOVED TILES");
        for (Point p : neighbors) {
            printLine("Removed tile: row=" + p.x + " column=" + p.y + " color="
                    + board[p.x][p.y].getColorID());
        }
        printSectionEnd("REMOVED TILES");
    }

    @Override
    public void gravityApplied(ArrayList<Integer> fallenTilesInColumn) {
        printSectionStart("GRAVITY FALLS");
        for (Integer i: fallenTilesInColumn){
            printLine("Tiles in column " + i + " shifted down");
        }
        printSectionEnd("GRAVITY FALLS");
    }

    @Override
    public void Shiftedleft(ArrayList<Point> XmovedToY) {
        printSectionStart("SHIFT LEFT");
        for(Point pair : XmovedToY){
            printLine("Shifting column " + pair.x + " to " + pair.y);
        }
        printSectionEnd("SHIFT LEFT");
    }

    @Override
    public void scoreUpdated(int lastMoveScore, int currentScore) {
        printSectionStart("SCORE UPDATES");
        printLine("Score increased by: " + lastMoveScore);
        printLine("Current Score: " + currentScore);
        printSectionEnd("SCORE UPDATES");
        printSectionStart("DEBUG END");
    }
}
