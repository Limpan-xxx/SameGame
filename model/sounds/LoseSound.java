package model.sounds;

import model.observers.SoundObserver;

public class LoseSound implements SoundObserver {
    /**
     * plays a sound that represents the losing of a game
     * @param gameEvent the event, enumerator
     */
    @Override
    public void playSound(GameEvent gameEvent) {
        if(GameEvent.LOSE != gameEvent) return;
        Sound.playSound("resources/lose.wav");
    }
}
