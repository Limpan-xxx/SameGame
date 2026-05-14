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

        boardModel.addObserver(this);

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

    public void fillTileColor(JButton Tile, int x, int y) {
        Tile.setBackground(TileColor.IDtoColor(boardModel.getBoard()[x][y].getColorID()));
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

    @Override
    public void boardChanged() {
        updatePanel();
    }

    public JButton[][] getButtons(){
        return buttons;
    }
}
