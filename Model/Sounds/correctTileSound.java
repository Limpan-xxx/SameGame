package Model.Sounds;

import Model.Observers.SoundObserver;

public class correctTileSound implements SoundObserver {
    @Override
    public void playSound(GameEvent gameEvent) {
        if(GameEvent.CORRECT_CLICK != gameEvent) return;
        Sound.playSound("Resources/correctTile.wav");
    }
}
