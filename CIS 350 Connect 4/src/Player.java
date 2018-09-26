
public class Player {
	char color;
	int position;
	String name;
	
	private Player(char c, int p, String n){
		color = c;
		position = p;
		name = n;
	}
	private char getColor(){
		return color;
	}
	private int getPosition(){
		return position;
	}
	private String getName(){
		return name;
	}
	private void setColor(char c){
		color = c;
		return;
	}
	private void setPosition(int p){
		position = p;
		return;
	}
	private void setName(String n){
		name = n;
		return;
	}
	
}
