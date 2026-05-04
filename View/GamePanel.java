package View;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel{
    int rows = 5; //
    int columns = 5;
    int tileSize = 35;

    public GamePanel(){
        setPreferredSize(new Dimension(rows * tileSize, columns * tileSize));
        setLayout(new GridLayout(rows, columns));
        setBackground(Color.DARK_GRAY);

        for(int i = 0; i < rows*columns; i++){
            JButton cell = new JButton();
            cell.setBackground(randomColor());
            add(cell);
        }
    }

    private Color randomColor(){
        Color[] colors = {Color.RED, Color.blue, Color.green};
        return colors[(int) (Math.random() * colors.length)];
    }


}
