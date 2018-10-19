package gamelogic;

import java.util.Scanner;

public class ConnectTest {
	private static ConnectLogic logic;

	private static int[][] grid;

	public static void main(String[] args){
	
		
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
	
		int column = 0;	
		do{
			if(logic.checkWin() != 0){
				System.out.println("Win!");
				break;
			}
			logic.nextTurn();
			column = scn.nextInt() - 1;
			if(!logic.isValid(column)){
				System.out.println("*out of bounds*");
			}else{
				logic.drop(column);
				printBoard();	
			}
		}while(column != -1);
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
