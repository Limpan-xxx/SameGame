package Presentation;

import Model.BoardModel;
import Model.ScoreboardModel;
import View.Board;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class GamePresenter {
    BoardModel boardModel;
    Board board;
    ScoreboardModel scoreboardModel;

    public GamePresenter(BoardModel boardModel, Board board, ScoreboardModel scoreboardModel) {
        this.boardModel = boardModel;
        this.board = board;
        this.scoreboardModel = scoreboardModel;
    }

    /**
     *
     * @param row    index of the Tile
     * @param column index of the Tile
     */
    public void tileClicked(int row, int column) {
        boardModel.saveState();
        scoreboardModel.saveState();
        ArrayList<Point> neighbors = boardModel.searchConnected(row, column);
        if (neighbors.size() < 2) {
            return;
        }
        for (Point p : neighbors) {
            boardModel.removeTile(p.x, p.y);
        }
        boardModel.gravityFalls();
        boardModel.shiftLeft();
        scoreboardModel.updateAfterMove(neighbors.size());
        if (!boardModel.hasMoves()) {

        if (boardModel.win()) {
            JOptionPane.showMessageDialog(board.getFrame(), " You Won! 🎉","Game Over",    JOptionPane.PLAIN_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(board.getFrame(), " YOU LOST! 😢","Game Over",    JOptionPane.PLAIN_MESSAGE);
        }
}
    }

    public void undo() {
        boardModel.undo();
        scoreboardModel.undo();
    }

    public void redo() {
        boardModel.redo();
        scoreboardModel.redo();
    }

    public void reset() {
        boardModel.reset();
        scoreboardModel.reset(board.BOARD_COLUMNS * board.BOARD_ROWS);
    }

    public void exitToMenu() {
        board.returnToStartMenu();
    }
}
