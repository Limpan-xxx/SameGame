package Presentation;

import Model.BoardModel;

public class GamePresenter {
    BoardModel boardModel;

    public GamePresenter(BoardModel boardModel){
        this.boardModel = boardModel;
    }

    public void tileClicked(int row, int column) {
        boardModel.removeTile(row, column);
    }
}
