package View;

import Model.BoardModel;
import Model.ScoreboardModel;
import Presentation.GamePresenter;
import java.awt.*;
import javax.swing.*;

public class Board {
    private JFrame frame;
    public final int BOARD_ROWS = 8;
    public final int BOARD_COLUMNS = 8;

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
        int numberOfColors = switch (level) {
            case "Easy" -> 2;
            case "Intermediate" -> 3;
            case "Hard" -> 4;
            case "Insane" -> 5;
            case "Nightmare" -> 6;
            default -> 3;
        };

        frame.getContentPane().removeAll();
        frame.setLayout(new BorderLayout());

        JLabel title = new JLabel("SameGame", SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 30));
        BoardModel boardModel = new BoardModel(BOARD_ROWS, BOARD_COLUMNS, numberOfColors);
        ScoreboardModel scoreboardModel = new ScoreboardModel(BOARD_ROWS * BOARD_COLUMNS);
        GamePresenter gamePresenter = new GamePresenter(boardModel, this, scoreboardModel);
        Menu menu = new Menu(gamePresenter);
        Scoreboard scoreboard = new Scoreboard(scoreboardModel);
        JPanel gamePanelWrapper = new JPanel(new GridBagLayout());
        GamePanel gamepanel = new GamePanel(boardModel, gamePresenter);
        gamePanelWrapper.add(gamepanel);

        frame.add(title, BorderLayout.NORTH);
        frame.add(menu, BorderLayout.EAST);
        JPanel leftSpacer = new JPanel();
        leftSpacer.setPreferredSize(menu.getPreferredSize());
        frame.add(leftSpacer, BorderLayout.WEST);
        frame.add(gamePanelWrapper, BorderLayout.CENTER);
        frame.add(scoreboard, BorderLayout.SOUTH);

        frame.revalidate();
        frame.repaint();
    }

    public void returnToStartMenu() {
        frame.getContentPane().removeAll();
        StartMenu startMenu = new StartMenu(this);
        frame.add(startMenu);
        frame.revalidate();
        frame.repaint();
    }

}