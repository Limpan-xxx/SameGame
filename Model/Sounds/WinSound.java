package Model.Sounds;

import Model.Observers.SoundObserver;

public class WinSound implements SoundObserver {
    @Override
    public void playSound(GameEvent gameEvent) {
        if(GameEvent.WIN != gameEvent) return;
        Sound.playSound("Resources/win1.wav");
    }
}
