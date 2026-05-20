package Controller.InputStrategy;

import Controller.GameController;
import View.GamePanel;

import javax.swing.*;

public class MouseStrategy implements InputStrategy {

    /**
     * the setup method for the MouseStrategy
     * @param gameController references to the class GameController
     * @param gamePanel references to the class GameController
     */
    @Override
    public void setupInput(GameController gameController, GamePanel gamePanel) {
        JButton[][] buttons = gamePanel.getButtons();
        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons[i].length; j++) {
                int row = i;
                int column = j;
                buttons[i][j].addActionListener(e -> gameController.tileClicked(row, column));
            }
        }
    }
}
