package Model.Sounds;

import Model.Observers.SoundObserver;

public class WinSound implements SoundObserver {
    /**
     * plays a sound that represents the winning of a game
     * @param gameEvent the event, enumerator
     */
    @Override
    public void playSound(GameEvent gameEvent) {
        if(GameEvent.WIN != gameEvent) return;
        Sound.playSound("Resources/win1.wav");
    }
}
