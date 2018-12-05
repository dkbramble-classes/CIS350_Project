//icons imported from https://kenney.nl/assets/ui-pack

package view;

import java.io.FileInputStream;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
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
import javafx.stage.StageStyle;
import model.ExitButton;
import model.PlayButton;

public class ViewManager {

  private static final int HEIGHT = 600; 
  private static final int WIDTH = 800;
  private AnchorPane mainPane;
  private Scene mainScene;
  private Stage mainStage;
  private Stage gameStage;
  
  private static final String FONT_PATH = "kenvector_future.ttf";

  /******************************************************************
  * Class that deals with the second screen of the program; the 
  * player details including the names and colors of each person 
  * playing Connect 4.
  * @throws IOException if there is an issue with the file paths
  */

  public ViewManager() throws IOException {
    mainPane = new AnchorPane();
    mainScene = new Scene(mainPane, WIDTH, HEIGHT);
    mainStage = new Stage();
    mainStage.initStyle(StageStyle.UNDECORATED);
    mainStage.setScene(mainScene);
    createButtons();
    createBackground();

  }

  /******************************************************************
  * returns the current stage.
  ******************************************************************/
  //returns the current stage
  public Stage getMainStage() {
    return mainStage;
  }

  /******************************************************************
  * Creates labels and buttons and adds all of them to the main pane.
  * @throws IOException if there is an issue with FONT_PATH
  */

  private void createButtons() throws
      IOException {
    Label title = new Label("CONNECT 4");
    title.setLayoutX(180);
    title.setLayoutY(100);
    title.setFont(Font.loadFont(new FileInputStream(FONT_PATH),70));
    title.setTextFill(Color.DARKSLATEGRAY);

    ExitButton exitButton = new ExitButton(null);
    PlayButton startButton = new PlayButton("PLAY NOW");
    mainPane.getChildren().addAll(exitButton, startButton, title);
    
    exitButton.setLayoutX(700);
    exitButton.setLayoutY(0);
    startButton.setLayoutX(320);
    startButton.setLayoutY(300);

    /******************************************************************
    *  creates an instance of playerDetailsManager and creates the 
    *  screen when user clicks on the CONNECT 4 button on the main menu.
    ******************************************************************/
    
    startButton.setOnAction(new EventHandler<ActionEvent>()  {
      @Override
      public void handle(ActionEvent event) {
        PlayerDetailsManager playerDetailsManager;
        try {
          playerDetailsManager = new PlayerDetailsManager();
          playerDetailsManager.createDetailsScreen(mainStage);
        } catch (IOException e) {
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
  
  /******************************************************************
  * Creates a new instance of the gameStage to be played on.
  ******************************************************************/
  public void createNewGame(Stage gameStage) {
    this.gameStage = gameStage;
    this.gameStage.close(); // remove the old gameStage
    mainStage.show();

  }
}
