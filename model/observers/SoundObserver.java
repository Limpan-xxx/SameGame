package model.observers;

import model.sounds.GameEvent;

public interface SoundObserver {
    /**
     * plays a sound for the specified event
     * @param gameEvent the event, enumerator
     */
    void playSound(GameEvent gameEvent);
}
