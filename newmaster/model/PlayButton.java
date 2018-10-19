package model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;

public class PlayButton extends Button{

		private final String FONT_PATH = "src/model/resources/kenvector_future.ttf";
		private final String PLAY_PRESSED_STYLE = "-fx-background-color: transparent; -fx-background-image: url('/model/resources/blue_button04.png);";
		private final String PLAY_FREE_STYLE = "-fx-background-color: transparent; -fx-background-image: url('/model/resources/blue_button04.png');";
//		private final String MUSIC_PRESSED_STYLE = "-fx-background-color: transparent; -fx-background-image: url('/model/resources/buttonX.png');";
//		private final String MUSIC_FREE_STYLE = "-fx-background-color: transparent; -fx-background-image: url('/model/resources/buttonX.png');";
//		
		
		public PlayButton(String text) {
			setText(text);
			setButtonFont();
			setPrefWidth(191);
			setPrefHeight(50);
			setStyle(PLAY_FREE_STYLE);
			//setStyle(MUSIC_FREE_STYLE);
			initializeButtonListener();
		}

		private void setButtonFont() {
			try {
				setFont(Font.loadFont(new FileInputStream(FONT_PATH), 20));
				} catch (FileNotFoundException e) {
					setFont(Font.font("Verdana", 20));
				}
		}
		
		private void setExitPressedStyle() {
			setStyle(PLAY_PRESSED_STYLE);
			setPrefHeight(20);
			setLayoutY(getLayoutY() + 4);
		}
		private void setExitFreeStyle() {
			setStyle(PLAY_PRESSED_STYLE);
			setPrefHeight(20);
			setLayoutY(getLayoutY() - 4);
		}
		//private void setMusicPressedStyle() {
//			setStyle(PLAY_FREE_STYLE);
//			setPrefHeight(20);
//			setLayoutY(getLayoutY() + 4);
//		}
//
//		private void setMusicFreeStyle() {
//			setStyle(PLAY_FREE_STYLE);
//			setPrefHeight(20);
//			setLayoutY(getLayoutY() - 4);
//		}
		
		private void initializeButtonListener() {
			setOnMousePressed(new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent event) {
					if(event.getButton().equals(MouseButton.PRIMARY)) {
						setExitPressedStyle();
						
						
						//SEND TO NEXT SCREEN TO PICK NAMES AND COLORS
						
						
					}
					
				}
				
			});
			setOnMouseReleased(new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent event) {
					if(event.getButton().equals(MouseButton.PRIMARY)) {
						setExitFreeStyle();
					}
					
				}
				
			});
			
			setOnMouseEntered(new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent event) {
					setEffect(new DropShadow());
					
				}
				
			});
			setOnMouseReleased(new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent event) {
					setEffect(null);
					
				}
				
			});
		}

}