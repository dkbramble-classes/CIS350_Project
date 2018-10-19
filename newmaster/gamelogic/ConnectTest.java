package gamelogic;

import java.util.Scanner;


/***********************************************************************
  *Connect Test: Terminal interface for connect 4 game
  *@author Ben Townsend
  *@date 10/19/18
  *********************************************************************/

public class ConnectTest {
	private static ConnectLogic logic;

	private static int[][] grid;

	
    /******************************************************************
      *Main method test
      ****************************************************************/
    
    public static void main(String[] args){
	
		//setup board
		Scanner scn = new Scanner(System.in);
		System.out.print("--------\nWidth: ");
		int width = scn.nextInt();
		System.out.print("Height: ");
		int height = scn.nextInt();
		System.out.print("Number of players: ");
		int players = scn.nextInt();
		
		logic = new ConnectLogic(width,height,players);
		
		
		
		grid = logic.getGrid();
		
		printBoard();
	
		
        //game loop
        int column = 0;	
		do{
            //make sure no one has won yet
			if(logic.checkWin() != 0){
				System.out.println("Win!");
				break;
			}

            //player input
			logic.nextTurn();
			column = scn.nextInt() - 1;
			if(!logic.isValid(column)){
				System.out.println("*out of bounds*");
			
            //drop the chip
            }else{
				logic.drop(column);
				printBoard();	
			}
		}while(column != -1);
	}


    /***************************************************************
      *Print Board: displays the current game state to terminal
      *************************************************************/
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
