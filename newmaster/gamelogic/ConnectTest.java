package gamelogic;
import java.util.*;

//import playertype.ComputerPlayer;
import playertype.Player;

public class ConnectTest{
  private static ConnectLogic logic;

  private static int[][] grid;

  public static void main(String[] args){

    //take inputs for size
    Scanner scn = new Scanner(System.in);
    System.out.print("--------\nWidth: ");
    int width = 7; //scn.nextInt();
    System.out.print("Height: ");
    int height = 6; //scn.nextInt();
    

    logic = new ConnectLogic(width,height); // establish game size

    //create any players here. usage is (color, position, name);
    //make sure positions are correct, bad stuff will happen if you skip a number
            Player play1 = new Player("d", 1, "hackerman", "m", true);
            Player play2 = new Player("d", 2, "Mr. Frodo");
            Player play3 = new Player("d", 3, "DESTROYEROFWORLDS", "m", true);
            Player play4 = new Player("d", 4, "One-Eyed Willy");

//    ComputerPlayer play1 = new ComputerPlayer("d", 1, "hackerman", 'm');
//    ComputerPlayer play2 = new ComputerPlayer("d", 3, "Mr. Frodo", 'm');
//    ComputerPlayer comp1 = new ComputerPlayer("d", 2, "DESTROYEROFWORLDS", 'm');
//    ComputerPlayer comp2 = new ComputerPlayer("d", 4, "One-Eyed Willy", 'm');
        
        logic = new ConnectLogic(width,height); // establish game size
        
        
        //add the players to the game logic. can't add more than 4
        logic.addPlayer(play1);
        logic.addPlayer(play2);
        logic.addPlayer(play3);
        logic.addPlayer(play4);
        
        grid = logic.getGrid(); //get the game board
        
        int column = 0; 
        do{
            printBoard(); //print the board in terminal
            if(logic.checkWin() != 0){ //if won
                
                Player winner = logic.getCurrentPlayer(); //get the winner's name
                System.out.println(winner.getName() + " Wins!");
                break;
            }
            column = logic.nextTurn(); //place another chip
        }while(column != -1); //will continue to loop until win condition or until out of bounds
        scn.close();
    }
  
  public static void printBoard(){            

    System.out.println("--------------");
    for(int i=0;i<logic.getY();i++){
      for(int j=0;j<logic.getX();j++){
        System.out.print(grid[j][i] + " ");
      }
      System.out.println();
    }

    System.out.println("--------------");

  }

}
