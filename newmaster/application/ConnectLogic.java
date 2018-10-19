package application;
import java.util.*;


public class ConnectLogic{


	private int[][]	gameGrid;
	private int width, height;
	
	public ConnectLogic(int width, int height){
		gameGrid = new int[width][height];
		this.width = width;
		this.height = height;
		startGame(width, height);	
	}

	public void startGame(int width, int height){
		for(int i=0;i<height-1;i++){
			for(int j=0;j<width-1;j++){
				gameGrid[i][j] = 0;
			}
		}
	}
	

	public void drop(int col, int player){
		
		int depth = 0;
		
		//find the lowest point before chip hits bottom or another chip
		if(depth + 1 < height){
			while(gameGrid[col][depth+1] == 0){
				depth++;
			}
			
			gameGrid[col][depth] = player;
		}
	}


	public int[][] getGrid(){
		return gameGrid;
	}

	public int getY(){
		return height;
	}
	
	public int getX(){
		return width;
	}


}