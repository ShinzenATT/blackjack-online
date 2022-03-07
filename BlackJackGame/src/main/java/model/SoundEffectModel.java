package model;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.util.concurrent.CompletableFuture;

/**
 * Class that tries to play sound files.
 * @author Joel
 * @version 2022-03-07
 */
public class SoundEffectModel {
  private Clip bgClip;

  /** 
   * Plays a supported sound file from the resources
   * folder given the name of the file.
   * 
   * @param url The full name of the sound file to play.
   */
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

  /** 
   * Plays a supported sound file on repeat as background music
   * from the resources folder given the name of the file.
   * 
   * @param url The full name of the sound file to play on repeat as background music.
   */
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
