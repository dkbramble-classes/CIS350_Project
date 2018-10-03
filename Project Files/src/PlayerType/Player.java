/*****************************************************************
A Player that plays the Connect 4 game. 
The player chooses the color of the chip and his name.
Each player has an order in which his turn will be.

@author Dane Bramble
@version September 2018
*****************************************************************/
package PlayerType;
public class Player {
	private char color; //The color of the player's chip
	private int position; // The order of the players (1st, 2nd, 3rd, 4th)
	private String name; //The name displayed
	
	/*****************************************************************
    Constructor - a player without a name , color, or position
    Sets default values 
    *****************************************************************/
    Player(){
		this.color = 'n';
		this.position = 0;
		this.name = "DEFAULT";
	}
	
	/*****************************************************************
    Constructor - a player with a name, position, and color
    @param c the color of the chip
    @param p the position of the player
    @param n the name of the player
    *****************************************************************/
	Player(char c, int p, String n){
		this.color = c;
		this.position = p;
		this.name = n;
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
		this.color = c;
		return;
	}
	public void setPosition(int p){
		this.position = p;
		return;
	}
	public void setName(String n){
		this.name = n;
		return;
	}
	
}
