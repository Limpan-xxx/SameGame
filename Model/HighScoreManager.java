package Model;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HighScoreManager implements Serializable {
    private static final String FILE_PATH = "Resources/highscores.ser";
    private static final int MAX_SCORES = 10;

    private List<HighScore> highScores;

    public HighScoreManager() {
        this.highScores = loadHighScores();
    }

    public void addScore(String playerName, int score) {
        highScores.add(new HighScore(playerName, score));

        // Sort highest to lowest
        Collections.sort(highScores, (a, b) -> b.getScore() - a.getScore());

        // Keep only the top scores
        if (highScores.size() > MAX_SCORES) {
            highScores = highScores.subList(0, MAX_SCORES);
        }

        saveHighScores();
    }

    public List<HighScore> getHighScores() {
        return Collections.unmodifiableList(highScores);
    }

    private void saveHighScores() {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(FILE_PATH))) {
            oos.writeObject(highScores);
        } catch (IOException e) {
            System.err.println("Failed to save high scores: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    private List<HighScore> loadHighScores() {
        File file = new File(FILE_PATH);
        if (!file.exists()) return new ArrayList<>(); // first run, no file yet

        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(FILE_PATH))) {
            return (List<HighScore>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Failed to load high scores: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}
