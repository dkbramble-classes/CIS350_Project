package gamelogic;

//import playertype.ComputerPlayer;
import playertype.Player;

public class ConnectLogic {

  private int[][] gameGrid;

  private int width; // the width of the board
  private int height; // the height of the board
  private int numPlayers; //number of players in the game
  private int activePlayer; //the position of the player currently playing

  private Player[] playerlist = new Player[4]; //list holding all real players
  //private ComputerPlayer[] complist = new ComputerPlayer[4]; //list holding all computer players


  /**************************************************************************
   *Connect Logic Constructor.
   *@param width the width of the game board
   *@param height the height of the game board
   */
  public ConnectLogic(int width, int height) {
    
    //default size in case of error
    if (width <= 0 || height <= 0) {
      width = 7;
      height = 6;
    }
    gameGrid = new int[width][height];
    this.width = width;
    this.height = height;
    this.numPlayers = 0;
    startGame(width, height);
  }

  /**************************************************************************
   *Start Game: Initialize all spaces to be empty, and start with player 1.
   *@param width the width of the game board
   *@param height the height of the game board
   */
  public void startGame(int width, int height) {
    for (int i = 0; i < width - 1; i++) {
      for (int j = 0; j < height - 1; j++) {
        gameGrid[i][j] = 0;
      }
    }

    activePlayer = 0;

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
    if (activePlayer < numPlayers) { // set the next player
      activePlayer++;
    } else {
      activePlayer = 1;
    }

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
      column = -1; //if no more open slots, game over
      System.out.println("Game board is filled, it's a tie!");
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


//  public ComputerPlayer[] getComplist() {
//    return complist;
//  }

  public void setNumPlayers(int n) {
    numPlayers = n;
  }
}
