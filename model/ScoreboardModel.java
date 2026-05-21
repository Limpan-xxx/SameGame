package model;

import java.util.ArrayList;
import java.util.Stack;

import model.observers.DebugObserver;
import model.observers.GameObserver;

public class ScoreboardModel {

    int currentScore;
    int lastMoveScore;
    int remainingTiles;
    private ArrayList<GameObserver> scoreObservers = new ArrayList<>();
    private ArrayList<DebugObserver> debugObservers = new ArrayList<>();
    private Stack<Integer> undoStackCurrentScore = new Stack<>();
    private Stack<Integer> redoStackCurrentScore = new Stack<>();

    private Stack<Integer> redoStackRemainingTiles = new Stack<>();
    private Stack<Integer> undoStackRemainingTiles = new Stack<>();

    private Stack<Integer> undoStackLastMoveScore = new Stack<>();
    private Stack<Integer> redoStackLastMoveScore = new Stack<>();

    public ScoreboardModel(int totalTiles) {
        currentScore = 0;
        lastMoveScore = 0;
        remainingTiles = totalTiles;
    }

    /**
     *
     * @param observer the observer of the game, Tile[][] board
     */
    public void addObserver(GameObserver observer) {
        scoreObservers.add(observer);
    }

    private void notifyObservers() {
        for (GameObserver observer : scoreObservers) {
            observer.boardChanged();
        }
    }

    public void addDebugObserver(DebugObserver observer) {
        debugObservers.add(observer);
    }

    public void removeDebugObserver(DebugObserver debugObserver) {
        debugObservers.remove(debugObserver);
    }

    private void notifyDebugObservers() {
        for (DebugObserver observer : debugObservers) {
            observer.scoreUpdated(lastMoveScore, currentScore);
        }
    }

    public int calculateMoveScore(int numberOfRemovedTiles) {
        if (numberOfRemovedTiles < 2) {
            return 0;
        }
        return (numberOfRemovedTiles - 2) * (numberOfRemovedTiles - 2);
    }

    public void updateAfterMove(int numberOfRemovedTiles) {
        lastMoveScore = calculateMoveScore(numberOfRemovedTiles);

        currentScore = currentScore + lastMoveScore;

        remainingTiles = remainingTiles - numberOfRemovedTiles;

        if (remainingTiles < 0) {
            remainingTiles = 0;
        }

        notifyDebugObservers();
        notifyObservers();
    }

    public void saveState() {
        undoStackCurrentScore.push(currentScore);
        undoStackRemainingTiles.push(remainingTiles);
        undoStackLastMoveScore.push(lastMoveScore);

        redoStackCurrentScore.clear();
        redoStackRemainingTiles.clear();
        redoStackLastMoveScore.clear();
    }

    public void reset(int totalTiles) {
        undoStackCurrentScore.clear();
        undoStackRemainingTiles.clear();
        undoStackLastMoveScore.clear();

        redoStackCurrentScore.clear();
        redoStackRemainingTiles.clear();
        redoStackLastMoveScore.clear();

        currentScore = 0;
        lastMoveScore = 0;
        remainingTiles = totalTiles;
        notifyObservers();
    }

    public void undo() {
        if (!undoStackCurrentScore.isEmpty()
                && !undoStackRemainingTiles.isEmpty()
                && !undoStackLastMoveScore.isEmpty()) {

            redoStackCurrentScore.push(currentScore);
            redoStackRemainingTiles.push(remainingTiles);
            redoStackLastMoveScore.push(lastMoveScore);

            currentScore = undoStackCurrentScore.pop();
            remainingTiles = undoStackRemainingTiles.pop();
            lastMoveScore = undoStackLastMoveScore.pop();

            notifyObservers();
        }
    }

    public void redo() {
        if (!redoStackCurrentScore.isEmpty()
                && !redoStackRemainingTiles.isEmpty()
                && !redoStackLastMoveScore.isEmpty()) {

            undoStackCurrentScore.push(currentScore);
            undoStackRemainingTiles.push(remainingTiles);
            undoStackLastMoveScore.push(lastMoveScore);

            currentScore = redoStackCurrentScore.pop();
            remainingTiles = redoStackRemainingTiles.pop();
            lastMoveScore = redoStackLastMoveScore.pop();

            notifyObservers();
        }
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
