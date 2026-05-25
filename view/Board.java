package view;

import model.BoardModel;
import model.HighScoreManager;
import model.ScoreboardModel;
import controller.GameController;
import controller.inputStrategy.InputStrategy;
import controller.inputStrategy.KeyboardStrategy;
import controller.inputStrategy.MouseStrategy;
import model.sounds.SoundManager;

import java.awt.*;
import javax.swing.*;

public class Board {
    private JFrame frame;
    public final int BOARD_ROWS = 8;
    public final int BOARD_COLUMNS = 8;
    private boolean controllerModeOn;
    HighScoreManager highScoreManager;

    public Board() {
        frame = new JFrame("SameGame");
        frame.setSize(900, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        this.highScoreManager = new HighScoreManager();

        StartMenu startMenu = new StartMenu(this, highScoreManager);
        frame.add(startMenu);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new Board();
    }

    /**
     * method for starting the game in the difficulty of choice
     * @param level the difficulty level
     */
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
        SoundManager soundManager = new SoundManager();
        GameController gameController = new GameController(boardModel, this, scoreboardModel, soundManager,
                highScoreManager);
        Scoreboard scoreboard = new Scoreboard(scoreboardModel);
        JPanel gamePanelWrapper = new JPanel(new GridBagLayout());
        gamePanelWrapper.setPreferredSize(new Dimension(BOARD_COLUMNS * 40, BOARD_ROWS * 40));
        GamePanel gamepanel = new GamePanel(boardModel);
        Menu menu = new Menu(gameController, boardModel, scoreboardModel, gamepanel);
        gamePanelWrapper.add(gamepanel);

        frame.add(title, BorderLayout.NORTH);
        frame.add(menu, BorderLayout.EAST);
        JPanel leftSpacer = new JPanel();
        leftSpacer.setPreferredSize(menu.getPreferredSize());
        frame.add(leftSpacer, BorderLayout.WEST);
        frame.add(gamePanelWrapper, BorderLayout.CENTER);
        frame.add(scoreboard, BorderLayout.SOUTH);

        InputStrategy inputStrategy;
        if (controllerModeOn) {
            inputStrategy = new KeyboardStrategy();
        } else {
            inputStrategy = new MouseStrategy();
        }

        inputStrategy.setupInput(gameController, gamepanel);

        frame.revalidate();
        frame.repaint();
        gamepanel.requestFocusInWindow();
    }

    /**
     * returns to the start menu
     */
    public void returnToStartMenu() {
        frame.getContentPane().removeAll();
        StartMenu startMenu = new StartMenu(this, highScoreManager);
        frame.add(startMenu);
        frame.revalidate();
        frame.repaint();
    }

    public JFrame getFrame() {
        return frame;
    }

    public void inputToggleController(Boolean controllerModeOn) {
        this.controllerModeOn = controllerModeOn;
    }

    public boolean getControllerState() {
        return this.controllerModeOn;
    }

}