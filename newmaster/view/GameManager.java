package view;

import gamelogic.ConnectLogic;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.image.Image;
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


public class GameManager extends Application {

  private static final int TILE_SIZE = 80; // size of each chip
  private int gridColumns; // how many columns on the board
  private int gridRows; // how many rows on the board 
  private Pane gamePane = new Pane(); //the actual screen
  private Scene gameScene; //the instance of the board
  private Stage gameStage; //the stage that the scene will be on
  private boolean gameOver = false; // if the game is ended, the buttons can't be used
  private Shape gridShape; // allows the placement of chips
  private Stage playerDetailsStage; //the previous screen
  private Disc[][] grid;  //all of the Disc objects
  private Pane discRoot =  new Pane(); // the disc placement aspect of the GUI
  private ConnectLogic logic = new ConnectLogic();  //the actual game logic
  private String winner = ""; //will be populated with the winner's name
  
  /******************************************************************
  * Creates the game panel for when you start a new game from
  * the Players Detail Manager.
  ******************************************************************/
  public GameManager(int columns, int rows) {

    //set the board size
    gridColumns = columns;
    gridRows = rows;
    //Create the game board
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
  ******************************************************************/
  private Parent createContent() {

    createBackground();
    return gamePane;

  }
  
  /******************************************************************
  * Goes to the next page in the game menu.
  ******************************************************************/ 
  public void createNewGame(Stage playerDetailsStage) {
    this.playerDetailsStage = playerDetailsStage;
    this.playerDetailsStage.close(); //get rid of the previous screen
    gameStage.show();
    
  }
  
  /******************************************************************
   * Setter that takes in logic from ConnectLogic.
   ******************************************************************/
  public void setLogic(ConnectLogic logic) {
    this.logic = logic; //Receives this from PlayerDetailsManager
  }
  
  /******************************************************************
  * Creates the game board grid.
  ******************************************************************/
  private Shape makeGrid() {
    Shape shape = new Rectangle((gridColumns + 1) * TILE_SIZE, 
        (gridRows + 1) * TILE_SIZE);
    for (int y = 0; y < gridRows; y++) { //create the circles for each section of the grid
      for (int x = 0; x < gridColumns; x++) {
        Circle circle = new Circle(TILE_SIZE / 2);
        circle.setCenterX(TILE_SIZE / 2);
        circle.setCenterY(TILE_SIZE / 2);
        circle.setTranslateX(x * (TILE_SIZE + 5) + TILE_SIZE / 3);
        circle.setTranslateY(y * (TILE_SIZE + 5) + TILE_SIZE / 3);
        
        shape = Shape.subtract(shape, circle);
      }
    } 
    
    //basic lighting for the board
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
  * @throws Throwable in order to call functions after a mouse click
  */
  private List<Rectangle> makeColumns() {
    List<Rectangle> list = new ArrayList<>();
     
    for (int x = 0; x < gridColumns; x++) {
      Rectangle rect = 
          new Rectangle(TILE_SIZE, (gridRows + 1) * TILE_SIZE); //which column will be chosen
      //animation for when the rectangle is hovered over / clicked 
      rect.setTranslateX(x * (TILE_SIZE + 5) + TILE_SIZE / 3);
      rect.setFill(Color.TRANSPARENT);
      rect.setOnMouseEntered(e -> rect.setFill(Color.rgb(200, 200, 50, 0.3)));
      rect.setOnMouseExited(e -> rect.setFill(Color.TRANSPARENT));
         
      final int column = x;
      rect.setOnMouseClicked(e -> {
        try {
          placeDisc(new Disc(findColor()), column, false); //create a new disc / place it at column
        } catch (Throwable e1) {
          e1.printStackTrace();
        }
      });
        
      list.add(rect); // add rectangle to list 
    }
    return list;
  }
  
  /******************************************************************
  * Method that implements game logic to determine winner.
  * Also creates the animation of the chip falling
  * @throws Throwable if checkComputer cannot be ran after the animation.
  */
  private void placeDisc(Disc disc, int column, boolean computer) 
      throws Throwable {
    if (!gameOver) { // if the game is not over, accept mouse clicks
      //find all of the discs on the grid, create the animation where there are no discs
      int row = gridRows - 1; //where to place disc
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
          + (TILE_SIZE / 3)); //set animation

      TranslateTransition animation = new TranslateTransition(Duration.seconds(0.5), disc);
      animation.setToY(row * (TILE_SIZE + 5) + (TILE_SIZE / 3));
      if (!computer) { //if not a computer
        System.out.println(column); //for logging
        int result = 0;
        winner = logic.getCurrentPlayer().getName(); //set winner name
        result = logic.nextTurn(column); //place the chip in the logic       
        animation.setOnFinished(e -> { //once the animation is done
          try {
            checkComputer(column); //check if the next player is a computer
          } catch (Throwable e1) {
            e1.printStackTrace();
          }
        }); 
        animation.play(); //play the animation
        if (result == -20) { //a tie
          showMessage("No more chips can be placed, it's a tie!");
          gameOver = true; //stop accepting button presses
          
        }
        
        if (logic.checkWin() != 0) { //if won
          showMessage(winner + " Wins!");
          gameOver = true; //stop accepting button presses
          return;
        }
      } else { //if its a computer

        animation.setOnFinished(e -> { 
          try {
            checkComputer(column); //check if the next player is a computer
          } catch (Throwable e1) {
            e1.printStackTrace();  
          } 
        });
        animation.play(); //play the animation

      }
    }
  }
  /******************************************************************
  * This method displays a pop message with the given information.
  * This is used for the win / tie conditions.
  * @throws IOException if there are issues with file paths.
  */
  
  private void showMessage(String message) 
      throws IOException {

    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Game Finished");
    alert.setHeaderText(message);
    alert.setResizable(false);
    alert.setContentText("The Game is over, returning to menu");
    alert.setOnCloseRequest(evt -> { 
      try {
        restartApp();
      } catch (IOException e) {
        e.printStackTrace();
      }
    });
    alert.show();
  }

  /******************************************************************
  * Resets the View Manager so the game can be played again.
  * @throws IOException if ViewManager has issues with file paths.
  */
  public void restartApp() throws IOException{
    ViewManager viewManager = new ViewManager(); //create a new menu screen
    viewManager.createNewGame(gameStage);
    gameStage.close(); //close the old game board stage
  
  }
  
  /******************************************************************
  * Returns optional discs if they exist at the given grid location.
  */
  private Optional<Disc> getDisc(int column, int row) {
    if (column < 0 || column >= gridColumns 
        || row < 0 || row >= gridRows) {
      return Optional.empty();
    }
    return Optional.ofNullable(grid[column][row]);
  }
  
  /******************************************************************
  * Class creates a disc, which can be used the game board.
  ******************************************************************/
  private static class Disc extends Circle {
    /******************************************************************
    * Constructor creates a disk with the chosen color.
    * @param  playcolor - the color that the disk will be
    */
    public Disc(Color playcolor) {
      super(TILE_SIZE / 2, playcolor); //calls super to create a circle with the given color

      setCenterX(TILE_SIZE / 2); 
      setCenterY(TILE_SIZE / 2);
    }
  }
  
  /******************************************************************
  * Uses an image to create the background for the game board.
  ******************************************************************/
  private void createBackground() {
  
    Image backgroundImage = new Image("model/resources/"
        + "Slider-CL01-Background-256x256.png", 256,256,false,true);
    BackgroundImage background = new BackgroundImage(backgroundImage, 
        BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, 
        BackgroundPosition.DEFAULT, null);
    gamePane.setBackground(new Background(background));
    
  }

  /******************************************************************
  * Method that returns the current player's chip color so that 
  * the chip can be created.
  ******************************************************************/
  private Color findColor() {
    Color color = Color.RED; //by default, the color is red
    String playcolor = logic.getCurrentPlayer().getColor();
    switch (playcolor) { //
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
  
  /******************************************************************
  * This method runs Computer Player GUI logic.
  * @throws Throwable in case placeDisc or showMessage have issues
  */
  
  private void checkComputer(int column) throws Throwable {
    boolean computer = logic.getCurrentPlayer().getCompStatus();
    if (computer & !gameOver) { //if the current player is a computer
      int result;
      Color color = findColor(); //get computer chip color
      winner = logic.getCurrentPlayer().getName(); //get computer name in case it wins
      result = logic.nextTurn(column); //find the computer's chip placement, move to next turn
      placeDisc(new Disc(color), result, computer); //place the chip
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

  /******************************************************************
  * This method creates the scene needed to play the game.
  ******************************************************************/
  @Override
  public void start(Stage stage) throws Exception { 
    stage.setScene(new Scene(createContent()));
    stage.show();
  }

}