package Model;

import Model.Observers.GameObserver;
import java.awt.*;
import java.util.ArrayList;
import java.util.Stack;

public class BoardModel {
    private Tile[][] board;
    private final int gridColumns;
    private final int gridRows;

    private ArrayList<GameObserver> observers = new ArrayList<>();
    private Tile[][] originalBoard;
    private Stack<Tile[][]> undoStack = new Stack<>();
    private Stack<Tile[][]> redoStack = new Stack<>();

    public BoardModel(int gridRows, int gridColumns, int numberOfColors) {
        this.board = new Tile[gridRows][gridColumns];
        this.gridRows = gridRows;
        this.gridColumns = gridColumns;

        for (int i = 0; i < gridRows; i++) {
            for (int j = 0; j < gridColumns; j++) {
                int ID = randomColorID(numberOfColors);
                board[i][j] = new Tile(ID);
            }
        }

        this.originalBoard = copyBoard(board);
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
     * @param row    index to tile
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
     * @param row                  index to tile
     * @param column               index to tile
     * @param connectedCoordinates ArrayList filled with index to connected Tiles,
     *                             recursive
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
     * makes the tiles fall down to their correct spot after removing their
     * neighbors
     */
    public void gravityFalls() {
        for (int i = 0; i < gridColumns; i++) {

            ArrayList<Integer> filledColumnColors = new ArrayList<>();

            // Samla färgerna i kolumnen, nerifrån och upp
            for (int j = gridRows - 1; j >= 0; j--) {
                if (board[j][i].getColorID() != 0) {
                    filledColumnColors.add(board[j][i].getColorID());
                }
            }

            int rowIndex = gridRows - 1;

            // Lägg tillbaka färgerna längst ner i samma kolumn
            for (int colorID : filledColumnColors) {
                board[rowIndex][i].setColorID(colorID);
                rowIndex--;
            }

            // Fyll resten ovanför med gråa rutor
            while (rowIndex >= 0) {
                board[rowIndex][i].setColorID(0);
                rowIndex--;
            }
        }
        for (int i = 0; i < gridRows; i++) {
            System.out.println();
            for (int j = 0; j < gridColumns; j++) {
                System.out.print(board[i][j].getColorID() + " ");
            }
        }
        // notifyObservers(); tror ej behövs för att det finns i slutet som är shiftLeft() som alltid körs i TileClicked()
    }

    public void shiftLeft() {
        int targetColumn = 0;

        for (int currentColumn = 0; currentColumn < gridColumns; currentColumn++) {

            if (!isColumnEmpty(currentColumn)) {

                if (currentColumn != targetColumn) {
                    moveColumn(currentColumn, targetColumn);
                    clearColumn(currentColumn);
                }

                targetColumn++;
            }
        }

        notifyObservers();
    }

    private boolean isColumnEmpty(int column) {
        for (int row = 0; row < gridRows; row++) {
            if (board[row][column].getColorID() != 0) {
                return false;
            }
        }

        return true;
    }

    private void moveColumn(int fromColumn, int toColumn) {
        for (int row = 0; row < gridRows; row++) {
            board[row][toColumn].setColorID(board[row][fromColumn].getColorID());
        }
    }

    private void clearColumn(int column) {
        for (int row = 0; row < gridRows; row++) {
            board[row][column].setColorID(0);
        }
    }

    /**
     *
     * @param row    index on board
     * @param column index on board
     * @return boolean value if index belongs to board[][]
     */
    public boolean isTile(int row, int column) {
        return row < board.length && column < board[0].length && row >= 0 && column >= 0;
    }

    /**
     * Removes tile from board
     * 
     * @param row    index to tile to remove
     * @param column index to tile to remove
     */
    public void removeTile(int row, int column) {
        System.out.println();
        System.out.println();
        this.board[row][column].setColorID(0); // sets the tile to DARK_GRAY

        // för debug konsol
        System.out.println("removed " + row + " " + column);

        //notifyObservers();
    }

    /**
     *
     * @return the main playboard
     */
    public Tile[][] getBoard() {
        return board;
    }

    /**
     * @return n number of random colorID's
     */
    private int randomColorID(int n) {
        return (int) (Math.random() * n) + 1; // 1=red, 2=green, 3=blue, 4=yellow, 5=orange, 6=pink
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

    public void saveState() {
        undoStack.push(copyBoard(board));
        redoStack.clear();
    }

    public void undo() {
        if (!undoStack.isEmpty()) {
            redoStack.push(copyBoard(board));
            board = undoStack.pop();
            notifyObservers();
        }
    }

    public void redo() {
        if (!redoStack.isEmpty()) {
            undoStack.push(copyBoard(board));
            board = redoStack.pop();
            notifyObservers();
        }
    }

    public void reset() {
        undoStack.clear();
        redoStack.clear();
        board = copyBoard(originalBoard);
        notifyObservers();
    }

    private Tile[][] copyBoard(Tile[][] source) {
        Tile[][] copy = new Tile[gridRows][gridColumns];
        for (int i = 0; i < gridRows; i++) {
            for (int j = 0; j < gridColumns; j++) {
                copy[i][j] = new Tile(source[i][j].getColorID());
            }
        }
        return copy;
    }
}
