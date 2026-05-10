package View;

import java.awt.*;
import javax.swing.*;

import Model.ScoreboardModel;
import Model.Observers.GameObserver;

public class Scoreboard extends JPanel implements GameObserver {

    private JLabel currentScoreLabel;
    private JLabel lastMoveScoreLabel;
    private JLabel remainingTilesLabel;
    private ScoreboardModel scoreboard;

    public Scoreboard(ScoreboardModel scoreboard) {
        setLayout(new FlowLayout());
        currentScoreLabel = new JLabel("Current Score: 0");
        lastMoveScoreLabel = new JLabel("Last Move Score: 0");
        remainingTilesLabel = new JLabel("Remaining Tiles: " + scoreboard.getRemainingTiles());

        add(currentScoreLabel);
        add(lastMoveScoreLabel);
        add(remainingTilesLabel);
        this.scoreboard = scoreboard;
        scoreboard.addObserver(this);
    }

    public void setCurrentScoreLabel(int score) {
        currentScoreLabel.setText("Current Score: " + score);
    }

    public void setLastMoveScoreLabel(int score) {
        lastMoveScoreLabel.setText("Last Move Score: " + score);
    }

    public void setRemainingTilesLabel(int tiles) {
        remainingTilesLabel.setText("Remaining Tiles: " + tiles);
    }

    @Override
    public void boardChanged() {
        updateScorePanel();
    }

    private void updateScorePanel() {
        setCurrentScoreLabel(scoreboard.getCurrentScore());
        setLastMoveScoreLabel(scoreboard.getLastMoveScore());
        setRemainingTilesLabel(scoreboard.getRemainingTiles());
    }
}