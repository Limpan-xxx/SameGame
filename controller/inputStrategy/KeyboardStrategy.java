package controller.inputStrategy;

import controller.GameController;
import view.GamePanel;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyboardStrategy implements InputStrategy {
    private int selectedRow = 0;
    private int selectedColumn = 0;

    /**
     * the setup method for the KeyboardStrategy
     * @param gameController references to the class GameController
     * @param gamePanel references to the class GameController
     */
    @Override
    public void setupInput(GameController gameController, GamePanel gamePanel) {
        JButton[][] buttons = gamePanel.getButtons();

        for (JButton[] row : buttons) {
            for (JButton button : row) {
                button.setFocusable(false);
            }
        }

        gamePanel.setFocusable(true);
        gamePanel.requestFocusInWindow();

        gamePanel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP:
                        if (selectedRow > 0) {
                            selectedRow--;
                        }
                        break;
                    case KeyEvent.VK_DOWN:
                        if (selectedRow < buttons.length - 1) {
                            selectedRow++;
                        }
                        break;
                    case KeyEvent.VK_LEFT:
                        if (selectedColumn > 0) {
                            selectedColumn--;
                        }
                        break;
                    case KeyEvent.VK_RIGHT:
                        if (selectedColumn < buttons[0].length - 1) {
                            selectedColumn++;
                        }
                        break;
                    case KeyEvent.VK_ENTER:
                        gameController.tileClicked(selectedRow, selectedColumn);
                        break;
                }
                highlightButton(buttons);
            }
        });
    }

    private void highlightButton(JButton[][] buttons) {

        // Återställ alla borders
        for (JButton[] button : buttons) {

            for (JButton jButton : button) {

                jButton.setBorder(
                        BorderFactory.createEmptyBorder());
            }
        }

        // Markera vald knapp
        buttons[selectedRow][selectedColumn].setBorder(
                BorderFactory.createLineBorder(
                        java.awt.Color.WHITE,
                        3));
    }
}
