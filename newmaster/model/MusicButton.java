package model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;

public class MusicButton extends Button{

		private final String FONT_PATH = "src/model/resources/kenvector_future.ttf";
		private final String MUSIC_ON_STYLE = "-fx-background-color: transparent; -fx-background-image: url('/model/resources/audioOn.png');";
		private final String MUSIC_OFF_STYLE = "-fx-background-color: transparent; -fx-background-image: url('/model/resources/audioOff.png');";

//		
		public MusicButton(String text) {
			setText(text);
			setButtonFont();
			setPrefWidth(50);
			setPrefHeight(30);
			setStyle(MUSIC_ON_STYLE);
			initializeButtonListener();
		}

		private void setButtonFont() {
			try {
				setFont(Font.loadFont(new FileInputStream(FONT_PATH), 25));
				} catch (FileNotFoundException e) {
					setFont(Font.font("Verdana", 25));
				}
		}
		

		
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
