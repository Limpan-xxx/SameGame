package Controller;

import Model.BoardModel;
import Model.ScoreboardModel;
import Model.Sounds.GameEvent;
import Model.Sounds.SoundManager;
import View.Board;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class GameController {
    BoardModel boardModel;
    Board board;
    ScoreboardModel scoreboardModel;
    SoundManager soundManager;

    public GameController(BoardModel boardModel, Board board, ScoreboardModel scoreboardModel, SoundManager soundManager) {
        this.boardModel = boardModel;
        this.board = board;
        this.scoreboardModel = scoreboardModel;
        this.soundManager = soundManager;
    }

    /**
     *
     * @param row    index of the Tile
     * @param column index of the Tile
     */
    public void tileClicked(int row, int column) {

        boardModel.notifyDebugTileClicked(row, column, boardModel.board[row][column].getColorID());

        boardModel.saveState();
        scoreboardModel.saveState();
        ArrayList<Point> neighbors = boardModel.searchConnected(row, column);

        if (neighbors.size() < 2) {
            soundManager.notifySound(GameEvent.INCORRECT_CLICK);
            return;
        }
        soundManager.notifySound(GameEvent.CORRECT_CLICK);

        boardModel.notifyDebugRemovedTile(neighbors, boardModel.board);
        for (Point p : neighbors) {
            boardModel.removeTile(p.x, p.y);
        }

        boardModel.gravityFalls();
        boardModel.shiftLeft();
        scoreboardModel.updateAfterMove(neighbors.size());
        if (!boardModel.hasMoves()) {

            if (boardModel.win()) {
                soundManager.notifySound(GameEvent.WIN);
                JOptionPane.showMessageDialog(board.getFrame(), " You Won! 🎉", "Game Over", JOptionPane.PLAIN_MESSAGE);
            } else {
                soundManager.notifySound(GameEvent.LOSE);
                JOptionPane.showMessageDialog(board.getFrame(), " YOU LOST! 😢", "Game Over", JOptionPane.PLAIN_MESSAGE);
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
