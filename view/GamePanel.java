package view;

import model.BoardModel;
import model.observers.GameObserver;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GamePanel extends JPanel implements GameObserver {
    int tileSize = 50;
    JButton[][] buttons;
    private BoardModel boardModel;
    private boolean highlightBestMove = false;

    public GamePanel(BoardModel boardModel) {
        this.boardModel = boardModel;
        int rows = boardModel.getGridRows();
        int columns = boardModel.getGridColumns();
        this.buttons = new JButton[rows][columns];

        boardModel.addGameObserver(this);

        setPreferredSize(new Dimension(rows * this.tileSize, columns * this.tileSize));
        setLayout(new GridLayout(rows, columns, 2, 2));
        setBackground(Color.DARK_GRAY);

        for (int i = 0; i < boardModel.getGridRows(); i++) {
            for (int j = 0; j < boardModel.getGridColumns(); j++) {
                JButton Tile = new JButton();
                fillTileColor(Tile, i, j);
                buttons[i][j] = Tile;
                add(Tile);
            }
        }

        if (highlightBestMove) {
            highlightTiles(boardModel.getBestClusterToRemove());
        } else {
            clearHighlights();
        }
    }

    /**
     * fills tiles with color from BoardModel
     * 
     * @param Tile   JButton with background
     * @param row    index of the JButton tile
     * @param column index of the JButton tile
     */
    private void fillTileColor(JButton Tile, int row, int column) {
        Tile.setBackground(TileColor.IDtoColor(boardModel.getBoard()[row][column].getColorID()));
    }

    private void clearHighlights() {
        int rows = boardModel.getGridRows();
        int columns = boardModel.getGridColumns();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                buttons[i][j].setBorder(BorderFactory.createEmptyBorder(4,4,4,4));
            }
        }
    }

    private void highlightTiles(ArrayList<Point> tiles) {
        clearHighlights();

        for (Point p : tiles) {
            buttons[p.x][p.y].setBorder(
                    BorderFactory.createCompoundBorder(
                            BorderFactory.createEmptyBorder(2, 2, 2, 2),
                            BorderFactory.createLineBorder(Color.BLACK, 2)));
        }
    }

    public void setHighlightBestMove(boolean highlightBestMove) {
        this.highlightBestMove = highlightBestMove;

        if (highlightBestMove) {
            highlightTiles(boardModel.getBestClusterToRemove());
        } else {
            clearHighlights();
        }
    }

    /**
     * updates the panel based on if Tile[][] board has changed
     */
    private void updatePanel() {
        int rows = boardModel.getGridRows();
        int columns = boardModel.getGridColumns();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                fillTileColor(buttons[i][j], i, j);
            }
        }
    }

    /**
     * GameObserver calls this function
     */
    @Override
    public void boardChanged() {
        updatePanel();

        if (highlightBestMove) {
            highlightTiles(boardModel.getBestClusterToRemove());
        } else {
            clearHighlights();
        }
    }

    /**
     *
     * @return the 2D array with buttons
     */
    public JButton[][] getButtons() {
        return buttons;
    }
}
