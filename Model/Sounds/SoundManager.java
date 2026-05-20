package Model.Sounds;

import Model.Observers.SoundObserver;

import java.util.ArrayList;

public class SoundManager {
    private ArrayList<SoundObserver> soundObservers = new ArrayList<>();

    public SoundManager(){
        soundObservers.add(new WinSound());
        soundObservers.add(new LoseSound());
        soundObservers.add(new correctTileSound());
        soundObservers.add(new wrongTileSound());
    }


    public void notifySound(GameEvent gameEvent){
        for (SoundObserver soundObserver : soundObservers){
            soundObserver.playSound(gameEvent);
        }
    }
}
