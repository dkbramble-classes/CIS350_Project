/*****************************************************************
Handles the execution of the Music Player.

@author Dane Bramble
@version November 2018
*****************************************************************/

package connectsystem;

public class SystemOptions {

  /*****************************************************************
  If playing, the music will stop. If stopped, the music will resume.
  To start the music, create a MusicPlayer object. The music will stop
  automatically when the music player object leaves the scope.
  
  @param audioPlayer the music object to stop/resume playing
  */
  
  public static void musicControl(MusicPlayer audioPlayer) {
    try { 
      String status = audioPlayer.getStatus();
      if (status != "paused") { //if not paused, pause the music
        audioPlayer.pause(); 
      } else {
        audioPlayer.resumeAudio(); //if paused, resume the music
      }
    } catch (Exception ex) {  //error handling 
      System.out.println("Error with music control."); 
      ex.printStackTrace(); 
    } 
    return;
  }

}
