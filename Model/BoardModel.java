package Model;


import Model.Observers.GameObserver;

import java.awt.*;
import java.util.ArrayList;

public class BoardModel {
    private Tile[][] board;
    private final int gridColumns;
    private final int gridRows;

    private ArrayList<GameObserver> observers = new ArrayList<>();

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

    public void addObserver(GameObserver observer) {
        observers.add(observer);
    }

    private void notifyObservers() {
        for (GameObserver observer : observers) {
            observer.boardChanged();
        }
    }

    public ArrayList<Point> searchConnected(int row, int column){
        ArrayList<Point> ConnectedCoordinates = new ArrayList<>();

        ConnectedCoordinates.add(new Point(row, column));

        if(isTile(row-1,column) && this.board[row][column].getColorID() == this.board[row-1][column].getColorID()){ // upp
            ConnectedCoordinates.add(new Point(row-1,column));
        }
        if (isTile(row+1,column) && this.board[row][column].getColorID() == this.board[row+1][column].getColorID()) { // ner
            ConnectedCoordinates.add(new Point(row+1,column));
        }
        if (isTile(row,column-1) && this.board[row][column].getColorID() == this.board[row][column-1].getColorID()) { // vänster
            ConnectedCoordinates.add(new Point(row,column-1));
        }
        if (isTile(row,column+1) && this.board[row][column].getColorID() == this.board[row][column+1].getColorID()) { // höger
            ConnectedCoordinates.add(new Point(row,column+1));
        }

        return ConnectedCoordinates;
    }

    public boolean isTile(int row, int column){
        return row < board.length && column < board[0].length && row >= 0 && column >= 0;
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

        notifyObservers();
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
