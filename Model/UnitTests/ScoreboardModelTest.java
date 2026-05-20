package Model.UnitTests;

import Model.ScoreboardModel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ScoreboardModelTest {

    @Test
    void calculateMoveScoreLessThanTwo() {
        ScoreboardModel scoreboard = new ScoreboardModel(64);

        assertEquals(0, scoreboard.calculateMoveScore(0));
        assertEquals(0, scoreboard.calculateMoveScore(1));
    }

    @Test
    void updateAfterMove() {
        ScoreboardModel scoreboard = new ScoreboardModel(64);

        scoreboard.updateAfterMove(5);

        assertEquals(9, scoreboard.getLastMoveScore());
        assertEquals(9, scoreboard.getCurrentScore());
        assertEquals(59, scoreboard.getRemainingTiles());
    }

    @Test
    void resetRestoreInitialScoreValues() {
        ScoreboardModel scoreboard = new ScoreboardModel(64);

        scoreboard.updateAfterMove(5);
        scoreboard.reset(64);

        assertEquals(0, scoreboard.getCurrentScore());
        assertEquals(0, scoreboard.getLastMoveScore());
        assertEquals(64, scoreboard.getRemainingTiles());
    }

    @Test
    void undoRestorePreviousScoreState() {
        ScoreboardModel scoreboard = new ScoreboardModel(64);

        scoreboard.saveState();
        scoreboard.updateAfterMove(5);

        scoreboard.undo();

        assertEquals(0, scoreboard.getCurrentScore());
        assertEquals(0, scoreboard.getLastMoveScore());
        assertEquals(64, scoreboard.getRemainingTiles());
    }

    @Test
    void redoRestoreUndoneScoreState() {
        ScoreboardModel scoreboard = new ScoreboardModel(64);

        scoreboard.saveState();
        scoreboard.updateAfterMove(5);

        scoreboard.undo();
        scoreboard.redo();

        assertEquals(9, scoreboard.getCurrentScore());
        assertEquals(9, scoreboard.getLastMoveScore());
        assertEquals(59, scoreboard.getRemainingTiles());
    }

    @Test
    void saveStateClearRedoStackAfterNewMove() {
        ScoreboardModel scoreboard = new ScoreboardModel(64);

        scoreboard.saveState();
        scoreboard.updateAfterMove(5);

        scoreboard.undo();

        scoreboard.saveState();
        scoreboard.updateAfterMove(4);

        scoreboard.redo();

        assertEquals(4, scoreboard.getCurrentScore());
        assertEquals(4, scoreboard.getLastMoveScore());
        assertEquals(60, scoreboard.getRemainingTiles());
    }

    @Test
    void redoWithoutUndo() {
        ScoreboardModel scoreboard = new ScoreboardModel(64);

        scoreboard.redo();

        assertEquals(0, scoreboard.getCurrentScore());
        assertEquals(0, scoreboard.getLastMoveScore());
        assertEquals(64, scoreboard.getRemainingTiles());
    }
}