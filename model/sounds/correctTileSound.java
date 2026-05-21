package model.sounds;

import model.observers.SoundObserver;

public class correctTileSound implements SoundObserver {
    /**
     * plays a sound that represents correct tile selected
     * @param gameEvent the event, enumerator
     */
    @Override
    public void playSound(GameEvent gameEvent) {
        if(GameEvent.CORRECT_CLICK != gameEvent) return;
        Sound.playSound("resources/correctTile.wav");
    }
}
