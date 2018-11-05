/*****************************************************************
A Computer Player that plays the Connect 4 game. 
The computer player chooses the color of the chip and its name.
The computer player has an order in which its turn will be.

@author Dane Bramble
@version September 2018
*****************************************************************/

package playertype;

import gamelogic.ConnectLogic;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class ComputerPlayer {
  private char color; //The color of the computer's chip
  private int position; // The order of the computer (1st, 2nd, 3rd, 4th)
  private String name; //The name displayed
  private char difficulty; // The computer's level of difficulty (e = easy, m = medium, h = hard)

  /*****************************************************************
    Constructor - a computer without a name , color, or position.
    Sets default values.
    *****************************************************************/
  public ComputerPlayer() {
    this.color = 'n';
    this.position = 0;
    this.name = "DEFAULT";
    this.difficulty = 'e';
  }

  /*****************************************************************
    Constructor - a computer with a name, position, and color.
    @param c the color of the chip
    @param p the position of the player
    @param n the name of the player
    @param d the difficulty of the computer
    */
  
  public ComputerPlayer(char c, int p, String n, char d) {
    this.color = c;
    this.position = p;
    this.name = n;
    this.difficulty = d;
  }
  
  /*****************************************************************
  Calculates the next chip placement for the computer player.
  @param grid - the game board to use in calculations
  */
  public int calculateTurn(int[][] grid) {
    return 0;
  }
  
  /*****************************************************************
  Calculates the chip placement for the computer player and drops it onto the game board.
  @param logic the game logic for the computer to interact with
  */
  public int computerTurn(ConnectLogic logic) {
    int column = 0;
    int chance = 0;
    int[][] grid = logic.getGrid();
    int cturn = calculateTurn(grid);
    
    Random rand1 = new Random();
    Random rand2 = new Random();
    
    if (difficulty == 'h') {
      column = cturn;
    } else if (difficulty == 'm') {
      chance = rand2.nextInt(2);
      if (chance == 0) {
        column = rand1.nextInt(grid.length);
      } else {
        column = cturn;
      }
    } else {
      column = rand1.nextInt(grid.length);
    }
    
    System.out.println(
        "Computer Player " 
        + name + " (" + position + ") placed a chip at column "
        + (column + 1));
    // if you want to delay the input for effect
    //    try {
    //      TimeUnit.SECONDS.sleep(4);
    //    } catch (InterruptedException e) {
    //      e.printStackTrace();
    //    }
    
    if (!logic.isValid(column)) {
      System.out.println("*out of bounds*");
      column = -1;
    } else {
      logic.drop(column);
    }
    return column;
  }

  //Retrieve the computer characteristics
  public char getColor() {
    return color;
  }
  
  public int getPosition() {
    return position;
  }
  
  public String getName() {
    return name;
  }
  
  public char getDifficulty() {
    return difficulty;
  }

  //set the computer characteristics
  public void setColor(char c) {
    this.color = c;
    return;
  }
  
  public void setPosition(int p) {
    this.position = p;
    return;
  }
  
  public void setName(String n) {
    this.name = n;
    return;
  }
  
  public void setDifficulty(char d) {
    this.difficulty = d;
    return;
  }
  
}