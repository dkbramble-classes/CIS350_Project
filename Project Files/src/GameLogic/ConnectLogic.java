import java.util.*;

public class ConnectLogic{

	private int[][]	gameGrid;
	private int width, height;
	private int numPlayers;
	private int activePlayer;	


	/**************************************************************************
	*Connect Logic Constructor
	**************************************************************************/
	public ConnectLogic(int width, int height, int numPlayers){
		gameGrid = new int[width][height];
		this.width = width;
		this.height = height;
		this.numPlayers = numPlayers;
		startGame(width, height);
	}

	/**************************************************************************
	*Start Game: Initialize all spaces to be empty, and start with player 1
	**************************************************************************/
	public void startGame(int width, int height){
		for(int j=0;j<width-1;j++){
			for(int i=0;i<height-1;i++){
				gameGrid[j][i] = 0;
			}
		}

		activePlayer = 0;

	}


	/**************************************************************************
	*Drop: Find the lowest space in the column, set it to be active player
	**************************************************************************/
	public void drop(int col){
		
		int depth = 0;
		
		//find the lowest point before chip hits bottom or another chip	
		while(gameGrid[col][depth+1] == 0){
			depth++;
			if(depth+1 >= height){
				break;
			}
		}
	
		System.out.println(depth);
		gameGrid[col][depth] = activePlayer;
		
	}

	/**************************************************************************
	*Next Turn: Increment whose turn it is
	**************************************************************************/
	public void nextTurn(){
		if(activePlayer < numPlayers){
			activePlayer++;
		}else{
			activePlayer = 1;
		}
		System.out.println("Player " + activePlayer + "'s turn");
	}

	/**************************************************************************
	*Check Win: 
	**************************************************************************/
	/*public int checkWin(){
	
	}*/


	/**************************************************************************
	*Is Valid: Returns the validity of a move, false if out of bounds
	**************************************************************************/
	public boolean isValid(int col){
		return (col >= 0 && col <= width);
	}


	/**************************************************************************
	*Get Grid:
	**************************************************************************/
	public int[][] getGrid(){
		return gameGrid;
	}


	/**************************************************************************
	*Get Y
	**************************************************************************/
	public int getY(){
		return height;
	}
	

	/**************************************************************************
	*Get X
	**************************************************************************/
	public int getX(){
		return width;
	}
}
