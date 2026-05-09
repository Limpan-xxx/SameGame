package View.InputStrategy;

import Presentation.GamePresenter;
import View.GamePanel;

public interface InputStrategy {
    void setupInput(GamePresenter gamePresenter, GamePanel gamePanel);
}
