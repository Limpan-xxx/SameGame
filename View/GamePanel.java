package View;

import javax.swing.*;
import java.awt.*;

public class GamePanel {

    JPanel panel;
    int GamePanelSizeX = 50; // ska ändras till rätt storlek
    int GamePanelSizeY = 50;
    int tileSize = 10;

    public GamePanel(){
        panel = new JPanel();
        panel.setPreferredSize(new Dimension(GamePanelSizeX * tileSize, GamePanelSizeY * tileSize));
        panel.setLayout(new GridLayout(GamePanelSizeX, GamePanelSizeY));
    }
}
