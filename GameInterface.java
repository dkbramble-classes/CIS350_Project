public interface GameInterface{
	
	/* 0 for empty, numbers for players */
	int[][] gameGrid;
	

	/*************************************
	*Start Game: begin a game with a 
	*			 certain amount of players
	*************************************/
	void startGame(int playerCount, int[] players);		
	

	/*************************************
	*Pick Column: Player chooses chip loc
	*************************************/
	void pickColumn(int );
	
	
	/*************************************
	*Next Turn: Switch active player
	*************************************/
	void nextTurn();
	
	
	/*************************************
	*Check win: Determines if there is a 
	*			winning connection
	*************************************/
	Player checkWin(int[][] grid);
	

	/*************************************
	*Fall: "animate" the chip falling
	*************************************/
	void fall(int[][] grid, Position p);

}
