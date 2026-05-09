package View;

import java.awt.*;
import javax.swing.*;

public class StartMenu extends JPanel {

    private String selectedLevel = "Easy";
    private JButton selectedButton = null;

    // Färger för vald/ovald knapp
    private static final Color SELECTED_COLOR = new Color(70, 130, 180);
    private static final Color DEFAULT_COLOR   = UIManager.getColor("Button.background");

    public StartMenu(Board board) {

        setLayout(new GridBagLayout()); // Centrerar content-panelen

        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));

        // --- Titel ---
        JLabel title = new JLabel("Choose difficulty");
        title.setFont(new Font("SansSerif", Font.BOLD, 20));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        content.add(title);
        content.add(Box.createVerticalStrut(25));

        // --- Knappar ---
        String[] levels = {"Easy", "Intermediate", "Hard", "Insane", "Nightmare"};
        for (String level : levels) {
            JButton btn = createButton(level);
            content.add(btn);
            content.add(Box.createVerticalStrut(8)); // Fast mellanrum → ingen hoppning
            if (level.equals("Easy")) {
                markSelected(btn); // Markera Easy som vald från start
            }
        }

        content.add(Box.createVerticalStrut(20));

        // --- Start-knapp ---
        JButton start = new JButton("Start Game");
        start.setAlignmentX(Component.CENTER_ALIGNMENT);
        start.setMaximumSize(new Dimension(200, 40));
        start.setPreferredSize(new Dimension(200, 40));
        start.setMinimumSize(new Dimension(200, 40));
        start.addActionListener(e -> board.startGame(selectedLevel));
        content.add(start);

        content.add(Box.createVerticalStrut(100));

        JToggleButton controller = new JToggleButton("Keyboard", false);
        controller.setFocusable(false);
        controller.setBackground(Color.red);
        controller.setAlignmentX(Component.CENTER_ALIGNMENT);
        controller.setMaximumSize(new Dimension(200, 40));
        controller.setPreferredSize(new Dimension(200, 40));
        controller.setMinimumSize(new Dimension(200, 40));
        controller.addActionListener(e -> {board.inputToggleController(controller.isSelected());
                });
        content.add(controller, BorderLayout.WEST);

        add(content); // GridBagLayout centrerar automatiskt
    }

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

    // Återställer föregående knapp och markerar ny
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