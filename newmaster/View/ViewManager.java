//icons imported from https://kenney.nl/assets/ui-pack

package View;

import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import connectsystem.MusicPlayer;
import connectsystem.SystemOptions;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import model.ExitButton;
import model.PlayButton;
import model.MusicButton;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;

public class ViewManager {
	
	private static final int HEIGHT = 600; 
	private static final int WIDTH = 800;
	private AnchorPane mainPane;
	private Scene mainScene;
	private Stage mainStage;
	
	private final static int MENU_BUTTONS_START_X = 100;
	private final static int MENU_BUTTONS_START_Y = 150;
	private final String FONT_PATH = "kenvector_future.ttf";
	
/******************************************************************
* Class that deals with the second screen of the program; the 
* player details including the names and colors of each person 
* playing Connect 4
 * @throws LineUnavailableException 
 * @throws IOException 
 * @throws UnsupportedAudioFileException 
******************************************************************/

	public ViewManager() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		mainPane = new AnchorPane();
		mainScene = new Scene(mainPane, WIDTH, HEIGHT);
		mainStage = new Stage();
		mainStage.setScene(mainScene);
		createButtons();
		createBackground();
		
	}
	
/******************************************************************
* returns the current stage
******************************************************************/
	//returns the current stage
	public Stage getMainStage() {
		return mainStage;
	}
	
/******************************************************************
* Creates labels and buttons and adds all of them to the main pane
 * @throws LineUnavailableException 
 * @throws IOException 
 * @throws UnsupportedAudioFileException 
******************************************************************/
	 
	private void createButtons() throws UnsupportedAudioFileException, 
	 IOException, LineUnavailableException {
		Label title = new Label("CONNECT 4");
		title.setLayoutX(180);
		title.setLayoutY(100);
		title.setFont(Font.loadFont(new FileInputStream(FONT_PATH),70));
		title.setTextFill(Color.DARKSLATEGRAY);
		
		ExitButton exitButton = new ExitButton(null);
		PlayButton startButton = new PlayButton("PLAY NOW");
		//PlayButton intenseButton = new PlayButton("INTENSE4");
		MusicButton musicButton = new MusicButton(null);
		mainPane.getChildren().addAll(exitButton, startButton, 
		    musicButton, title);
		
		exitButton.setLayoutX(700);
		exitButton.setLayoutY(0);
		startButton.setLayoutX(320);
		startButton.setLayoutY(300);
//	intenseButton.setLayoutX(320);
//	intenseButton.setLayoutY(330);
		musicButton.setLayoutX(640);
		musicButton.setLayoutY(27);
		
/******************************************************************
*  creates an instance of playerDetailsManager and creates the 
*  screen when user clicks on the CONNECT 4 button on the main menu
******************************************************************/
		 
		  startButton.setOnAction(new EventHandler<ActionEvent>()  {
			@Override
			public void handle(ActionEvent event) {
				PlayerDetailsManager playerDetailsManager;
        try {
          playerDetailsManager = new 
              PlayerDetailsManager();
          playerDetailsManager.createDetailsScreen(mainStage);
        } catch (UnsupportedAudioFileException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        } catch (IOException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        } catch (LineUnavailableException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
				

			}
			
		});

	}
		
/******************************************************************
* creates the background to the player details screen. same as 
* main menu background.
******************************************************************/
  private void createBackground() {
	
		
		Image backgroundImage = new Image("model/resources/"
		    + "Slider-CL01-Background-256x256.png",
		    256,256,false,true);
		BackgroundImage background = new BackgroundImage(backgroundImage,
		    BackgroundRepeat.REPEAT,
		    BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null);
		mainPane.setBackground(new Background(background));
		
	}
}
