package model;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.util.concurrent.CompletableFuture;

public class SoundEffectModel {
    
  public static synchronized void playSound(final String url) {
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

  private Clip bgClip;

  public synchronized void playBackgroundSound(final String url) {
    CompletableFuture.runAsync(() -> {
        try {
          bgClip = AudioSystem.getClip();
          AudioInputStream inputStream = AudioSystem.getAudioInputStream(
            new File("./BlackJackGame/src/main/resources/" + url).getAbsoluteFile()
          );
          bgClip.open(inputStream);
          bgClip.start();
          bgClip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (Exception e) {
          e.printStackTrace();
        }
      });
  }

  public void stopBackgroundSound(){
    if(bgClip.isRunning()){
      bgClip.stop();
    }
  }
    
}
