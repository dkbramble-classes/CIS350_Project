package model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;

/******************************************************************
* Creates a Music Button down-loaded from Kenney.nl
******************************************************************/

public class MusicButton extends Button{

    //CSS to use .png files as the icon path
		private final String FONT_PATH = "src/model/resources/"
		    + "kenvector_future.ttf";
		private final String style = "-fx-background-color: "
		    + "transparent; -fx-background-image: url('/model/resources/"
		    + "audioOn.png');";

/******************************************************************
* constructor for MusicButton class, sets the height and width of 
* icon along with the style
******************************************************************/		
		public MusicButton(String text) {
			setText(text);
			setButtonFont();
			setPrefWidth(50);
			setPrefHeight(30);
			setStyle(style);
			initializeButtonListener();
		}

/******************************************************************
* Sets the size of the button
******************************************************************/
		private void setButtonFont() {
			try {
				setFont(Font.loadFont(new FileInputStream(FONT_PATH), 25));
				} catch (FileNotFoundException e) {
					setFont(Font.font("Verdana", 25));
				}
		}
	
/******************************************************************
*  currently not working... will just exit the program when clicked 
*  on, but will change to a music-off icon when toggled
******************************************************************/		
   
		private void initializeButtonListener() {
			setOnMousePressed(new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent event) {
					if(event.getButton().equals(MouseButton.PRIMARY)) {
						//setExitPressedStyle();
						System.exit(0);
					}
					
				}
				
			});

			
		}

}
