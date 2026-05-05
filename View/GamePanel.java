package View;

import Model.BoardModel;
import Model.Observers.GameObserver;
import Presentation.GamePresenter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GamePanel extends JPanel implements GameObserver {
    int tileSize = 35;
    JButton[][] buttons;
    private BoardModel boardModel;

    public GamePanel(BoardModel boardModel, GamePresenter gamePresenter) {
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
                Tile.setBackground(TileColor.IDtoColor(boardModel.getBoard()[i][j].getColorID()));
                buttons[i][j] = Tile;
                add(Tile);
                int row = i;
                int column = j;
                Tile.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        gamePresenter.tileClicked(row, column);
                    }
                });
            }
        }
    }

    public void updatePanel(){
        int rows = boardModel.getGridRows();
        int columns = boardModel.getGridColumns();

        for(int i = 0; i < rows; i++){
            for(int j = 0; j < columns; j++){
                buttons[i][j].setBackground(TileColor.IDtoColor(boardModel.getBoard()[i][j].getColorID()));
            }
        }
    }

    @Override
    public void boardChanged() {
        updatePanel();
    }
}
