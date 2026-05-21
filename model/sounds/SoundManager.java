package model.sounds;

import model.observers.SoundObserver;

import java.util.ArrayList;

public class SoundManager {
    private ArrayList<SoundObserver> soundObservers = new ArrayList<>();

    /**
     * the constructor of the SoundManager
     * adds the observers to the list of observers
     */
    public SoundManager(){
        soundObservers.add(new WinSound());
        soundObservers.add(new LoseSound());
        soundObservers.add(new correctTileSound());
        soundObservers.add(new wrongTileSound());
    }


    /**
     * is called when a sound is wanted to appear
     * @param gameEvent the event of the sound, enumerator
     */
    public void notifySound(GameEvent gameEvent){
        for (SoundObserver soundObserver : soundObservers){
            soundObserver.playSound(gameEvent);
        }
    }
}
