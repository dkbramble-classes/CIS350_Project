/*****************************************************************
Options to be used on the player options screen
Edits characteristics for each player

@author Dane Bramble
@version September 2018
*****************************************************************/

package playertype;

import java.util.Scanner;

public class PlayerOptions {
  /*****************************************************************
  Allows input to set the name of a player.
  @param play the Player whose name will be updated
  */
  private static void inputName(Player play) {
    System.out.println("Enter Name:");
    Scanner scan = new Scanner(System.in);
    String name = scan.nextLine();
    scan.close();

    if (name.length() < 11 && name.length() > 0) {
      play.setName(name);
    } else {
      System.out.println("Name must be between 1 and 10 characters!");
    }
    return;
  }
  
  /*****************************************************************
  Allows input to set the name of a computer player.
  @param play the computer player whose name will be updated
  */
  private static void inputName(ComputerPlayer play) {
    System.out.println("Enter Name:");
    Scanner scan = new Scanner(System.in);
    String name = scan.nextLine();
    scan.close();

    if (name.length() < 11 && name.length() > 0) {
      play.setName(name);
    } else {
      System.out.println("Name must be between 1 and 10 characters!");
    }
    return;
  }

  /*****************************************************************
   Allows input to set the color of a player.

   @param play the Player whose color will be updated
   @param c the color that the player has chosen
   */
  private static void selectColor(Player play, char c) {
    play.setColor(c);
    return;
  }
  
  /*****************************************************************
  Allows input to set the color of a computer player.

  @param play the computer player whose color will be updated
  @param c the color that the player has chosen for the computer
  */
  private static void selectColor(ComputerPlayer play, char c) {
    play.setColor(c);
    return;
  }
  
  /*****************************************************************
  Allows input to set the difficulty of a computer player.

  @param play the computer player whose difficulty will be updated
  @param d the difficulty that the player has chosen for the computer
  */
  private static void selectDifficulty(ComputerPlayer play, char c) {
    play.setDifficulty(c);
    return;
  }
}
