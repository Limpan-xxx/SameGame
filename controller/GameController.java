package controller;

import model.BoardModel;
import model.HighScoreManager;
import model.ScoreboardModel;
import model.sounds.GameEvent;
import model.sounds.SoundManager;
import view.Board;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class GameController {
    BoardModel boardModel;
    Board board;
    ScoreboardModel scoreboardModel;
    private ArrayList<Point> bestMove;
    SoundManager soundManager;
    HighScoreManager highScoreManager;

    /**
     * Constructs a GameController
     * @param boardModel the model containing the game boards state and logic
     * @param board the main class
     * @param scoreboardModel the model containing the current score and scoring logic.
     * @param soundManager responsible for playing game sound effects.
     * @param highScoreManager the class that handles the ser file with scores
     */
    public GameController(BoardModel boardModel, Board board, ScoreboardModel scoreboardModel,
            SoundManager soundManager, HighScoreManager highScoreManager) {
        this.boardModel = boardModel;
        this.board = board;
        this.scoreboardModel = scoreboardModel;
        this.bestMove = boardModel.getBestClusterToRemove();
        this.soundManager = soundManager;
        this.highScoreManager = highScoreManager;
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

        updateBestMove();

        if (!boardModel.hasMoves()) {

            if (boardModel.win()) {
                soundManager.notifySound(GameEvent.WIN);

                String name = JOptionPane.showInputDialog(board.getFrame(), "You Won! 🎉 Enter your name:");
                if (name != null && !name.isBlank()) {
                    highScoreManager.addScore(name, scoreboardModel.getCurrentScore());
                }
            } else {
                soundManager.notifySound(GameEvent.LOSE);
                JOptionPane.showMessageDialog(board.getFrame(), " YOU LOST! 😢", "Game Over",
                        JOptionPane.PLAIN_MESSAGE);
            }
        }
    }

    /**
     * updates the best move
     */
    private void updateBestMove() {
        this.bestMove = boardModel.getBestClusterToRemove();
    }

    /**
     * getter for the best move
     * @return the best move as a list with Points
     */
    public ArrayList<Point> getBestMove() {
        return bestMove;
    }

    /**
     * the undo method handles so that all redo calls are in sync
     */
    public void undo() {
        boardModel.undo();
        scoreboardModel.undo();
        updateBestMove();
    }

    /**
     * the redo method handles so that all redo calls are in sync
     */
    public void redo() {
        boardModel.redo();
        scoreboardModel.redo();
        updateBestMove();
    }

    /**
     * the reset method handles so that all reset calls are in sync
     */
    public void reset() {
        boardModel.reset();
        scoreboardModel.reset(board.BOARD_COLUMNS * board.BOARD_ROWS);
        updateBestMove();
    }

    /**
     * calls the board to exit to the startMenu
     */
    public void exitToMenu() {
        board.returnToStartMenu();
    }
}
