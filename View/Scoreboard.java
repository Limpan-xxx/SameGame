import javax.swing.*;
import java.awt.*;

public class Scoreboard extends JPanel {

    private JLabel currentScoreLabel;
    private JLabel lastMoveScoreLabel;
    private JLabel remainingTilesLabel;

    public Scoreboard() {
        setLayout(new FlowLayout());
        currentScoreLabel = new JLabel("Current Score: 0");
        lastMoveScoreLabel = new JLabel("Last Move Score: 0");
        remainingTilesLabel = new JLabel("Remaining Tiles: 0");

        add(currentScoreLabel);
        add(lastMoveScoreLabel);
        add(remainingTilesLabel);
    }

    public void setCurrentScoreLabel(int score) {
        currentScoreLabel.setText("Current Score: " + score);
    }

    public void SetLastMoveScoreLabel(int score) {
        lastMoveScoreLabel.setText("Last Move Score: " + score);
    }

    public void SetRemainingTilesLabel(int tiles) {
        remainingTilesLabel.setText("Remaining Tiles: " + tiles);
    }
}