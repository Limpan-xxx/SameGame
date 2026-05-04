package Model;

public class BoardModel {
    private Tile[][] board;
    private final int gridColumns;
    private final int gridRows;


    public BoardModel(int gridRows, int gridColumns){
        this.board = new Tile[gridRows][gridColumns];
        this.gridRows = gridRows;
        this.gridColumns = gridColumns;
    }

    public int getGridColumns() {
        return gridColumns;
    }

    public int getGridRows() {
        return gridRows;
    }
}
