package Model;

public class ScoreboardModel {

    int currentScore;
    int lastMoveScore;
    int remainingTiles;

    public ScoreboardModel(int totalTiles) {
        currentScore = 0;
        lastMoveScore = 0;
        remainingTiles = totalTiles;
    }

    public int calculateMoveScore(int numberOfRemovedTiles) {
        if (numberOfRemovedTiles < 2) {
            return 0;
        }

        int moveScore = (numberOfRemovedTiles - 2) * (numberOfRemovedTiles - 2);

        return moveScore;
    }

    public void updateAfterMove(int numberOfRemovedTiles) {
        lastMoveScore = calculateMoveScore(numberOfRemovedTiles);

        currentScore = currentScore + lastMoveScore;

        remainingTiles = remainingTiles - numberOfRemovedTiles;

        if (remainingTiles < 0) {
            remainingTiles = 0;
        }
    }

    public void reset(int totalTiles) {
        currentScore = 0;
        lastMoveScore = 0;
        remainingTiles = totalTiles;
    }

    public int getCurrentScore() {
        return currentScore;
    }

    public int getLastMoveScore() {
        return lastMoveScore;
    }

    public int getRemainingTiles() {
        return remainingTiles;
    }
}
