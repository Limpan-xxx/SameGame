package View;
import Presentation.GamePresenter;
import java.awt.*;
import javax.swing.*;

public class Menu extends JPanel {

    public Menu(GamePresenter gamePresenter) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JButton undo = new JButton("Undo");
        JButton redo = new JButton("Redo");
        JButton reset = new JButton("Reset");
        JButton exit = new JButton("Exit");

        Dimension buttonSize = new Dimension(120, 40);

        undo.setMaximumSize(buttonSize);
        redo.setMaximumSize(buttonSize);
        reset.setMaximumSize(buttonSize);
        exit.setMaximumSize(buttonSize);

        add(Box.createVerticalStrut(50));

        add(undo);
        add(Box.createVerticalStrut(20));

        add(redo);
        add(Box.createVerticalStrut(20));

        add(reset);
        add(Box.createVerticalStrut(20));

        add(exit);
        
    }
}