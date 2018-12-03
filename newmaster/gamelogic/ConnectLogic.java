package gamelogic;

//import playertype.ComputerPlayer;
import playertype.Player;

public class ConnectLogic {

  public int[][] gameGrid;

  private int width; // the width of the board
  private int height; // the height of the board
  private int numPlayers; //number of players in the game
  private int activePlayer; //the position of the player currently playing

  public Player[] playerlist = new Player[4]; //list holding all real players
  //private ComputerPlayer[] complist = new ComputerPlayer[4]; //list holding all computer players


  /**************************************************************************
   *Connect Logic Constructor.
   */
  public ConnectLogic() {
    
    //default size in case of error
    this.width = 7;
    this.height = 6;
    this.numPlayers = 0;
  }

  /**************************************************************************
   *Start Game: Initialize all spaces to be empty, and start with player 1.
   */
  public void startGame() {
    
    gameGrid = new int[width][height];
    
    for (int i = 0; i < width - 1; i++) {
      for (int j = 0; j < height - 1; j++) {
        gameGrid[i][j] = 0;
      }
    }

    activePlayer = 1;

  }


  /*************************************************************************
   * Update Grid Size: Variable game board sized for different number of 
   *                   players.
   */
  public void updateGridSize() {
    if (numPlayers == 3) {
      height = 7;
      width = 9;
    } else if (numPlayers == 4) {
      height = 9;
      width = 10;
    } else { //default for 2 players or error
      height = 6;
      width = 7;
    }
 
  }
  /**************************************************************************
   *Drop: Find the lowest space in the column, set it to be active player.
   *@param col the column of the game board in which to drop the chip
   */
  public int drop(int col) {

    int depth = 0;

    //find the lowest point before chip hits bottom or another chip 
    if (height > 1) { //just in case
      while (gameGrid[col][depth + 1] == 0) {
        depth++;
        if (depth + 1 >= height) {
          break;
        }
      }
    }

    //System.out.println(depth);
    gameGrid[col][depth] = activePlayer;
    return depth;
  }

  /**************************************************************************
   *Next Turn: Increment whose turn it is, and run the logic for the current 
   *player to place a new chip.
   **************************************************************************/
  public int nextTurn(int input) {

    int column = -1;
    for (Player play : playerlist) { //Find if any players are the current player
      if (play != null) {
        if (play.getPosition() == activePlayer) {
          column = play.playerTurn(this, input); //place the players new chip
          break;
        }
      }
    }

//    for (ComputerPlayer play : complist) { //find if any computer players are the current player
//      if (play != null) {
//        if (play.getPosition() == activePlayer) {
//          column = play.computerTurn(this); //place the computer players new chip
//          break;
//        }
//      }
//    }
    
    boolean possible = false;
    for (int i = 0; i < width; i++) { //check if any more chips can be added
      if (gameGrid [i][0] == 0) {
        possible = true;
        break;
      }
    }

    if (!possible) {
      column = -20; //if no more open slots, game over
      System.out.println("Game board is filled, it's a tie!");
    }
    
    if (activePlayer < numPlayers) { // set the next player
      activePlayer++;
    } else {
      activePlayer = 1;
    }

    return column; //if -1, end the game
  }

  /**************************************************************************
   *Check Win: find if a line of 4 exists horizontally, vertically, or
   *           diagonally.
   **************************************************************************/

  public int checkWin() {
    for (int i = 0; i < width; i++) {
      for (int j = 0; j < height; j++) {

        //check for each player
        for (int p = 1; p <= numPlayers; p++) {

          if (j < height - 3) {


            //vertical win
            if (gameGrid[i][j] == p && gameGrid[i][j + 1] == p 
                && gameGrid[i][j + 2] == p && gameGrid[i][j + 3] == p) {
              return p;   
            }
          }

          if (i < width - 3) {

            //horizontal win
            if (gameGrid[i][j] == p && gameGrid[i + 1][j] == p 
                && gameGrid[i + 2][j] == p && gameGrid[i + 3][j] == p) {
              return p;   
            }
          }

          if (i < width - 3 && j < height - 3) {

            //diagonal down 
            if (gameGrid[i][j] == p && gameGrid[i + 1][j + 1] == p 
                && gameGrid[i + 2][j + 2] == p && gameGrid[i + 3][j + 3] == p) {
              return p;   
            }
          }


          if (i < width - 3 && j > 3) {

            //diagonal up                       
            if (gameGrid[i][j] == p && gameGrid[i + 1][j - 1] == p 
                && gameGrid[i + 2][j - 2] == p && gameGrid[i + 3][j - 3] == p) {
              return p;   
            }
          }
        }
      }
    }   

    return 0;
  }


  /**************************************************************************
   *Is Valid: Returns the validity of a move, false if out of bounds.
   *@param col the column of the game board in order check validity
   */
  public boolean isValid(int col) {
    boolean valid = false;
    if (col >= 0 && col < width) { //if within the column range
      for (int depth : gameGrid[col]) {
        if (depth == 0) { // if the column isn't filled with chips
          valid = true;
        }
      }
    }

    return valid;
  }

  /**************************************************************************
   *Adds a player to the list of current players. 
   *@param play - the player to be added to the list
   */
  public void addPlayer(Player play) {
    if (numPlayers < 4) { //can't have more than 4
      for (int i = 0; i < 4; i++) {
        if (playerlist[i] == null) {
          playerlist[i] = play;
          numPlayers++;
          break;
        }
      }
    }
    updateGridSize();
    return;
  }

  /**************************************************************************
   *Adds a computer player to the list of current players. 
   *@param play - the computer player to be added to the list
   */
//  public void addPlayer(ComputerPlayer play) {
//    if (numPlayers < 4) { //can't have more than 4
//      for (int i = 0; i < 4; i++) {
//        if (complist[i] == null) {
//          complist[i] = play;
//          numPlayers++;
//          break;
//        }
//      }
//    }
//    return;  
//  }


  /**************************************************************************
   *Return the game Grid.
   **************************************************************************/
  public int[][] getGrid() {
    return gameGrid;
  }


  /**************************************************************************
   *Return the height of the grid.
   **************************************************************************/
  public int getY() {
    return height;
  }


  /**************************************************************************
   *Return the width of the grid.
   **************************************************************************/
  public int getX() {
    return width;
  }

  /**************************************************************************
   *Get return the name of the player that is currently playing.
   **************************************************************************/
  public Player getCurrentPlayer() {
    Player current = new Player();
    for (Player play : playerlist) { //check if a player is the current player
      if (play != null) {
        if (play.getPosition() == activePlayer) {
          current = play;
          break;
        }
      }
    }

//    for (ComputerPlayer play : complist) { //check if a computer player is the current player
//      if (play != null) {
//        if (play.getPosition() == activePlayer) {
//          current = play.getName();
//          break;
//        }
//      }
//
//    }
    return current;
  }

  /**************************************************************************
   * Set Space: allows an individual space to be set for testing purposes.
   * @param col column place
   * @param depth depth place
   * @param player number of the player
   */
  public void setSpace(int col, int depth, int player) {
    gameGrid[col][depth] = player;
  }

  /**************************************************************************
   * Get Player List: used for testing
   * @return list of players in the game
   */
  public Player[] getPlayerlist() {
    return playerlist;
  }

  /**************************************************************************
   * Set Number of Players: used for testing
   * @param n Number of players
   */
  public void setNumPlayers(int n) {
    numPlayers = n;
  }
}
