//icons imported from https://www.iconfinder.com/icons/1288819/action_call_dial_four_fourth_number_phone_icon
//https://www.iconfinder.com/icons/2075802/arrow_back_redo_return_icon
package View;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.ExitButton;
import model.MusicButton;
import model.PlayButton;

public class PlayerDetailsManager {
	
	private AnchorPane playerDetailsPane;
	private Scene playerDetailsScene;
	private Stage playerDetailsStage;
	private final String FONT_PATH = "src/model/resources/kenvector_future.ttf";
	private static final int SCREEN_WIDTH = 800;
	private static final int SCREEN_HEIGHT = 600;
	
	private Stage menuStage;
	private Stage mainStage;
	
	
	public PlayerDetailsManager() {
		initializeStage();
		createBackground();
		createItems();
	}

	private void initializeStage() {
		playerDetailsPane = new AnchorPane();
		playerDetailsScene = new Scene(playerDetailsPane, SCREEN_WIDTH, SCREEN_HEIGHT);
		playerDetailsStage = new Stage();
		playerDetailsStage.setScene(playerDetailsScene);
		
	}
	
	public void createDetailsScreen(Stage menuStage) {
		this.menuStage = menuStage;
		this.menuStage.hide();
		playerDetailsStage.show();
		
	}
	
	private void createItems(){
		Label title = new Label("PLAYER   DETAILS");
		Label names = new Label("NAME");
		Label colors = new Label("COLOR");
		title.setLayoutX(135);
		title.setLayoutY(80);
		names.setLayoutX(165);
		names.setLayoutY(170);
		colors.setLayoutX(500);
		colors.setLayoutY(170);
		
		Button returnToMenu = new Button(null);
		returnToMenu.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				playerDetailsStage.hide();
				menuStage.show();
				
			}
		});
		
		Button playGame = new Button(null);
		playGame.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
//				GameManager gameManager = new GameManager();
//				gameManager.createNewGame(playerDetailsStage);
//				
			}
		});
		
		
		//player icons
		Button playerOneIcon = new Button(null);
		Button playerTwoIcon = new Button(null);
		Button playerThreeIcon = new Button(null);
		Button playerFourIcon = new Button(null);
		
		
		ChoiceBox<String> color1 = new ChoiceBox<>();
		color1.getItems().addAll("Red", "Blue", "Green", "White");
		color1.setPrefWidth(80);
		color1.setLayoutX(520);
		color1.setLayoutY(220);
		
		//button will be what is pressed after entering in all player data. 
		//once you go to the next screen (the game), it will capture
		//all the choices and set the to the value of each player.
		//button.setOnAction(e -> getChoice(color1));
		
		ChoiceBox<String> color2 = new ChoiceBox<>();
		color2.getItems().addAll("Red", "Blue", "Green", "White");
		color2.setPrefWidth(80);
		color2.setLayoutX(520);
		color2.setLayoutY(290);
		ChoiceBox<String> color3 = new ChoiceBox<>();
		color3.getItems().addAll("Red", "Blue", "Green", "White");
		color3.setPrefWidth(80);
		color3.setLayoutX(520);
		color3.setLayoutY(360);
		ChoiceBox<String> color4 = new ChoiceBox<>();
		color4.getItems().addAll("Red", "Blue", "Green", "White");
		color4.setPrefWidth(80);
		color4.setLayoutX(520);
		color4.setLayoutY(430);
		
		returnToMenu.setStyle("-fx-background-color: transparent; -fx-background-image: url('/model/resources/backArrow.png');");
		returnToMenu.setPrefHeight(45);
		returnToMenu.setPrefWidth(64);
		returnToMenu.setLayoutX(20);
		returnToMenu.setLayoutY(20);
		
		playGame.setStyle("-fx-background-color: transparent; -fx-background-image: url('/model/resources/playButton.png');");
		playGame.setPrefHeight(120);
		playGame.setPrefWidth(120);
		playGame.setLayoutX(650);
		playGame.setLayoutY(470);
		
		
		playerOneIcon.setStyle("-fx-background-color: transparent; -fx-background-image: url('/model/resources/numberone.png');");
		playerOneIcon.setPrefHeight(48);
		playerOneIcon.setPrefWidth(48);
		playerOneIcon.setLayoutX(50);
		playerOneIcon.setLayoutY(220);
		
		
		playerTwoIcon.setStyle("-fx-background-color: transparent; -fx-background-image: url('/model/resources/numbertwo.png');");
		playerTwoIcon.setPrefHeight(48);
		playerTwoIcon.setPrefWidth(48);
		playerTwoIcon.setLayoutX(50);
		playerTwoIcon.setLayoutY(290);
		
		playerThreeIcon.setStyle("-fx-background-color: transparent; -fx-background-image: url('/model/resources/numberthree.png');");
		playerThreeIcon.setPrefHeight(48);
		playerThreeIcon.setPrefWidth(48);
		playerThreeIcon.setLayoutX(50);
		playerThreeIcon.setLayoutY(360);
		
		playerFourIcon.setStyle("-fx-background-color: transparent; -fx-background-image: url('/model/resources/numberfour.png');");
		playerFourIcon.setPrefHeight(48);
		playerFourIcon.setPrefWidth(48);
		playerFourIcon.setLayoutX(50);
		playerFourIcon.setLayoutY(430);
		
		try {
			title.setFont(Font.loadFont(new FileInputStream(FONT_PATH), 50));
			names.setFont(Font.loadFont(new FileInputStream(FONT_PATH), 30));
			colors.setFont(Font.loadFont(new FileInputStream(FONT_PATH), 30));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		title.setTextFill(Color.DARKSLATEGRAY);
		names.setTextFill(Color.DARKSLATEGRAY);
		colors.setTextFill(Color.DARKSLATEGRAY);
		
		playerDetailsPane.getChildren().addAll(title, names, colors, playerOneIcon, playerTwoIcon, playerThreeIcon, playerFourIcon,
				color1, color2, color3, color4, returnToMenu, playGame);
		
		
	}
	
//	private void getPlayerColor1(ChoiceBox<String> color1) {
//		String playerOneColor = color1.getValue();
//	}
	
	private void createBackground() {
	
		
		Image backgroundImage = new Image("model/resources/Slider-CL01-Background-256x256.png", 256,256,false,true);
		BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null);
		playerDetailsPane.setBackground(new Background(background));
		
	}
}
