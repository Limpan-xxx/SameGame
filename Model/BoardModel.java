package Model;

import Model.Observers.DebugObserver;
import Model.Observers.GameObserver;

import java.awt.*;
import java.util.ArrayList;
import java.util.Stack;

public class BoardModel {
    public Tile[][] board;
    private final int gridColumns;
    private final int gridRows;

    private final ArrayList<GameObserver> gameObservers = new ArrayList<>();
    private final ArrayList<DebugObserver> debugObservers = new ArrayList<>();
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
     * @param gameObserver the gameObserver of the game, Tile[][] board
     */
    public void addGameObserver(GameObserver gameObserver) {
        gameObservers.add(gameObserver);
    }

    /**
     * notifies gameObservers that Tile[][] board has changed
     */
    private void notifyGameObservers() {
        for (GameObserver observer : gameObservers) {
            observer.boardChanged();
        }
    }

    /**
     *
     * @param debugObserver the debugObserver of the game, Tile[][] board
     */
    public void addDebugObserver(DebugObserver debugObserver) {
        debugObservers.add(debugObserver);
    }

    public void removeDebugObserver(DebugObserver debugObserver) {
        debugObservers.remove(debugObserver);
    }


    /**
     * notifies debugObservers that Tile[][] board has changed
     */
    private void notifyDebugCurrentBoard() {
        for (DebugObserver observer : debugObservers) {
            observer.currentBoard(board);
        }
    }

    public void notifyDebugTileClicked(int row, int column, int colorID){
        for (DebugObserver observer : debugObservers) {
            observer.tileClicked(row, column, colorID);
        }
    }

    public void notifyDebugRemovedTile(ArrayList<Point> neighbors, Tile[][] board){
        for (DebugObserver observer : debugObservers) {
            observer.tilesRemoved(neighbors, board);
        }
    }

    private void notifyDebugShiftedLeft(ArrayList<Point> XmovedToY){
        for (DebugObserver observer : debugObservers) {
            observer.Shiftedleft(XmovedToY);
        }
    }

    private void notifyDebugGravityApplied(ArrayList<Integer> fallenTilesInColumn){
        for (DebugObserver observer : debugObservers) {
            observer.gravityApplied(fallenTilesInColumn);
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

    public boolean hasMoves() {
        for (int row = 0; row < gridRows; row++) {
            for (int column = 0; column < gridColumns; column++) {
                int colorID = this.board[row][column].getColorID();

                if (board[row][column].getColorID() == 0) {
                    continue;
                }

                if (isTile(row + 1, column)
                        && this.board[row + 1][column].getColorID() == colorID) {
                    return true;
                }

                if (isTile(row, column + 1)
                        && this.board[row][column + 1].getColorID() == colorID) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean win() {
        return board[gridRows - 1][0].getColorID() == 0;
    }

    /**
     * makes the tiles fall down to their correct spot after removing their
     * neighbors
     */
    public void gravityFalls() {
        ArrayList<Integer> fallenTilesInColumn = new ArrayList<>();

        for (int i = 0; i < gridColumns; i++) {
            boolean columnShifted = false;

            ArrayList<Integer> filledColumnColors = new ArrayList<>();

            // Samla färgerna i kolumnen, nerifrån och upp
            for (int j = gridRows - 1; j >= 0; j--) {
                if (board[j][i].getColorID() != 0) {
                    filledColumnColors.add(board[j][i].getColorID());
                } else {

                    // Om vi hittar en tom ruta UNDER en färgad ruta
                    // betyder det att gravity kommer ske
                    if (!filledColumnColors.isEmpty()) {
                        columnShifted = true;
                    }
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
            if (columnShifted) {
                fallenTilesInColumn.add(i);
            }
        }
        notifyDebugGravityApplied(fallenTilesInColumn);
    }

    public void shiftLeft() {
        ArrayList<Point> XmovedToY = new ArrayList<>(); // for console-view
        int targetColumn = 0;

        for (int currentColumn = 0; currentColumn < gridColumns; currentColumn++) {

            if (!isColumnEmpty(currentColumn)) {

                if (currentColumn != targetColumn) {
                    XmovedToY.add(new Point(currentColumn, targetColumn));
                    moveColumn(currentColumn, targetColumn);
                    clearColumn(currentColumn);
                }

                targetColumn++;
            }
        }

        notifyGameObservers();
        notifyDebugShiftedLeft(XmovedToY);
        notifyDebugCurrentBoard();
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
     * @return boolean value if index belongs to board[][] and if it is colored
     */
    public boolean isTile(int row, int column) {
        return row < board.length && column < board[0].length && row >= 0 && column >= 0
                && board[row][column].getColorID() != 0;
    }

    /**
     * Removes tile from board
     * 
     * @param row    index to tile to remove
     * @param column index to tile to remove
     */
    public void removeTile(int row, int column) {
        this.board[row][column].setColorID(0); // sets the tile to DARK_GRAY
    }

    /**
     *
     * @return the main playboard
     */
    public Tile[][] getBoard() {
        return board;
    }

    /**
     * @return a random color ID in the range 1 to n
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
            notifyGameObservers();
        }
    }

    public void redo() {
        if (!redoStack.isEmpty()) {
            undoStack.push(copyBoard(board));
            board = redoStack.pop();
            notifyGameObservers();
        }
    }

    public void reset() {
        undoStack.clear();
        redoStack.clear();
        board = copyBoard(originalBoard);
        notifyGameObservers();
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
