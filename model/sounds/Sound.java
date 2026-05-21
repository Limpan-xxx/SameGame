package model.sounds;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class Sound {
    /**
     * the static method that plays a sound
     * @param filePath the file path to the sound file
     */
    public static void playSound(String filePath) {
        try {
            File soundFile = new File(filePath);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile);

            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);

            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
