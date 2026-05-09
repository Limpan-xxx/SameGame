package View;
import Presentation.GamePresenter;
import java.awt.*;
import javax.swing.*;

public class Menu extends JPanel {

    public Menu(GamePresenter gamePresenter) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
        JButton undo = new JButton("Undo");
        JButton redo = new JButton("Redo");
        JButton reset = new JButton("Reset");
        JButton exit = new JButton("Exit");

        Dimension buttonSize = new Dimension(120, 40);

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
        undo.addActionListener(e -> gamePresenter.undo());
        redo.addActionListener(e -> gamePresenter.redo());
        reset.addActionListener(e -> gamePresenter.reset());
        exit.addActionListener(e -> gamePresenter.exitToMenu());
        undo.setAlignmentX(Component.CENTER_ALIGNMENT);
        redo.setAlignmentX(Component.CENTER_ALIGNMENT);
        reset.setAlignmentX(Component.CENTER_ALIGNMENT);
        exit.setAlignmentX(Component.CENTER_ALIGNMENT);
        
    }
}