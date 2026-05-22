package view;

import model.HighScoreManager;
import model.HighScore;

import java.awt.*;
import javax.swing.*;

public class StartMenu extends JPanel {

    private String selectedLevel = "Easy";
    private JButton selectedButton = null;

    private static final Color SELECTED_COLOR = new Color(70, 130, 180);
    private static final Color DEFAULT_COLOR   = UIManager.getColor("Button.background");

    HighScoreManager highScoreManager;

    /**
     * StartMenu constructor
     * @param board main
     * @param highScoreManager the highscoreManager keeping track of the highscores
     */
    public StartMenu(Board board, HighScoreManager highScoreManager) {
        this.highScoreManager = highScoreManager;

        setLayout(new GridBagLayout());

        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));

        JLabel title = new JLabel("Choose difficulty");
        title.setFont(new Font("SansSerif", Font.BOLD, 20));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        content.add(title);
        content.add(Box.createVerticalStrut(25));

        String[] levels = {"Easy", "Intermediate", "Hard", "Insane", "Nightmare"};
        for (String level : levels) {
            JButton btn = createButton(level);
            content.add(btn);
            content.add(Box.createVerticalStrut(8));
            if (level.equals("Easy")) {
                markSelected(btn); // mark easy as start
            }
        }

        content.add(Box.createVerticalStrut(20));

        JButton start = new JButton("Start Game");
        start.setAlignmentX(Component.CENTER_ALIGNMENT);
        start.setMaximumSize(new Dimension(200, 40));
        start.setPreferredSize(new Dimension(200, 40));
        start.setMinimumSize(new Dimension(200, 40));
        start.addActionListener(e -> board.startGame(selectedLevel));
        content.add(start);

        content.add(Box.createVerticalStrut(100));

        JButton highScoreButton = new JButton("High Scores");
        highScoreButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        highScoreButton.setMaximumSize(new Dimension(200, 40));
        highScoreButton.setPreferredSize(new Dimension(200, 40));
        highScoreButton.setMinimumSize(new Dimension(200, 40));
        highScoreButton.addActionListener(e -> showHighScores());
        content.add(highScoreButton);

        content.add(Box.createVerticalStrut(100));

        JCheckBox controller = new JCheckBox("Keyboard", false);
        controller.setSelected(board.getControllerState());
        controller.setFocusable(false);
        controller.setAlignmentX(Component.CENTER_ALIGNMENT);
        controller.addActionListener(e -> {board.inputToggleController(controller.isSelected());
                });
        content.add(controller, BorderLayout.WEST);

        add(content); // gridBagLayout
    }

    /**
     * shows the highscore if there is any, as a JOptionPane
     */
    private void showHighScores() {
        if (highScoreManager.getHighScores().isEmpty()) {
            JOptionPane.showMessageDialog(null, "No high scores yet!", "High Scores", JOptionPane.PLAIN_MESSAGE);
            return;
        }

        StringBuilder sb = new StringBuilder();
        int rank = 1;
        for (HighScore hs : highScoreManager.getHighScores()) {
            sb.append(rank).append(". ").append(hs.getPlayerName())
                    .append(" — ").append(hs.getScore()).append("\n");
            rank++;
        }
        JOptionPane.showMessageDialog(null, sb.toString(), "High Scores", JOptionPane.PLAIN_MESSAGE);
    }

    /**
     * creates buttons for the level selection
     * @param level the level of the button in creation
     * @return the made button ready to add
     */
    private JButton createButton(String level) {
        JButton button = new JButton(level);

        Dimension size = new Dimension(200, 40);
        button.setMaximumSize(size);
        button.setPreferredSize(size);
        button.setMinimumSize(size);

        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setFocusPainted(false);
        button.setFocusable(false);

        button.addActionListener(e -> {
            selectedLevel = level;
            markSelected(button); // Byt färg på vald knapp
        });

        return button;
    }

    /**
     * marks the button that is selected
     * @param button the button clicked on
     */
    private void markSelected(JButton button) {
        if (selectedButton != null) {
            selectedButton.setBackground(DEFAULT_COLOR);
            selectedButton.setForeground(Color.BLACK);
        }
        button.setBackground(SELECTED_COLOR);
        button.setForeground(Color.WHITE);
        selectedButton = button;
    }
}