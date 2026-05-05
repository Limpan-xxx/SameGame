package Model;


public class BoardModel {
    private Tile[][] board;
    private final int gridColumns;
    private final int gridRows;


    public BoardModel(int gridRows, int gridColumns){
        this.board = new Tile[gridRows][gridColumns];
        this.gridRows = gridRows;
        this.gridColumns = gridColumns;
        for(int i = 0; i < gridRows; i++){
            System.out.println();
            for(int j = 0; j < gridColumns ; j++){
                int ID = randomColorID();
                board[i][j] = new Tile(ID);
                System.out.print(ID + " ");
            }
        }
    }

    public void removeTile(int row, int column){
        System.out.println();
        this.board[row][column].setColorID(0); // sets the tile to DARK_GRAY

        // för debug konsol
        System.out.println("removed " + row + " " + column);
        for(int i = 0; i < gridRows; i++){
            System.out.println();
            for(int j = 0; j < gridColumns ; j++){
                System.out.print(board[i][j].getColorID() + " ");
            }
        }
    }

    public Tile[][] getBoard(){
        return board;
    }
    /**
     @return random colorID
     */
    private int randomColorID() {
        return (int)(Math.random() * 3) + 1; // 1=red, 2=green, 3=blue
    }

    /**
     @return gridColumns
     */
    public int getGridColumns() {
        return gridColumns;
    }

    /**
     @return gridRows
     */
    public int getGridRows() {
        return gridRows;
    }
}
