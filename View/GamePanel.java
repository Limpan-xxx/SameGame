package View;

import Model.BoardModel;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    int tileSize = 35;
    JButton[][] buttons;
    private BoardModel boardmodel;

    public GamePanel(BoardModel boardmodel) {
        this.boardmodel = boardmodel;
        int rows = boardmodel.getGridRows();
        int columns = boardmodel.getGridColumns();
        this.buttons = new JButton[rows][columns];

        setPreferredSize(new Dimension(rows * this.tileSize, columns * this.tileSize));
        setLayout(new GridLayout(rows, columns));
        setBackground(Color.DARK_GRAY);

        for (int i = 0; i < boardmodel.getGridRows(); i++) {
            for (int j = 0; j < boardmodel.getGridColumns(); j++) {
                JButton Tile = new JButton();
                Tile.setBackground(TileColor.IDtoColor(boardmodel.getBoard()[i][j].getColorID()));
                buttons[i][j] = Tile;
                add(Tile);
            }
        }
    }

    public void updatePanel(){
        int rows = boardmodel.getGridRows();
        int columns = boardmodel.getGridColumns();

        for(int i = 0; i < rows; i++){
            for(int j = 0; j < columns; j++){
                buttons[i][j].setBackground(TileColor.IDtoColor(boardmodel.getBoard()[i][j].getColorID()));
            }
        }
    }
}
