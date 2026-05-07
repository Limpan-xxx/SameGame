package Presentation;

import Model.BoardModel;
import View.Board;
import java.awt.*;
import java.util.ArrayList;

public class GamePresenter {
    BoardModel boardModel;
    Board board;
    public GamePresenter(BoardModel boardModel, Board board){
        this.boardModel = boardModel;
        this.board = board;
    }

    /**
     *
     * @param row index of the Tile
     * @param column index of the Tile
     */
    public void tileClicked(int row, int column) {
        boardModel.saveState();
        ArrayList<Point> neighbors = boardModel.searchConnected(row, column);
        if(neighbors.size() < 2){
            return;
        }
        for (Point p : neighbors) {
            boardModel.removeTile(p.x, p.y);
        }
        boardModel.gravityFalls();
        boardModel.shiftLeft();
    }
    public void undo() { boardModel.undo(); }
    public void redo() { boardModel.redo(); }
    public void reset() { boardModel.reset(); }
    public void exitToMenu() { board.returnToStartMenu(); }
}
