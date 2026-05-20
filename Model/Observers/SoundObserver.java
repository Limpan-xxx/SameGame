package Model.Observers;

import Model.Sounds.GameEvent;

public interface SoundObserver {
    /**
     * plays a sound for the specified event
     * @param gameEvent the event, enumerator
     */
    void playSound(GameEvent gameEvent);
}
