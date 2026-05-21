package model.sounds;

import model.observers.SoundObserver;

public class WinSound implements SoundObserver {
    /**
     * plays a sound that represents the winning of a game
     * @param gameEvent the event, enumerator
     */
    @Override
    public void playSound(GameEvent gameEvent) {
        if(GameEvent.WIN != gameEvent) return;
        Sound.playSound("resources/win1.wav");
    }
}
