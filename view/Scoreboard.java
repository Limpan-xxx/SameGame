package view;

import java.awt.*;
import javax.swing.*;

import model.ScoreboardModel;
import model.observers.GameObserver;

public class Scoreboard extends JPanel implements GameObserver {

    private JLabel currentScoreLabel;
    private JLabel lastMoveScoreLabel;
    private JLabel remainingTilesLabel;
    private ScoreboardModel scoreboard;

    /**
     * Constructs a Scoreboard panel that observes the provided ScoreboardModel.
     * Initializes the labels for current score, last move score, and remaining
     * tiles.
     *
     * @param scoreboard the model to observe
     */
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

    /**
     * observer calls boardChanged
     */
    @Override
    public void boardChanged() {
        setCurrentScoreLabel(scoreboard.getCurrentScore());
        setLastMoveScoreLabel(scoreboard.getLastMoveScore());
        setRemainingTilesLabel(scoreboard.getRemainingTiles());
    }
}