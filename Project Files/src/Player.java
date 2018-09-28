/*****************************************************************
A Player that plays the Connect 4 game. Has a 

@author Dane Bramble
@version September 2018
*****************************************************************/

public class Player {
	private char color; //The color of the player's chip
	private int position; // The order of the players (1st, 2nd, 3rd, 4th)
	private String name; //The name displayed
	
	/*****************************************************************
    Constructor a player with a name, position, and color
    @param c the color of the chip
    @param p the position of the player
    @param n the name of the player
    *****************************************************************/
	private Player(char c, int p, String n){
		color = c;
		position = p;
		name = n;
	}
	
	//Retrieve the player characteristics
	public char getColor(){
		return color;
	}
	public int getPosition(){
		return position;
	}
	public String getName(){
		return name;
	}
	
	//set the player characteristics
	public void setColor(char c){
		color = c;
		return;
	}
	public void setPosition(int p){
		position = p;
		return;
	}
	public void setName(String n){
		name = n;
		return;
	}
	
	//
	
}
