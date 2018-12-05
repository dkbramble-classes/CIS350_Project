/*****************************************************************
A Player that plays the Connect 4 game. 
The player chooses the color of the chip and his name.
Each player has an order in which his turn will be.
The player can also be a computer player with different difficulties


@author Dane Bramble
@version November 2018
*****************************************************************/

package playertype;

import gamelogic.ConnectLogic;
import java.util.Random;



public class Player {
  private String color; //The color of the player's chip
  private int position; // The order of the players (1st, 2nd, 3rd, 4th)
  private String name; //The name displayed
  private String difficulty; // The computer's level of difficulty (e = easy, m = medium, h = hard)
  private boolean computer; //true if a computer player, false if otherwise
  
  private int min = -1; //the minimum range for the computer to pick
  private int max = -1; // the maximum range for the computer to pick
  private int current = -1; //the column location of this computer's last chip placement


  /*****************************************************************
    Constructor - a player without any inputs
    Sets default values.
    *****************************************************************/
  public Player() {
    this.color = "n";
    this.position = 0;
    this.name = "DEFAULT";
    this.difficulty = "e";
    this.computer = false;
  }

  /*****************************************************************
    Constructor - a player with all valid inputs
    .
    @param c the color of the chip
    @param p the position of the player
    @param n the name of the player
    @param d the difficulty of the computer player
    @param b whether or not the player is a computer
  */
  
  public Player(String c, int p, String n, String d, boolean b) {
    this.color = c;
    this.position = p;
    this.name = n;
    this.difficulty = d;
    this.computer = b;
  }
  
  /*****************************************************************
  Constructor - a player with a name, position, and color. 
  If not a computer player, can provide only the necessary details
  
  @param c the color of the chip
  @param p the position of the player
  @param n the name of the player
*/

  public Player(String c, int p, String n) {
    this.color = c;
    this.position = p;
    this.name = n;
    this.difficulty = "e";
    this.computer = false;
  }
  
  /*****************************************************************
  Takes in a column and attempts to place it on the grid. If it is a
  computer player, then it will generate the next column on its own.
  
  @param logic - the game logic for the player to interact with
  @param column - the column where the Player want to place the chip
  @return column where the computer player places the chip / error codes
  */
  
  public int playerTurn(ConnectLogic logic, int column) {
    if (!computer) {   //if not a computer
      System.out.println(name + "'s turn");
      if (!logic.isValid(column)) { //check if a chip can be placed
        System.out.println("*out of bounds*");
        column = -1; //exit the game
      } else {
        logic.drop(column); ///place the chip
        System.out.println("Player " 
            + name + " (" + position + ") placed a chip at column " 
            + (column + 1));
      }
    } else {
      column = this.computerTurn(logic); //generate column for computer
    }
    
    return column;
  }
  
  /*****************************************************************
  Calculates the next chip placement for the computer player.
  @param logic - the game board to use in calculations
  @return calcturn - the calculated column
  */
  public int calculateTurn(ConnectLogic logic) {
    Random rand = new Random(); //to calculate random columns
    int[][] grid = logic.getGrid(); //grid of the board
    boolean col = false; //if the column is available
    int calcturn = 0; //column to be generated

    if (min == -1) { //if the range has not been set
      min = rand.nextInt(grid.length); //set the range
      if ((grid.length - min) < 4) { //if the range is beyond the game grid, fix it
        min -= 4;
        if (min < 0) {
          min = 0;
        }
      } 

      calcturn = min; // first chip placement
      current = min;
      max = min + 4; 
    } else {
      
      if (max > grid.length) {
        max = grid.length; //if the range is beyond the game grid, fix it
      }
      
      for (int i = min; i < max; i++) {
        int count = 0; //amount of chips on top
        int zeroes = 0; // amount spaces available in the column
        
        for (int j = 1; j < grid[i].length; j++) {
          if (grid[i][j] == position) { //if the chips on top are yours
            count++;
          } else if (grid[i][j] != 0 
              && grid[i][j] != position) { //if someone else's chip is on top dont bother
            break; 
          } else { //if this is an empty space
            zeroes++;
          }
        }
        
        if (count > 1 && zeroes >= 2) { //if you are on top and there is room keep placing chips
          calcturn = i;
          col = true;
          break;
        }
      }
      
      if (!col) {
        if ((current + 1) >= max && (current - 1) <= min) {
          calcturn = rand.nextInt((current + 1) - (current - 1))
              + (current - 1); //picks on of the slots by current position
        } else {
          calcturn = rand.nextInt(max - min) + min;
        }
        boolean valid = false;
        int rangecount = 0; // don't check more than 4 times
        while (!valid && rangecount < 4) { //if not a valid column, move until one is found 
          if (logic.isValid(calcturn)) {
            valid = true;
          } else {
            calcturn++;
            rangecount++;
            if (calcturn > max) {
              calcturn = min;
            }
          }
        }
      }  
    }
    
    return calcturn;
  }
  
  
  /*****************************************************************
  Calculates the chip placement for the computer player and drops it onto the game board.
  @param logic the game logic for the computer to interact with
  @return column - the column the computer has picked
  */
  public int computerTurn(ConnectLogic logic) {
    int column = 0; //column to be generated
    int[][] grid = logic.getGrid();
    
    boolean valid = false; //don't stop until the chip is valid
    
    Random rand = new Random();
    
    if (difficulty.equals("m")) { 
      column = calculateTurn(logic); // if medium difficulty, generate column
    } else {
      column = rand.nextInt(grid.length); // easy difficulty is just random
    }
    
    // if you want to delay the input for effect
    //try {
    //TimeUnit.SECONDS.sleep(4);
    //}catch (InterruptedException e) {
    //e.printStackTrace();
    //}
    int rangecount = 0;
    while (!valid && rangecount < grid.length) { //if not a valid column, move until one is found 
      if (logic.isValid(column)) {
        valid = true;
        logic.drop(column);
      } else {
        column = (column + 1) % grid.length; //move to next column, loops back on the grid
      }
    }
    
    if (rangecount >= grid.length) { // if no chips could be placed, exit game
      column = -1;
    } else {
      System.out.println(
          "Computer Player " 
          + name + " (" + position + ") placed a chip at column "
          + (column + 1));
    }
    
    current = column;
    return column;
  }

  //Retrieve the player characteristics
  public String getColor() {
    return color;
  }
  
  public int getPosition() {
    return position;
  }
  
  public String getName() {
    return name;
  }
  
  public String getDifficulty() {
    return difficulty;
  }
  
  public boolean getCompStatus() {
    return computer;
  }

  //set the player characteristics
  public void setColor(String c) {
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
  
  public void setDifficulty(String d) {
    this.difficulty = d;
    return;
  }
  
  public void setCompStatus(boolean b) {
    this.computer = b;
    return;
  }
}
