import java.util.*;

public class ConnectTest{
	private static ConnectLogic logic;
	
	private static int[][] grid;

	public static void main(String[] args){	
		logic = new ConnectLogic(5,4);
		grid = logic.getGrid();
		
		printBoard();

		Scanner scn = new Scanner(System.in);
		int column = scn.nextInt();

		logic.drop(column, 1);
		printBoard();
	}

	public static void printBoard(){			
		for(int i=0;i<logic.getX();i++){
			for(int j=0;j<logic.getY();j++){
				System.out.print(grid[i][j]);
			}
			System.out.println();
		}
	}
}
