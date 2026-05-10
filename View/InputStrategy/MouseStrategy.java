package View.InputStrategy;

import Presentation.GamePresenter;
import View.GamePanel;

import javax.swing.*;

public class MouseStrategy implements InputStrategy {

    @Override
    public void setupInput(GamePresenter gamePresenter, GamePanel gamePanel) {
        JButton[][] buttons = gamePanel.getButtons();
        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons[i].length; j++) {
                int row = i;
                int column = j;
                buttons[i][j].addActionListener(e -> gamePresenter.tileClicked(row, column));
            }
        }
    }
}
