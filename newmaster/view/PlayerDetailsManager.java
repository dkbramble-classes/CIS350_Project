//icons imported from https://www.iconfinder.com/icons/1288819/
//action_call_dial_four_fourth_number_phone_icon
//
//https://www.iconfinder.com/icons/2075802/arrow_back_redo_return_icon

package view;

import gamelogic.ConnectLogic;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

import model.ExitButton;
//import model.MusicButton;
import model.PlayButton;
import playertype.Player;

/******************************************************************
* Class that deals with the second screen of the program; the 
* player details including the names and colors of each person 
* playing Connect 4.
******************************************************************/

public class PlayerDetailsManager extends Player {

  private AnchorPane playerDetailsPane;
  private AnchorPane dialogPane;
  private Scene playerDetailsScene;
  private Stage playerDetailsStage;
  //private Stage namePopUp;
  private static final String FONT_PATH = "kenvector_future.ttf";
  private static final int SCREEN_WIDTH = 800;
  private static final int SCREEN_HEIGHT = 600;
  private Stage menuStage;
  public Player player1 = new Player();
  public Player player2 = new Player();
  public Player player3 = new Player();
  public Player player4 = new Player();
  //private int maxChar = 10;
  
  /******************************************************************
  *   initializes the pane, scene, and stage, creates the background 
  *   image, adds all the items, including buttons, labels, and icons.
  * @throws IOException if issues with file paths
  ******************************************************************/

  public PlayerDetailsManager() throws IOException {
    initializeStage();
    createBackground();
    createItems();
  }

  /******************************************************************
  * initializes the pane, scene, and stage, creates the background 
  * image.
  ******************************************************************/
  
  private void initializeStage() {
    playerDetailsPane = new AnchorPane();
    playerDetailsScene = new Scene(playerDetailsPane, 
        SCREEN_WIDTH, SCREEN_HEIGHT);
    playerDetailsStage = new Stage();
    playerDetailsStage.setScene(playerDetailsScene);

  }
  
  /******************************************************************
  *   will be called from ViewManager, hides the menu screen and 
  *   shows the player details screen.
  ******************************************************************/

  public void createDetailsScreen(Stage menuStage) {
    this.menuStage = menuStage;
    this.menuStage.hide();
    playerDetailsStage.show();

  }

  /******************************************************************
  *   Creates labels, buttons, and drop down menus for the different 
  *   color options. Still need to add text fields for the players 
  *   names and link that data to each player.
  ******************************************************************/
  
  public void createItems() throws
       IOException {
    Label title = new Label("PLAYER   DETAILS");
    Label names = new Label("NAME");
    title.setLayoutX(135);
    title.setLayoutY(80);
    names.setLayoutX(165);
    names.setLayoutY(170);
    Label colors = new Label("COLOR");
    colors.setLayoutX(500);
    colors.setLayoutY(170);
 
    //MusicButton musicButton = new MusicButton(null);
    //    musicButton.setLayoutX(640);
    //musicButton.setLayoutY(27);
    ExitButton exitButton = new ExitButton(null);
    exitButton.setLayoutX(700);
    exitButton.setLayoutY(0);
    
    Button returnToMenu = new Button(null);
    returnToMenu.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        playerDetailsStage.hide();
        menuStage.show();

      }
    });

    
    
    Button plus2 = new Button(null);
    plus2
         .setStyle("-fx-background-color: transparent; "
             + "-fx-background-image: "
        + "url('/model/resources/plus-sign.png'); -fx-min-height: 10px;");
    plus2.setPrefHeight(24);
    plus2.setPrefWidth(24);
    plus2.setLayoutX(315);
    plus2.setLayoutY(300);

    Button plus3 = new Button(null);
    plus3
        .setStyle("-fx-background-color: transparent; "
            + "-fx-background-image: "
        + "url('/model/resources/plus-sign.png'); -fx-min-height: 10px;");
    plus3.setPrefHeight(24);
    plus3.setPrefWidth(24);
    plus3.setLayoutX(315);
    plus3.setLayoutY(370);

    Button plus4 = new Button(null);
    plus4
        .setStyle("-fx-background-color: transparent;"
            + " -fx-background-image: "
        + "url('/model/resources/plus-sign.png'); -fx-min-height: 10px;");
    plus4.setPrefHeight(24);
    plus4.setPrefWidth(24);
    plus4.setLayoutX(315);
    plus4.setLayoutY(440);
    
    
    /******************************************************************
    *  Creates separate dialog boxes that captures computer player 
    *  information.
    ******************************************************************/
    
    
    //player 2 dialog box for potential CPU 
    
    plus2.setOnAction(e -> {
      dialogPane = new AnchorPane();
      final Stage dialog = new Stage();
      dialog.initModality(Modality.APPLICATION_MODAL);
      dialog.initOwner(playerDetailsStage);
      Scene dialogScene = new Scene(dialogPane, 500, 250);
      dialog.setScene(dialogScene);
      dialog.show();
      dialogPane.setStyle("-fx-background-color: #E9EE24;");

      //label for the title of the pop-up window, CPU Difficulty
      Label cpuDifficulty = new Label("CPU   Difficulty");
      cpuDifficulty.setLayoutX(140);
      try {
        cpuDifficulty.setFont(Font.loadFont(new 
            FileInputStream(FONT_PATH),20));
      } catch (FileNotFoundException e1) {
        e1.printStackTrace();
      }
      cpuDifficulty.setTextFill(Color.DARKSLATEGRAY);
      
      //buttons for the user to choose the CPU difficulty
      PlayButton easy = new PlayButton("EASY");
      easy.setLayoutX(10);
      easy.setLayoutY(60);
      
      PlayButton medium = new PlayButton("MEDIUM");
      medium.setLayoutX(10);
      medium.setLayoutY(130);
   
      
      //descriptions of the two difficulties
      Label easyDescription = new Label("CPU  isn't  the  best. "
          + " It  will "
          + "place  \na  chip  randomly  on  the  board.");
      easyDescription.setLayoutX(210);
      easyDescription.setLayoutY(65);
      try {
        easyDescription.setFont(Font.loadFont(new 
            FileInputStream(FONT_PATH),12));
      } catch (FileNotFoundException e1) {
        e1.printStackTrace();
      }
      easyDescription.setTextFill(Color.DARKSLATEGRAY);
      
      easy.setOnAction(r -> {
        player2.setDifficulty("e");
        easyDescription.setTextFill(Color.BLUE);
        try {
          easyDescription.setFont(Font.loadFont(new 
              FileInputStream(FONT_PATH),12.5));
        } catch (FileNotFoundException e1) {
          e1.printStackTrace();
        }
      });
      
      
      Label mediumDescription = new Label("CPU  has  decent  skill."
          + "  It  will\nplace  a  chip  near  its'  previous\nchip.");
      mediumDescription.setLayoutX(210);
      mediumDescription.setLayoutY(135);
      try {
        mediumDescription.setFont(Font.loadFont(new
            FileInputStream(FONT_PATH),12));
      } catch (FileNotFoundException e1) {
        e1.printStackTrace();
      }
      mediumDescription.setTextFill(Color.DARKSLATEGRAY);
      
      medium.setOnAction(r -> {
        player2.setDifficulty("m");
        mediumDescription.setTextFill(Color.BLUE);
        try {
          mediumDescription.setFont(Font.loadFont(new 
              FileInputStream(FONT_PATH),12.5));
        } catch (FileNotFoundException e1) {
          e1.printStackTrace();
        }
      });
      
      
      //confirm difficulty button
      Button confirm = new Button(null);
      confirm
          .setStyle("-fx-background-color: transparent; "
              + "-fx-background-image: "
          + "url('/model/resources/smallPlayButton.png');");
      confirm.setPrefHeight(48);
      confirm.setPrefWidth(48);
      confirm.setLayoutX(435);
      confirm.setLayoutY(185);
      
      
      confirm.setOnAction(r -> {
        
        player2.setCompStatus(true);
        
        dialog.close();
      });
    
      dialogPane.getChildren().addAll(cpuDifficulty, easy, medium,
          easyDescription, mediumDescription, confirm);
      
    });

    
    //player 3 dialog box for potential CPU 
    
    plus3.setOnAction(e -> {
      dialogPane = new AnchorPane();
      final Stage dialog = new Stage();
      dialog.initModality(Modality.APPLICATION_MODAL);
      dialog.initOwner(playerDetailsStage);
      Scene dialogScene = new Scene(dialogPane, 500, 250);
      dialog.setScene(dialogScene);
      dialog.show();
      dialogPane.setStyle("-fx-background-color: #E9EE24;");

      //label for the title of the pop-up window, CPU Difficulty
      Label cpuDifficulty = new Label("CPU   Difficulty");
      cpuDifficulty.setLayoutX(140);
      try {
        cpuDifficulty.setFont(Font.loadFont(new 
            FileInputStream(FONT_PATH),20));
      } catch (FileNotFoundException e1) {
        e1.printStackTrace();
      }
      cpuDifficulty.setTextFill(Color.DARKSLATEGRAY);
      
      //buttons for the user to choose the CPU difficulty
      PlayButton easy = new PlayButton("EASY");
      easy.setLayoutX(10);
      easy.setLayoutY(60);
      
      PlayButton medium = new PlayButton("MEDIUM");
      medium.setLayoutX(10);
      medium.setLayoutY(130);
   
      
      //descriptions of the two difficulties
      Label easyDescription = new Label("CPU  isn't  the  best. "
          + " It  will "
          + "place  \na  chip  randomly  on  the  board.");
      easyDescription.setLayoutX(210);
      easyDescription.setLayoutY(65);
      try {
        easyDescription.setFont(Font.loadFont(new 
            FileInputStream(FONT_PATH),12));
      } catch (FileNotFoundException e1) {
        e1.printStackTrace();
      }
      easyDescription.setTextFill(Color.DARKSLATEGRAY);
      
      easy.setOnAction(r -> {
        player3.setDifficulty("e");
        easyDescription.setTextFill(Color.BLUE);
        try {
          easyDescription.setFont(Font.loadFont(new 
              FileInputStream(FONT_PATH),12.5));
        } catch (FileNotFoundException e1) {
          e1.printStackTrace();
        }
      });
      
      
      Label mediumDescription = new Label("CPU  has  decent  skill."
          + "  It  will\nplace  a  chip  near  its'  previous\nchip.");
      mediumDescription.setLayoutX(210);
      mediumDescription.setLayoutY(135);
      try {
        mediumDescription.setFont(Font.loadFont(new 
            FileInputStream(FONT_PATH),12));
      } catch (FileNotFoundException e1) {
        e1.printStackTrace();
      }
      mediumDescription.setTextFill(Color.DARKSLATEGRAY);
      
      medium.setOnAction(r -> {
        player3.setDifficulty("m");
        mediumDescription.setTextFill(Color.BLUE);
        try {
          mediumDescription.setFont(Font.loadFont(new 
              FileInputStream(FONT_PATH),12.5));
        } catch (FileNotFoundException e1) {
          e1.printStackTrace();
        }
      });
      
      
      //confirm difficulty button
      Button confirm = new Button(null);
      confirm
          .setStyle("-fx-background-color: transparent; "
              + "-fx-background-image: "
          + "url('/model/resources/smallPlayButton.png');");
      confirm.setPrefHeight(48);
      confirm.setPrefWidth(48);
      confirm.setLayoutX(435);
      confirm.setLayoutY(185);
      
      
      confirm.setOnAction(r -> {
        
        player3.setCompStatus(true);
        
        dialog.close();
      });
    
      dialogPane.getChildren().addAll(cpuDifficulty, easy, medium,
          easyDescription, mediumDescription, confirm);
      
    });


    //player 4 dialog box for potential CPU 
    
    plus4.setOnAction(e -> {
      dialogPane = new AnchorPane();
      final Stage dialog = new Stage();
      dialog.initModality(Modality.APPLICATION_MODAL);
      dialog.initOwner(playerDetailsStage);
      Scene dialogScene = new Scene(dialogPane, 500, 250);
      dialog.setScene(dialogScene);
      dialog.show();
      dialogPane.setStyle("-fx-background-color: #E9EE24;");

      //label for the title of the pop-up window, CPU Difficulty
      Label cpuDifficulty = new Label("CPU   Difficulty");
      cpuDifficulty.setLayoutX(140);
      try {
        cpuDifficulty.setFont(Font.loadFont(new 
            FileInputStream(FONT_PATH),20));
      } catch (FileNotFoundException e1) {
        e1.printStackTrace();
      }
      cpuDifficulty.setTextFill(Color.DARKSLATEGRAY);
      
      //buttons for the user to choose the CPU difficulty
      PlayButton easy = new PlayButton("EASY");
      easy.setLayoutX(10);
      easy.setLayoutY(60);
      
      PlayButton medium = new PlayButton("MEDIUM");
      medium.setLayoutX(10);
      medium.setLayoutY(130);
   
      
      //descriptions of the two difficulties
      Label easyDescription = new Label("CPU  isn't  the  best. "
          + " It  will "
          + "place  \na  chip  randomly  on  the  board.");
      easyDescription.setLayoutX(210);
      easyDescription.setLayoutY(65);
      try {
        easyDescription.setFont(Font.loadFont(new
            FileInputStream(FONT_PATH),12));
      } catch (FileNotFoundException e1) {
        e1.printStackTrace();
      }
      easyDescription.setTextFill(Color.DARKSLATEGRAY);
      
      easy.setOnAction(r -> {
        player4.setDifficulty("e");
        easyDescription.setTextFill(Color.BLUE);
        try {
          easyDescription.setFont(Font.loadFont(new 
              FileInputStream(FONT_PATH),12.5));
        } catch (FileNotFoundException e1) {
          e1.printStackTrace();
        }
      });
      
      
      Label mediumDescription = new Label("CPU  has  decent  skill."
          + "  It  will\nplace  a  chip  near  its'  previous\nchip.");
      mediumDescription.setLayoutX(210);
      mediumDescription.setLayoutY(135);
      try {
        mediumDescription.setFont(Font.loadFont(new 
            FileInputStream(FONT_PATH),12));
      } catch (FileNotFoundException e1) {
        e1.printStackTrace();
      }
      mediumDescription.setTextFill(Color.DARKSLATEGRAY);
      
      medium.setOnAction(r -> {
        player4.setDifficulty("m");
        mediumDescription.setTextFill(Color.BLUE);
        try {
          mediumDescription.setFont(Font.loadFont(new 
              FileInputStream(FONT_PATH),12.5));
        } catch (FileNotFoundException e1) {
          e1.printStackTrace();
        }
      });
      
      
      //confirm difficulty button
      Button confirm = new Button(null);
      confirm
          .setStyle("-fx-background-color: transparent; "
              + "-fx-background-image: "
          + "url('/model/resources/smallPlayButton.png');");
      confirm.setPrefHeight(48);
      confirm.setPrefWidth(48);
      confirm.setLayoutX(435);
      confirm.setLayoutY(185);
      
      
      confirm.setOnAction(r -> {
        
        player4.setCompStatus(true);
        
        dialog.close();
      });
    
      dialogPane.getChildren().addAll(cpuDifficulty, easy, medium,
          easyDescription, mediumDescription, confirm);
      
    });

    
    
    //Text fields and choice boxes to capture each player's
    //unique name and color
    
    TextField player1Name = new TextField();
    player1Name.setLayoutX(140);
    player1Name.setLayoutY(230);
    
    TextField player2Name = new TextField();
    player2Name.setLayoutX(140);
    player2Name.setLayoutY(300);
    
    TextField player3Name = new TextField();
    player3Name.setLayoutX(140);
    player3Name.setLayoutY(370);
    
    TextField player4Name = new TextField();
    player4Name.setLayoutX(140);
    player4Name.setLayoutY(440);
    
    ChoiceBox<String> color1 = new ChoiceBox<>();
    color1.getItems().addAll("Red", "Blue", "Green", "Yellow");
    color1.setPrefWidth(80);
    color1.setLayoutX(520);
    color1.setLayoutY(230);
    ChoiceBox<String> color2 = new ChoiceBox<>();
    color2.getItems().addAll("Red", "Blue", "Green", "Yellow");
    color2.setPrefWidth(80);
    color2.setLayoutX(520);
    color2.setLayoutY(300);
    ChoiceBox<String> color3 = new ChoiceBox<>();
    color3.getItems().addAll("Red", "Blue", "Green", "Yellow");
    color3.setPrefWidth(80);
    color3.setLayoutX(520);
    color3.setLayoutY(370);
    ChoiceBox<String> color4 = new ChoiceBox<>();
    color4.getItems().addAll("Red", "Blue", "Green", "Yellow");
    color4.setPrefWidth(80);
    color4.setLayoutX(520);
    color4.setLayoutY(440);
    
    
    //number icons and play game and return to menu icons
    returnToMenu
        .setStyle("-fx-background-color: transparent;"
            + " -fx-background-image: "
        + "url('/model/resources/backArrow.png');");
    returnToMenu.setPrefHeight(45);
    returnToMenu.setPrefWidth(64);
    returnToMenu.setLayoutX(20);
    returnToMenu.setLayoutY(20);
    Button playGame = new Button(null);

    playGame
        .setStyle("-fx-background-color: transparent; "
            + "-fx-background-image:"
        + " url('/model/resources/playButton.png');");
    playGame.setPrefHeight(120);
    playGame.setPrefWidth(120);
    playGame.setLayoutX(650);
    playGame.setLayoutY(470);

    Button playerOneIcon = new Button(null);
    playerOneIcon
        .setStyle("-fx-background-color: transparent; "
            + "-fx-background-image: "
        + "url('/model/resources/numberone.png');");
    playerOneIcon.setPrefHeight(48);
    playerOneIcon.setPrefWidth(48);
    playerOneIcon.setLayoutX(50);
    playerOneIcon.setLayoutY(220);

    Button playerTwoIcon = new Button(null);
    playerTwoIcon
        .setStyle("-fx-background-color: transparent; "
            + "-fx-background-image: "
        + "url('/model/resources/numbertwo.png');");
    playerTwoIcon.setPrefHeight(48);
    playerTwoIcon.setPrefWidth(48);
    playerTwoIcon.setLayoutX(50);
    playerTwoIcon.setLayoutY(290);

    Button playerThreeIcon = new Button(null);
    playerThreeIcon
        .setStyle("-fx-background-color: transparent; "
            + "-fx-background-image: "
        + "url('/model/resources/numberthree.png');");
    playerThreeIcon.setPrefHeight(48);
    playerThreeIcon.setPrefWidth(48);
    playerThreeIcon.setLayoutX(50);
    playerThreeIcon.setLayoutY(360);

    Button playerFourIcon = new Button(null);
    playerFourIcon
        .setStyle("-fx-background-color: transparent; "
            + "-fx-background-image: "
        + "url('/model/resources/numberfour.png');");
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

    playerDetailsPane.getChildren().addAll(title, names, colors, 
        playerOneIcon, playerTwoIcon, playerThreeIcon,
        playerFourIcon, color1, color2, color3, color4, 
        returnToMenu, playGame, player1Name, player2Name, 
        player3Name, player4Name, exitButton,
        plus2, plus3, plus4);
    
    
    //on "play button" click, every player's information is set 
    playGame.setOnAction(e -> {
      
      player1.setPosition(1);
      player1.setName(player1Name.getText());
      player1.setColor(color1.getValue());
      
      player2.setPosition(2);
      player2.setName(player2Name.getText());
      player2.setColor(color2.getValue());
      
      player3.setPosition(3);
      player3.setName(player3Name.getText());
      player3.setColor(color3.getValue());
      
      player4.setPosition(4);
      player4.setName(player4Name.getText());
      player4.setColor(color4.getValue());
      
    
      ConnectLogic logic = new ConnectLogic();

      //if there are two of the same colors, a pop-up will
      //notify the user(s) to make sure every player has
      //a unique color
      int colorCounter = 0;
      
      if (color1.getValue() != null &&
          (color1.getValue() == color2.getValue() || 
          color1.getValue() == color3.getValue() || 
          color1.getValue() == color4.getValue())) {
        colorCounter++;
      }
      if (color2.getValue() != null &&
          (color2.getValue() == color1.getValue() || 
          color2.getValue() == color3.getValue() || 
          color2.getValue() == color4.getValue())) {
        colorCounter++;
      }
      if (color3.getValue() != null &&
          (color3.getValue() == color1.getValue() || 
          color3.getValue() == color2.getValue() || 
          color3.getValue() == color4.getValue())) {
        colorCounter++;
      }
      if (color4.getValue() != null &&
          (color4.getValue() == color1.getValue() || 
          color4.getValue() == color2.getValue() || 
          color4.getValue() == color3.getValue())) {
        colorCounter++;
      }
      
      
       
      //checks for empty name fields, if there are more than 
      //two empty fields, game will not start, and will pop-up
      //a dialog, letting the user know to have at least 2 users
      
      int nameCounter = 0;
      if (player1Name.getText().isEmpty()) {
        nameCounter++;
      }
      if (player2Name.getText().isEmpty()) {
        nameCounter++;
      }
      if (player3Name.getText().isEmpty()) {
        nameCounter++;
      }
      if (player4Name.getText().isEmpty()) {
        nameCounter++;
      }
      
      if (nameCounter > 2) {
        dialogPane = new AnchorPane();
        final Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(playerDetailsStage);
        Scene dialogScene = new Scene(dialogPane, 350, 70);
        dialog.setScene(dialogScene);
        dialog.show();
        dialogPane.setStyle("-fx-background-color: #E9EE24;");
        
        Label alert = new Label("      Not  enough  players,\n"
            + "Need  at  least  two  to  play");
        alert.setLayoutX(30);
        alert.setLayoutY(25);
        try {
          alert.setFont(Font.loadFont(new 
              FileInputStream(FONT_PATH),15));
        } catch (FileNotFoundException e1) {
          e1.printStackTrace();
        }
        alert.setTextFill(Color.DARKSLATEGRAY);
        dialogPane.getChildren().addAll(alert);
      } 
      else if(colorCounter > 0) {
        dialogPane = new AnchorPane();
        final Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(playerDetailsStage);
        Scene dialogScene = new Scene(dialogPane, 280, 90);
        dialog.setScene(dialogScene);
        dialog.show();
        dialogPane.setStyle("-fx-background-color: #E9EE24;");
        
        
        Label alert = new Label(" Two  or  more  players  "
            + "\nhave  the  same  color!\n  Can't  play  yet!");
        alert.setLayoutX(30);
        alert.setLayoutY(25);
        try {
          alert.setFont(Font.loadFont(new 
              FileInputStream(FONT_PATH),13));
        } catch (FileNotFoundException e1) {
          e1.printStackTrace();
        }
        alert.setTextFill(Color.DARKSLATEGRAY);
        dialogPane.getChildren().addAll(alert);
      }
      
      
      else {     
        //Validation to make sure each player that wants to play,
        //has a color selected, or else they aren't added to the
        //game
        int playerCounter = 0;
        if (color1.getValue() != null 
            && player1Name.getText().isEmpty() == false) {
          logic.addPlayer(player1);
          playerCounter++;
        }
      
        if (color2.getValue() != null 
            && player2Name.getText().isEmpty() == false) {
          logic.addPlayer(player2);
          playerCounter++;
        }
        
        if (color3.getValue() != null 
            && player3Name.getText().isEmpty() == false) {
          logic.addPlayer(player3);
          playerCounter++;
        }
         
        if (color4.getValue() != null 
            && player4Name.getText().isEmpty() == false) {
          logic.addPlayer(player4);
          playerCounter++;
        } 
        
      if (playerCounter > 1) {
        logic.startGame();
      
              GameManager gameManager = new GameManager(logic.getX(), 
            logic.getY());
        gameManager.createNewGame(playerDetailsStage);
        gameManager.setLogic(logic);
      }
      
      }
      
    });
    
 

  }
 

  /******************************************************************
  * Needs more work, but the button should have a shadow around it 
  * when pressed, when released it goes back to the "freestyle".
  ******************************************************************/
  //creates the background to the player details screen. 
  //same as main menu background
  private void createBackground() {

    Image backgroundImage = new Image("model/resources/"
        + "Slider-CL01-Background-256x256.png",
        256, 256, false, true);
    BackgroundImage background = new BackgroundImage(backgroundImage,
        BackgroundRepeat.REPEAT,
        BackgroundRepeat.REPEAT,
        BackgroundPosition.DEFAULT, null);
    playerDetailsPane.setBackground(new Background(background));

  }
}
