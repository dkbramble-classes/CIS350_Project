package View;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
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


public class GameManager{

	private static final int TILE_SIZE = 80;
	private static final int COLUMNS = 7;
	private static final int ROWS = 6;
	private AnchorPane gamePane;
	private Scene gameScene;
	private Stage gameStage;
	private Stage playerDetailsStage;

	/******************************************************************
 	* Creates the game panel.
	******************************************************************/
	public GameManager() {
		gamePane = new AnchorPane();
		gameScene = new Scene(gamePane, 800, 600);
		gameStage = new Stage();
		gameStage.setScene(gameScene);
		Shape gridShape = makeGrid();
		gridShape.setLayoutX(100);
		gridShape.setLayoutX(50);
		gamePane.getChildren().add(gridShape);
		gamePane.getChildren().addAll(makeColumns());
		createBackground();
	}
	
	/******************************************************************
 	* Goes to the next page in the game menu.
	******************************************************************/ 
	public void createNewGame(Stage playerDetailsStage) {
		this.playerDetailsStage = playerDetailsStage;
		this.playerDetailsStage.hide();
		gameStage.show();
		
	}
	
	/******************************************************************
 	* Creates the game board grid.
	******************************************************************/
	private Shape makeGrid() {
		Shape shape = new Rectangle((COLUMNS + 1) * TILE_SIZE, (ROWS + 1) * TILE_SIZE);
		for(int y = 0; y < ROWS; y++) {
			for(int x = 0; x < COLUMNS; x++) {
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
	
	private List<Rectangle> makeColumns(){
		List<Rectangle> list = new  ArrayList<>();

		for(int x = 0; x < COLUMNS; x++) {
			Rectangle rect = new Rectangle(TILE_SIZE, (ROWS + 1) * TILE_SIZE);
			rect.setTranslateX(x*(TILE_SIZE + 5) + TILE_SIZE / 3);
			rect.setFill(Color.TRANSPARENT);

            rect.setOnMouseEntered(e -> rect.setFill(Color.rgb(200,200,100,0.3)));
            rect.setOnMouseExited(e -> rect.setFill(Color.TRANSPARENT));

            list.add(rect);

        }
        return list;
    }
	
private void createBackground() {
	
		
		Image backgroundImage = new Image("model/resources/Slider-CL01-Background-256x256.png", 256,256,false,true);
		BackgroundImage background = new BackgroundImage(backgroundImage, 
				BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, 
				BackgroundPosition.DEFAULT, null);
		gamePane.setBackground(new Background(background));
		
	}


}