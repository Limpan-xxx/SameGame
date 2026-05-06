package View;

import Model.BoardModel;
import Presentation.GamePresenter;
import java.awt.*;
import javax.swing.*;

public class Board {
    private JFrame frame;
    public Board() {
    frame = new JFrame("SameGame");
    frame.setSize(800, 600);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLayout(new BorderLayout());

    StartMenu startMenu = new StartMenu(this);
    frame.add(startMenu);
    frame.setVisible(true);
}
    public static void main(String[] args) {
    new Board();
    }
    public void startGame(String level) {
    int colors = switch (level) {
        case "Easy"         -> 2;
        case "Intermediate" -> 3;
        case "Hard"         -> 4;
        case "Insane"       -> 5;
        case "Nightmare"    -> 6;
        default             -> 3;
    };

    frame.getContentPane().removeAll();
    frame.setLayout(new BorderLayout());

    JLabel title = new JLabel("SameGame", SwingConstants.CENTER);
    BoardModel boardModel = new BoardModel(8, 8, colors);
    GamePresenter gamePresenter = new GamePresenter(boardModel);
    Menu menu = new Menu();
    Scoreboard scoreboard = new Scoreboard();
    JPanel gamePanelWrapper = new JPanel(new GridBagLayout());
    GamePanel gamepanel = new GamePanel(boardModel, gamePresenter);
    gamePanelWrapper.add(gamepanel);

    frame.add(title, BorderLayout.NORTH);
    frame.add(menu, BorderLayout.EAST);
    frame.add(gamePanelWrapper, BorderLayout.CENTER);
    frame.add(scoreboard, BorderLayout.SOUTH);

    frame.revalidate();
    frame.repaint();
}
}