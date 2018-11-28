/*****************************************************************
A Player that plays the Connect 4 game. 
The player chooses the color of the chip and his name.
Each player has an order in which his turn will be.

@author Dane Bramble
@version September 2018
*****************************************************************/

package playertype;

import java.util.Random;

import gamelogic.ConnectLogic;

public class Player {
  private String color; //The color of the player's chip
  private int position; // The order of the players (1st, 2nd, 3rd, 4th)
  private String name; //The name displayed
  private char difficulty; // The computer's level of difficulty (e = easy, m = medium, h = hard)
  private boolean computer; //true if a computer player, false if otherwise
  
  private int min = -1; //the minimum range for the computer to pick
  private int max = -1; // the maximum range for the computer to pick
  private int current = -1; //the column location of this computer's last chip placement


  /*****************************************************************
    Constructor - a player without a name , color, or position.
    Sets default values.
    *****************************************************************/
  public Player() {
    this.color = "n";
    this.position = 0;
    this.name = "DEFAULT";
    this.difficulty = 'e';
    this.computer = false;
  }

  /*****************************************************************
    Constructor - a player with a name, position, and color.
    @param c the color of the chip
    @param p the position of the player
    @param n the name of the player
    */
  
  public Player(String c, int p, String n, char d, boolean b) {
    this.color = c;
    this.position = p;
    this.name = n;
    this.difficulty = d;
    this.computer = b;
  }
  
  /*****************************************************************
  Requests an input as a column, places it on the board if valid.
  @param logic - the game logic for the player to interact with
  */
  
  public int playerTurn(ConnectLogic logic) {
    int column = 0; // this is the selected column, change on implementation
    if (!computer) {   
      System.out.println(name + "'s turn");
      if (!logic.isValid(column)) {
        System.out.println("*out of bounds*");
        column = -1;
      } else {
        logic.drop(column);
        System.out.println("Player " 
            + name + " (" + position + ") placed a chip at column " 
            + (column + 1));
      }
    } else {
      column = this.computerTurn(logic);
    }
    
    return column;
  }
  
  /*****************************************************************
  Calculates the next chip placement for the computer player.
  @param logic - the game board to use in calculations
  */
  public int calculateTurn(ConnectLogic logic) {
    Random rand = new Random();
    int[][] grid = logic.getGrid();
    boolean col = false;
    int calcturn = 0;

    if (min == -1) {
      min = rand.nextInt(grid.length);
      if ((grid.length - min) < 4) {
        min -= 4;
        if (min < 0) {
          min = 0;
        }
      } 

      calcturn = min;
      current = min;
      max = min + 4;
    } else {
      
      if (max > grid.length) {
        max = grid.length;
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
  */
  public int computerTurn(ConnectLogic logic) {
    int column = 0;
    int[][] grid = logic.getGrid();
    
    boolean valid = false;
    
    Random rand = new Random();
    
    if (difficulty == 'm') {
      column = calculateTurn(logic);; 
    } else {
      column = rand.nextInt(grid.length);
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
        column = (column + 1) % grid.length;
      }
    }
    
    if (rangecount >= grid.length) {
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
  
  public char getDifficulty() {
    return difficulty;
  }
  
  public boolean getCompStatus(){
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
  
  public void setDifficulty(char d) {
    this.difficulty = d;
    return;
  }
  
  public void setCompStatus(boolean b){
    this.computer = b;
    return;
  }
}
