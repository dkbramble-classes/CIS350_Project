/*****************************************************************
This Music class starts playing a sound file in a 
continuous loop. Offers methods to pause/resume. 
To start the music, create a MusicPlayer object. 
The music will stop automatically when the music player object 
leaves the scope.Code based on class found on 
https://www.geeksforgeeks.org/play-audio-file-using-java/

@author Dane Bramble
@version October 2018
*****************************************************************/

package connectsystem;

import java.io.File; 
import java.io.IOException; 

import javax.sound.sampled.AudioInputStream; 
import javax.sound.sampled.AudioSystem; 
import javax.sound.sampled.Clip; 
import javax.sound.sampled.LineUnavailableException; 
import javax.sound.sampled.UnsupportedAudioFileException; 

public class MusicPlayer {
  // to store current position in song
  Long currentFrame; 
  Clip clip; 
      
  // current status of clip (play, paused)
  String status; 
      
  AudioInputStream audioInputStream; 
  static String filePath = "model/resources/First Try.wav";  // the file path of the desired file
    
  /*****************************************************************
  Constructor - initializes the streams and clip.
  */
  public MusicPlayer() 
      throws UnsupportedAudioFileException, 
      IOException, LineUnavailableException { 
    // create AudioInputStream object 
    audioInputStream = AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile()); 
          
    // create clip reference 
    clip = AudioSystem.getClip(); 
          
    // open audioInputStream to the clip 
    clip.open(audioInputStream); 
    clip.stop();
        
    clip.loop(Clip.LOOP_CONTINUOUSLY); 
  } 
    
  /*****************************************************************
  Plays the given audio file, and sets a new status.
  */
  public void play() { 
    //start the clip 
    clip.start(); 
    status = "play"; 
  } 
    
  /*****************************************************************
  Stops the audio stream and sets a new status.
  */
  public void pause() { 
    this.currentFrame = this.clip.getMicrosecondPosition(); 
    clip.stop(); 
    status = "paused"; 
  } 
    
  /*****************************************************************
  Resumes the audio play back from the last point it was paused at.
  */
  
  public void resumeAudio() throws UnsupportedAudioFileException, 
                                IOException, LineUnavailableException { 
    clip.close(); 
    resetAudioStream(); 
    clip.setMicrosecondPosition(currentFrame); 
    this.play(); 
  } 
    
    
  /*****************************************************************
  Resets the audio stream to be played again when resumed.
  */
  public void resetAudioStream() throws UnsupportedAudioFileException, IOException, 
                                            LineUnavailableException { 
    audioInputStream = AudioSystem.getAudioInputStream(
    new File(filePath).getAbsoluteFile()); 
    clip.open(audioInputStream); 
    clip.loop(Clip.LOOP_CONTINUOUSLY); 
  } 
    
    
  /*****************************************************************
  Returns the status of the audioPlayer ('play' for currently 
  playing, 'paused' for currently paused).
  */
  public String getStatus() {
    return status;
  }
  
  /*****************************************************************
  If playing, the music will stop. If stopped, the music will resume.
  */
  public void musicControl() {
    try { 
      if (status != "paused") { //if not paused, pause the music
        this.pause(); 
      } else {
        this.resumeAudio(); //if paused, resume the music
      }
    } catch (Exception ex) {  //error handling 
      System.out.println("Error with music control."); 
      ex.printStackTrace(); 
    } 
    return;
  }

}
