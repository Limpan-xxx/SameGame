package Model;

import Model.Observers.GameObserver;

import java.awt.*;
import java.util.ArrayList;

public class BoardModel {
    private Tile[][] board;
    private final int gridColumns;
    private final int gridRows;

    private ArrayList<GameObserver> observers = new ArrayList<>();

    public BoardModel(int gridRows, int gridColumns) {
        this.board = new Tile[gridRows][gridColumns];
        this.gridRows = gridRows;
        this.gridColumns = gridColumns;
        for (int i = 0; i < gridRows; i++) {
            System.out.println();
            for (int j = 0; j < gridColumns; j++) {
                int ID = randomColorID();
                board[i][j] = new Tile(ID);
                System.out.print(ID + " ");
            }
        }
    }

    /**
     *
     * @param observer the observer of the game, Tile[][] board
     */
    public void addObserver(GameObserver observer) {
        observers.add(observer);
    }

    /**
     * notifies observers that Tile[][] board has changed
     */
    private void notifyObservers() {
        for (GameObserver observer : observers) {
            observer.boardChanged();
        }
    }

    /**
     *
     * @param row index to tile
     * @param column index to tile
     * @return the finished ArrayList filled with index to connected Tiles
     */
    public ArrayList<Point> searchConnected(int row, int column) {
        ArrayList<Point> connectedCoordinates = new ArrayList<>();
        searchConnected(row, column, connectedCoordinates);
        return connectedCoordinates;
    }

    /**
     *
     * @param row index to tile
     * @param column index to tile
     * @param connectedCoordinates ArrayList filled with index to connected Tiles, recursive
     */
    private void searchConnected(int row, int column, ArrayList<Point> connectedCoordinates) {
        Point currentPoint = new Point(row, column);

        if (connectedCoordinates.contains(currentPoint)) {
            return;
        }

        connectedCoordinates.add(currentPoint);

        int colorID = this.board[row][column].getColorID();

        if (isTile(row - 1, column)
                && this.board[row - 1][column].getColorID() == colorID) {
            searchConnected(row - 1, column, connectedCoordinates);
        }

        if (isTile(row + 1, column)
                && this.board[row + 1][column].getColorID() == colorID) {
            searchConnected(row + 1, column, connectedCoordinates);
        }

        if (isTile(row, column - 1)
                && this.board[row][column - 1].getColorID() == colorID) {
            searchConnected(row, column - 1, connectedCoordinates);
        }

        if (isTile(row, column + 1)
                && this.board[row][column + 1].getColorID() == colorID) {
            searchConnected(row, column + 1, connectedCoordinates);
        }
    }

    /**
     *
     * @param row index on board
     * @param column index on board
     * @return boolean value if index belongs to board[][]
     */
    public boolean isTile(int row, int column) {
        return row < board.length && column < board[0].length && row >= 0 && column >= 0;
    }

    /**
     * Removes tile from board
     * @param row index to tile to remove
     * @param column index to tile to remove
     */
    public void removeTile(int row, int column) {
        System.out.println();
        System.out.println();
        this.board[row][column].setColorID(0); // sets the tile to DARK_GRAY

        // för debug konsol
        System.out.println("removed " + row + " " + column);
        for (int i = 0; i < gridRows; i++) {
            System.out.println();
            for (int j = 0; j < gridColumns; j++) {
                System.out.print(board[i][j].getColorID() + " ");
            }
        }

        notifyObservers();
    }

    /**
     *
     * @return the main playboard
     */
    public Tile[][] getBoard() {
        return board;
    }

    /**
     * @return random colorID
     */
    private int randomColorID() {
        return (int) (Math.random() * 3) + 1; // 1=red, 2=green, 3=blue
    }

    /**
     * @return gridColumns
     */
    public int getGridColumns() {
        return gridColumns;
    }

    /**
     * @return gridRows
     */
    public int getGridRows() {
        return gridRows;
    }
}
