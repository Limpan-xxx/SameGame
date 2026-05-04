package View;

import Model.BoardModel;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    int tileSize = 35;
    private BoardModel boardmodel; // till senare

    public GamePanel(BoardModel boardmodel) {
        this.boardmodel = boardmodel;
        setPreferredSize(new Dimension(boardmodel.getGridRows() * this.tileSize, boardmodel.getGridColumns() * this.tileSize));
        setLayout(new GridLayout(boardmodel.getGridRows(), boardmodel.getGridColumns()));
        setBackground(Color.DARK_GRAY);

        for (int i = 0; i < boardmodel.getGridRows(); i++) {
            for (int j = 0; j < boardmodel.getGridColumns(); j++) {
                JButton Tile = new JButton();
                Tile.setBackground(TileColor.IDtoColor(boardmodel.getBoard()[i][j].getColorID()));
                add(Tile);
            }
        }
    }



}
