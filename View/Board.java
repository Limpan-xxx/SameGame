package View;

import Model.BoardModel;

import java.awt.*;
import javax.swing.*;

public class Board {

    public static void main(String[] args) {
        JFrame frame = new JFrame("SameGame");

        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JLabel title = new JLabel("SameGame", SwingConstants.CENTER);

        Menu menu = new Menu();
        Scoreboard scoreboard = new Scoreboard();

        BoardModel boardmodel = new BoardModel(5,5);

        JPanel gamePanelWrapper = new JPanel(new GridBagLayout());
        GamePanel gamepanel = new GamePanel(boardmodel.getGridRows(),boardmodel.getGridColumns());
        gamePanelWrapper.add(gamepanel);

        frame.add(title, BorderLayout.NORTH);
        frame.add(menu, BorderLayout.EAST);
        frame.add(gamePanelWrapper, BorderLayout.CENTER);
        frame.add(scoreboard, BorderLayout.SOUTH);

        frame.setVisible(true);
    }
}