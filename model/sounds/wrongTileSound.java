package model.sounds;

import model.observers.SoundObserver;

public class wrongTileSound implements SoundObserver {

    /**
     * plays a sound that represents the selection of a tile that has no neighbors or is invalid
     * @param gameEvent the event, enumerator
     */
    @Override
    public void playSound(GameEvent gameEvent) {
        if(GameEvent.INCORRECT_CLICK != gameEvent) return;
        Sound.playSound("resources/incorrectTile.wav");
    }
}
