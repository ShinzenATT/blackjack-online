package Model;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.util.concurrent.CompletableFuture;

public class SoundEffectModel {
    
    public static synchronized void playSound(final String url) {
        // The wrapper thread is unnecessary, unless it blocks on the
        // Clip finishing;
        CompletableFuture.runAsync(() -> {
            try {
              Clip clip = AudioSystem.getClip();
              AudioInputStream inputStream = AudioSystem.getAudioInputStream(
                new File("./BlackJackGame/src/main/resources/" + url).getAbsoluteFile()
              );
              clip.open(inputStream);
              clip.start();
            } catch (Exception e) {
              e.printStackTrace();
            }
          });
      }
    
}
