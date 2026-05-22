package view;

import controller.GameController;
import model.BoardModel;
import model.ScoreboardModel;

import java.awt.*;
import javax.swing.*;

public class Menu extends JPanel {

    private boolean debugState = false;

    public Menu(GameController gameController, BoardModel boardModel, ScoreboardModel scoreboardModel,
            GamePanel gamePanel) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
        JCheckBox DebugMode = new JCheckBox("Debug mode");
        JCheckBox bestMoveMode = new JCheckBox("Best move");
        JButton undo = new JButton("Undo");
        JButton redo = new JButton("Redo");
        JButton reset = new JButton("Reset");
        JButton exit = new JButton("Exit");

        DebugView debugView = new DebugView();

        Dimension buttonSize = new Dimension(120, 40);

        DebugMode.setMaximumSize(buttonSize);
        DebugMode.setFocusable(false);
        DebugMode.addActionListener(e -> debugState = !debugState);

        DebugMode.addActionListener(e -> {

            if (DebugMode.isSelected()) {
                boardModel.addDebugObserver(debugView);
                scoreboardModel.addDebugObserver(debugView);
            } else {
                boardModel.removeDebugObserver(debugView);
                scoreboardModel.removeDebugObserver(debugView);
            }
        });

        bestMoveMode.setMaximumSize(buttonSize);
        bestMoveMode.setFocusable(false);
        bestMoveMode.setAlignmentX(Component.CENTER_ALIGNMENT);

        bestMoveMode.addActionListener(e -> {
            gamePanel.setHighlightBestMove(bestMoveMode.isSelected());
        });

        add(DebugMode);
        add(bestMoveMode);
        undo.setMaximumSize(buttonSize);
        undo.setFocusable(false);
        redo.setMaximumSize(buttonSize);
        redo.setFocusable(false);
        reset.setMaximumSize(buttonSize);
        reset.setFocusable(false);
        exit.setMaximumSize(buttonSize);
        exit.setFocusable(false);

        add(Box.createVerticalGlue());

        add(undo);
        add(Box.createVerticalStrut(20));

        add(redo);
        add(Box.createVerticalStrut(20));

        add(reset);
        add(Box.createVerticalStrut(20));

        add(exit);
        add(Box.createVerticalGlue());

        add(DebugMode);
        add(Box.createVerticalStrut(20));

        undo.addActionListener(e -> gameController.undo());
        redo.addActionListener(e -> gameController.redo());
        reset.addActionListener(e -> gameController.reset());
        exit.addActionListener(e -> gameController.exitToMenu());
        undo.setAlignmentX(Component.CENTER_ALIGNMENT);
        redo.setAlignmentX(Component.CENTER_ALIGNMENT);
        reset.setAlignmentX(Component.CENTER_ALIGNMENT);
        exit.setAlignmentX(Component.CENTER_ALIGNMENT);

    }
}