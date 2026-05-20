package Controller.InputStrategy;

import Controller.GameController;
import View.GamePanel;

public interface InputStrategy {
    /**
     * Each strategy uses this method to set up it controller config
     * @param gameController references to the class GameController
     * @param gamePanel references to the class GameController
     */
    void setupInput(GameController gameController, GamePanel gamePanel);
}
