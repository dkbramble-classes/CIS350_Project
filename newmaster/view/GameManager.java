package view;


//import java.awt.EventQueue;
import gamelogic.ConnectLogic;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
//import javax.swing.JOptionPane;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.image.Image;
//import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

import javafx.stage.Stage;

import javafx.util.Duration;
//import playertype.Player;



public class GameManager extends Application {

  private static final int TILE_SIZE = 80;
  private int gridColumns;
  private int gridRows;
  private Pane gamePane = new Pane();
  private Scene gameScene;
  private Stage gameStage;
  private boolean compDone = true;
  private boolean gameOver = false;
  private Shape gridShape;
  private Stage playerDetailsStage;
  private Disc[][] grid; 
  private Pane discRoot =  new Pane(); 
  private ConnectLogic logic = new ConnectLogic(); 
  private String winner = "";
  
  /******************************************************************
  * Creates the game panel for when you start a new game from
  * the Players Detail Manager.
  ******************************************************************/
  public GameManager(int columns, int rows) {

    //logic.addPlayer(play1);
    //logic.addPlayer(play2);
    //logic.addPlayer(play3);
    //logic.addPlayer(play3);
    gridColumns = columns;
    gridRows = rows;
    grid = new Disc[columns][rows];
    Pane gamePane = new Pane();
    gameScene = new Scene(gamePane);
    gameStage = new Stage();
    gameStage.setScene(gameScene);
    gridShape = makeGrid();
    gamePane.getChildren().add(discRoot); 
    gamePane.getChildren().add(gridShape);
    gamePane.getChildren().addAll(makeColumns());
    gridShape.setLayoutX(100);
    gridShape.setLayoutX(0);
    
  }
  
  /******************************************************************
  * This method returns gamePane, which acts as the rectangles
  * that allow you to pick what column to place your chip. 
  * Without this, the option to place another 
  ******************************************************************/
  private Parent createContent() {

    createBackground();
    return gamePane;

  }
  
  //System.out.print(logic.getCurrentPlayer().getName()));
  
  //  Player play2 = new Player("Red", 2, "Mr. Frodo");
  //  Player play3 = new Player("Green", 3, "One-Eyed Willy");
  //  Player play1 = new Player("Blue", 1, "hackerman");
  //  Player play3 = new Player("Yellow", 3, "DESTROYEROFWORLDS");

  
  /******************************************************************
  * Goes to the next page in the game menu.
  ******************************************************************/ 
  public void createNewGame(Stage playerDetailsStage) {
    this.playerDetailsStage = playerDetailsStage;
    this.playerDetailsStage.hide();
    gameStage.show();
    
  }
  
  /******************************************************************
   * Setter that takes in logic from ConnectLogic.
   ******************************************************************/
  public void setLogic(ConnectLogic logic) {
    this.logic = logic;
  }
  
  /******************************************************************
  * Creates the game board grid.
  ******************************************************************/
  private Shape makeGrid() {
    Shape shape = new Rectangle((gridColumns + 1) * TILE_SIZE, 
        (gridRows + 1) * TILE_SIZE);
    for (int y = 0; y < gridRows; y++) {
      for (int x = 0; x < gridColumns; x++) {
        Circle circle = new Circle(TILE_SIZE / 2);
        circle.setCenterX(TILE_SIZE / 2);
        circle.setCenterY(TILE_SIZE / 2);
        circle.setTranslateX(x * (TILE_SIZE + 5) + TILE_SIZE / 3);
        circle.setTranslateY(y * (TILE_SIZE + 5) + TILE_SIZE / 3);
        
        shape = Shape.subtract(shape, circle);
      }
    } 
    
    Light.Distant light = new Light.Distant();
    light.setAzimuth(45.0);
    light.setElevation(30.0);

    Lighting lighting = new Lighting();
    lighting.setLight(light);
    lighting.setSurfaceScale(5.0);

    shape.setFill(Color.BLUE);
    shape.setEffect(lighting);
    return shape;


  }
  
  /******************************************************************
  * Transparent rectangle that indicates which column you are 
  * choosing to drop a chip into.
  ******************************************************************/
  private List<Rectangle> makeColumns() {
    List<Rectangle> list = new ArrayList<>();
     
    for (int x = 0; x < gridColumns; x++) {
      Rectangle rect = new Rectangle(TILE_SIZE, (gridRows + 1) * TILE_SIZE);
      rect.setTranslateX(x * (TILE_SIZE + 5) + TILE_SIZE / 3);
      rect.setFill(Color.TRANSPARENT);
      rect.setOnMouseEntered(e -> rect.setFill(Color.rgb(200, 200, 50, 0.3)));
      rect.setOnMouseExited(e -> rect.setFill(Color.TRANSPARENT));
         
      final int column = x;
      rect.setOnMouseClicked(e -> {
        try {
          placeDisc(new Disc(findColor()), column, false);
        } catch (Throwable e1) {
          e1.printStackTrace();
        }
      });
        
      list.add(rect);
    }
    return list;
  }
  
  /******************************************************************
  * Method that implements game logic to determine winner.
   * @throws Throwable 
   * @throws Exception 
   * @throws UnsupportedAudioFileException 
  ******************************************************************/
  private void placeDisc(Disc disc, int column, boolean computer) throws UnsupportedAudioFileException, Exception, Throwable {
    if(!gameOver){
     compDone = false;
     int row = gridRows - 1;
      do {
        if (!getDisc(column, row).isPresent()) {
          break;
        }

        row--;
      } while (row >= 0);
      if (row < 0) {
        return;
      }
      grid[column][row] = disc;
      discRoot.getChildren().add(disc);
      disc.setTranslateX(column * (TILE_SIZE + 5) 
          + (TILE_SIZE / 3));

      TranslateTransition animation = new TranslateTransition(Duration.seconds(0.5), disc);
      animation.setToY(row * (TILE_SIZE + 5) + (TILE_SIZE / 3));
      if (!computer) {
        System.out.println(column);
        int result = 0;
        result = logic.nextTurn(column);
        animation.setOnFinished(e -> {
          //compDone = true;
          try {
            checkComputer(column);
          } catch (Throwable e1) {
            e1.printStackTrace();
          }
        });
        animation.play();
        if (result == -20) { //a tie
          showMessage("No more chips can be placed, it's a tie!");
          gameOver = true;
          
        }
        
        if (logic.checkWin() != 0) { //if won
          showMessage(winner + " Wins!");
          gameOver = true;
          return;
        }
        winner = logic.getCurrentPlayer().getName();
      } else {

          animation.setOnFinished(e -> {try {
            checkComputer(column);
          } catch (Throwable e1) {
            e1.printStackTrace();
          }});
          animation.play();

      }
    }
  }
  
  private void showMessage(String message) throws UnsupportedAudioFileException, Exception, Throwable {

  Alert alert = new Alert(Alert.AlertType.INFORMATION);
  alert.setTitle("Game Finished");
  alert.setHeaderText(message);
  alert.setResizable(false);
  alert.setContentText("The Game is over, returning to menu");

//  Optional<ButtonType> result = alert.showAndWait();
//  ButtonType button = result.orElse(ButtonType.CANCEL);
//
//  if (button == ButtonType.OK) {
//      restartApp();
//  } else {
//      alert.close();
//  }
  
  alert.setOnHidden(evt -> {try {
    restartApp();
  } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
    e.printStackTrace();
  }});
  //alert.setOnCloseRequest(value);
  alert.show();
}


public void restartApp() throws
UnsupportedAudioFileException, IOException,
LineUnavailableException {
  ViewManager viewManager = new ViewManager();
  viewManager.createNewGame(gameStage);

}
  
  private Optional<Disc> getDisc(int column, int row) {
    if (column < 0 || column >= gridColumns 
        || row < 0 || row >= gridRows) {
      return Optional.empty();
    }
    return Optional.ofNullable(grid[column][row]);
  }
  
  /******************************************************************
  * Reference Logic: Method creates two disks, instead of viewing
  * them as players. 
  ******************************************************************/
  private static class Disc extends Circle {
    public Disc(Color playcolor) {
      super(TILE_SIZE / 2, playcolor);

      setCenterX(TILE_SIZE / 2);
      setCenterY(TILE_SIZE / 2);
    }
  }
  
  private void createBackground() {
  
    Image backgroundImage = new Image("model/resources/"
        + "Slider-CL01-Background-256x256.png", 256,256,false,true);
    BackgroundImage background = new BackgroundImage(backgroundImage, 
        BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, 
        BackgroundPosition.DEFAULT, null);
    gamePane.setBackground(new Background(background));
    
  }

  private Color findColor() {
    Color color = Color.RED;
    String playcolor = logic.getCurrentPlayer().getColor();
    switch (playcolor) {
      case "Red":
        color =  Color.RED;
        break;
      case "Blue":
        color = Color.BLUE;
        break;
      case "Green":
        color = Color.GREEN;
        break;
      case "Yellow":
        color = Color.YELLOW;
        break;
      default:
        break;
    }
    return color;
  }
  
  private void checkComputer(int column) throws UnsupportedAudioFileException, Exception, Throwable {
    boolean computer = logic.getCurrentPlayer().getCompStatus();
    if (computer) {
      int result;
      Color color = findColor();
      winner = logic.getCurrentPlayer().getName();
      result = logic.nextTurn(column);
      placeDisc(new Disc(color), result, computer);
      if (result == -20 && !gameOver) { //a tie
            showMessage("No more chips can be placed, it's a tie!");
            gameOver = true;
      }
          
      if (logic.checkWin() != 0 && !gameOver) { //if won
          showMessage(winner + " Wins!");  
          gameOver = true;
      }
    }
  }

  @Override
  public void start(Stage stage) throws Exception { 
    stage.setScene(new Scene(createContent()));
    stage.show();
  }

}