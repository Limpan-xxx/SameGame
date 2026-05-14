package Controller.InputStrategy;

import Controller.GameController;
import View.GamePanel;

public interface InputStrategy {
    void setupInput(GameController gameController, GamePanel gamePanel);
}
