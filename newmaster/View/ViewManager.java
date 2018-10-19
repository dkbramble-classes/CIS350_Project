//icons imported from https://kenney.nl/assets/ui-pack
package View;

import javafx.scene.control.Button;
import javafx.event.EventHandler;
import javafx.event.Event;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.awt.Insets;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import model.ExitButton;
import model.PlayButton;
import model.MusicButton;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.stage.Stage;

public class ViewManager {
	
	private static final int HEIGHT = 600; 
	private static final int WIDTH = 800;
	private AnchorPane mainPane;
	private Scene mainScene;
	private Stage mainStage;
	
	private final static int MENU_BUTTONS_START_X = 100;
	private final static int MENU_BUTTONS_START_Y = 150;
	private final String FONT_PATH = "src/model/resources/kenvector_future.ttf";
	
	
	public ViewManager() throws FileNotFoundException {
		mainPane = new AnchorPane();
		mainScene = new Scene(mainPane, WIDTH, HEIGHT);
		mainStage = new Stage();
		mainStage.setScene(mainScene);
		createButtons();
		createBackground();
	}
		
	public Stage getMainStage() {
		return mainStage;
	}
	
	
	private void createButtons() throws FileNotFoundException {
		Label title = new Label("CONNECT 4");
		title.setLayoutX(200);
		title.setLayoutY(100);
		title.setFont(Font.loadFont(new FileInputStream(FONT_PATH), 70));
		title.setTextFill(Color.DARKSLATEGRAY);
		
		ExitButton exitButton = new ExitButton(null);
		PlayButton startButton = new PlayButton("PLAY NOW");
		PlayButton intenseButton = new PlayButton("INTENSE4");
		MusicButton musicButton = new MusicButton(null);
		mainPane.getChildren().addAll(exitButton, startButton, intenseButton, musicButton, title);
		
		exitButton.setLayoutX(700);
		exitButton.setLayoutY(0);
		startButton.setLayoutX(320);
		startButton.setLayoutY(260);
		intenseButton.setLayoutX(320);
		intenseButton.setLayoutY(330);
		musicButton.setLayoutX(640);
		musicButton.setLayoutY(27);
		
		startButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				PlayerDetailsManager playerDetailsManager = new PlayerDetailsManager();
				playerDetailsManager.createDetailsScreen(mainStage);
				
//				GameManager gameManager = new GameManager();
//				gameManager.createNewGame(mainStage);
				
			}
			
		});

	}

	
	
	
	private void createBackground() {
	
		
		Image backgroundImage = new Image("model/resources/Slider-CL01-Background-256x256.png", 256,256,false,true);
		BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null);
		mainPane.setBackground(new Background(background));
		
	}
}
