package Model.Sounds;

import Model.Observers.SoundObserver;

public class wrongTileSound implements SoundObserver {

    @Override
    public void playSound(GameEvent gameEvent) {
        if(GameEvent.INCORRECT_CLICK != gameEvent) return;
        Sound.playSound("Resources/incorrectTile.wav");
    }
}
