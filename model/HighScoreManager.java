package model;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HighScoreManager implements Serializable {
    private static final String FILE_PATH = "resources/highscores.ser";
    private static final int MAX_SCORES = 10;

    private List<HighScore> highScores;

    public HighScoreManager() {
        this.highScores = loadHighScores();
    }

    /**
     *  adds a score as a score and possibly adds it to the list of highscore if it is high enough
     * @param playerName the name of the player, fills in it themselves
     * @param score the score of the current game at win
     */
    public void addScore(String playerName, int score) {
        highScores.add(new HighScore(playerName, score));

        highScores.sort((a, b) -> Integer.compare(b.getScore(), a.getScore()));

        if (highScores.size() > MAX_SCORES) {
            highScores = new ArrayList<>(highScores.subList(0, MAX_SCORES));
        }

        saveHighScores();
    }

    /**
     * other classes can't modify the list, throws error
     * @return the list of highscores
     */
    public List<HighScore> getHighScores() {
        return Collections.unmodifiableList(highScores);
    }

    /**
     * saves the highscore through serialization
     */
    private void saveHighScores() {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(FILE_PATH))) {
            oos.writeObject(highScores);
        } catch (IOException e) {
            System.err.println("Failed to save high scores: " + e.getMessage());
        }
    }

    /**
     * loads the highscore, always done at new HighscoreMangager()
     * @return list of HighScores, cast as List
     */
    @SuppressWarnings("unchecked")
    private List<HighScore> loadHighScores() {
        File file = new File(FILE_PATH);
        if (!file.exists()) return new ArrayList<>();

        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(FILE_PATH))) {
            return (List<HighScore>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Failed to load high scores: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}
