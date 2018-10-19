/*****************************************************************
These are system functions to use during or before a game.

@author Dane Bramble
@version October 2018
*****************************************************************/

package connectsystem;

public class SystemOptions {

  /*****************************************************************
  Returns from the game board or options menu back to the main menu. 
  */
  public static void returnToMenu() {
    //I'm not sure what I should call.... I suppose it is whatever the menu is called
    return;
  }
  /*****************************************************************
  Exits the program to return to the desktop.
  */
  
  public static void exitProgram() {
    System.exit(0);
    return;
  }

  //  public static void startMusic(MusicPlayer audioPlayer) {
  //      try
  //      { 
  //       String status = audioPlayer.getStatus();
  //         if (status != "play"){
  //           audioPlayer.play(); 
  //       }
  //
  //      }  
  //        
  //      catch (Exception ex)  
  //      { 
  //          System.out.println("Error with starting sound file."); 
  //          ex.printStackTrace(); 
  //        
  //      } 
  //      return;
  //  }
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
  
  //    public static void main(String [ ] args) throws InterruptedException{
  //       try {
  //         MusicPlayer audioPlayer = new MusicPlayer(); //
  //
  //       } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
  //         System.out.println("error in playing music."); 
  //         e.printStackTrace();
  //       }
  //    }
}
