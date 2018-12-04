package model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
//import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;

/******************************************************************
* Creates a Play Button down-loaded from Kenney.nl
******************************************************************/
public class PlayButton extends Button {

  //CSS to use .png files as the icon path
  private static final String FONT_PATH = "kenvector_future.ttf";
  //  private final String PLAY_PRESSED_STYLE = "-fx-background-color:"
  //  + " transparent; -fx-background-image: url('/model/resources/"
  //  + "blue_button04.png);";
  private static final String PLAY_FREE_STYLE = "-fx-background-color: "
      + "transparent; -fx-background-image: url('/model/resources/"
      + "blue_button04.png');";

  /******************************************************************
  * constructor for ExitButton class, sets the height and width of 
  * icon along with the style.
  ******************************************************************/
  public PlayButton(String text) {
    setText(text);
    setButtonFont();
    setPrefWidth(191);
    setPrefHeight(50);
    setStyle(PLAY_FREE_STYLE);
    initializeButtonListener();
  }

  /******************************************************************
  * Sets the size of the button.
  ******************************************************************/
  private void setButtonFont() {
    try {
      setFont(Font.loadFont(new FileInputStream(FONT_PATH), 20));
    } catch (FileNotFoundException e) {
      setFont(Font.font("Verdana", 20));
    }
  }


  /******************************************************************
  * Needs more work, but the button should have a shadow around it 
  * when pressed, when released it goes back to the "freestyle".
  ******************************************************************/

  private void initializeButtonListener() {

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