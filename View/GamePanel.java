package View;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    int tileSize = 35;

    public GamePanel(int rows, int columns) {
        setPreferredSize(new Dimension(rows * this.tileSize, columns * this.tileSize));
        setLayout(new GridLayout(rows, columns));
        setBackground(Color.DARK_GRAY);

        for (int i = 0; i < rows * columns; i++) {
            JButton cell = new JButton();
            cell.setBackground(randomColor());
            add(cell);
        }
    }

    /**
     @return random color in colors array
     */
    private Color randomColor() {
        Color[] colors = { Color.RED, Color.blue, Color.green };
        return colors[(int) (Math.random() * colors.length)];
    }
}
