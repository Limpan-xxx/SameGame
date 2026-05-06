package Presentation;

import Model.BoardModel;

import java.awt.*;
import java.util.ArrayList;

public class GamePresenter {
    BoardModel boardModel;

    public GamePresenter(BoardModel boardModel){
        this.boardModel = boardModel;
    }

    public void tileClicked(int row, int column) {
        ArrayList<Point> neighbors = boardModel.searchConnected(row, column);
        for (Point p : neighbors) {
            boardModel.removeTile(p.x, p.y);
        }
    }
}
