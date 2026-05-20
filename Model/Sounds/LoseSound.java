package Model.Sounds;

import Model.Observers.SoundObserver;

public class LoseSound implements SoundObserver {
    @Override
    public void playSound(GameEvent gameEvent) {
        if(GameEvent.LOSE != gameEvent) return;
        Sound.playSound("Resources/lose.wav");
    }
}
