import java.awt.*;
import javax.swing.*;

public class Board {

    public static void main(String[] args) {
        JFrame frame = new JFrame("SameGame");

        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JLabel title = new JLabel("SameGame");

        Menu menu = new Menu();

        frame.add(title, BorderLayout.NORTH);
        frame.add(menu, BorderLayout.EAST);

        frame.setVisible(true);
    }
}