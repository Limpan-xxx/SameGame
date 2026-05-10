package Presentation;

import Model.BoardModel;
import Model.DebugPrinter;
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
        DebugPrinter.printSectionStart("DEBUG START");

        DebugPrinter.printSectionStart("CLICK EVENT");
        DebugPrinter.printLine("Clicked tile: row=" + row +
                " column=" + column +
                " color=" + boardModel.board[row][column].getColorID());
        DebugPrinter.printSectionEnd("CLICK EVENT");

        boardModel.saveState();
        scoreboardModel.saveState();
        ArrayList<Point> neighbors = boardModel.searchConnected(row, column);

        if (neighbors.size() < 2) {
            return;
        }
        DebugPrinter.printSectionStart("REMOVED TILES");
        for (Point p : neighbors) {
            DebugPrinter.printLine("Removed tile: row=" + p.x + " column=" + p.y + " color="
                    + boardModel.board[p.x][p.y].getColorID());
            boardModel.removeTile(p.x, p.y);
        }
        DebugPrinter.printSectionEnd("REMOVED TILES");

        boardModel.gravityFalls();
        boardModel.shiftLeft();
        scoreboardModel.updateAfterMove(neighbors.size());
        if (!boardModel.hasMoves()) {

            if (boardModel.win()) {
                JOptionPane.showMessageDialog(board.getFrame(), " You Won! 🎉", "Game Over", JOptionPane.PLAIN_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(board.getFrame(), " YOU LOST! 😢", "Game Over",
                        JOptionPane.PLAIN_MESSAGE);
            }
        }
        DebugPrinter.printSectionStart("DEBUG END");
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
