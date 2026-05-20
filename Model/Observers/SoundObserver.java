package Model.Observers;

import Model.Sounds.GameEvent;

public interface SoundObserver {
    void playSound(GameEvent gameEvent);
}
