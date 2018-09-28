/*****************************************************************
Options to be used on the player options screen
Edits characteristics for each player

@author Dane Bramble
@version September 2018
*****************************************************************/
import java.util.Scanner; 

public class PlayerOptions {
	/*****************************************************************
	Allows input to set the name of a player

	@param play the Player whose name will be updated
	*****************************************************************/
	private void inputName(Player play){
		Scanner scan = new Scanner(System.in);
		String name = scan.nextLine(); 
		if (name.length() < 11){
			play.setName(name);
		}
		else{
			System.out.println("Name can only be up to ten characters!");
		}
		return;
	}
	/*****************************************************************
	Allows input to set the name of a player

	@param play the Player whose name will be updated
	@param c the color that the player has chosen
	*****************************************************************/
	private void selectColor(Player play, char c){
		play.setColor(c);
		return;
	}
}
