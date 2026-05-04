import javax.swing.*;
import java.awt.*;

public class Board {

    public static void main(String[] args) {
        JFrame frame = new JFrame("SameGame");
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());

        JLabel caption = new JLabel("SameGame");

        frame.add(caption);

        frame.setVisible(true);
    }
}