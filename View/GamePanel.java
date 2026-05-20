package View;

import Model.BoardModel;
import Model.Observers.GameObserver;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements GameObserver {
    int tileSize = 50;
    JButton[][] buttons;
    private BoardModel boardModel;

    public GamePanel(BoardModel boardModel) {
        this.boardModel = boardModel;
        int rows = boardModel.getGridRows();
        int columns = boardModel.getGridColumns();
        this.buttons = new JButton[rows][columns];

        boardModel.addGameObserver(this);

        setPreferredSize(new Dimension(rows * this.tileSize, columns * this.tileSize));
        setLayout(new GridLayout(rows, columns));
        setBackground(Color.DARK_GRAY);

        for (int i = 0; i < boardModel.getGridRows(); i++) {
            for (int j = 0; j < boardModel.getGridColumns(); j++) {
                JButton Tile = new JButton();
                fillTileColor(Tile, i, j);
                buttons[i][j] = Tile;
                add(Tile);
            }
        }
    }

    /**
     * fills tiles with color from BoardModel
     * @param Tile JButton with background
     * @param row index of the JButton tile
     * @param column index of the JButton tile
     */
    public void fillTileColor(JButton Tile, int row, int column) {
        Tile.setBackground(TileColor.IDtoColor(boardModel.getBoard()[row][column].getColorID()));
    }

    /**
     * updates the panel based on if Tile[][] board has changed
     */
    public void updatePanel() {
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
    }

    /**
     *
     * @return the 2D array with buttons
     */
    public JButton[][] getButtons(){
        return buttons;
    }
}
