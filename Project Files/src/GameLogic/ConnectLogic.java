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
		for(int i=0;i<width-1;i++){
			for(int j=0;j<height-1;j++){
				gameGrid[i][j] = 0;
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
	
		//System.out.println(depth);
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
	public int checkWin(){
		for(int i=0;i<width;i++){
			for(int j=0;j<height;j++){
				for(int p=1;p<=numPlayers;p++){
				
					if(j < height - 3){
				
	
						//vertical win
						/*System.out.println("Vertical:\n--------\nplayer:\t" + p + "\n"
											+ (i+1) + "\t" + (j+1) + "\n" + (i+1) + "\t" 
											+ (j+2) + "\n" + (i+1) + "\t" + (j+3) + "\n"
											+ (i+1) + "\t" + (j+4) + "\n-----------");
						*/if(gameGrid[i][j] == p && gameGrid[i][j+1] == p && 
							gameGrid[i][j+2] == p && gameGrid[i][j+3] == p)
						{
							return p;	
						}
					}

					if(i < width - 3){
					
						//System.out.println("Horizontal-player:\t" + p);
						//horizontal win
						if(gameGrid[i][j] == p && gameGrid[i+1][j] == p && 
							gameGrid[i+2][j] == p && gameGrid[i+3][j] == p)
						{
							return p;	
						}
					}

					if(i < width-3 && j< height-3){
						
						//diagonal down
						//System.out.println("Diagonal Down-player:\t" + p);
						if(gameGrid[i][j] == p && gameGrid[i+1][j+1] == p && 
							gameGrid[i+2][j+2] == p && gameGrid[i+3][j+3] == p)
						{
							return p;	
						}
					}
	

					if(i < width - 3 && j > 3){

							//diagonal up
							//System.out.println("Diagonal Up-player\t" + p);
							if(gameGrid[i][j] == p && gameGrid[i+1][j-1] == p && 
								gameGrid[i+2][j-2] == p && gameGrid[i+3][j-3] == p)
						{
								return p;	
						}
					}
				}
			}
		}	
	
		return 0;
	}

	
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
