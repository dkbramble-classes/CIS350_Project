package model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;


/******************************************************************
* Creates an Exit Button down-loaded from Kenney.nl
******************************************************************/

public class ExitButton extends Button {

  //CSS to use .png files as the icon path
  private static final String FONT_PATH = "kenvector_future.ttf";
  //private final String EXIT_PRESSED_STYLE = "-fx-background-color:"
  //    + " transparent; "
  //    + "-fx-background-image: url('/model/resources/buttonX.png');";
  
  private static final String EXIT_FREE_STYLE = "-fx-background-color: "
      + "transparent;"
      + " -fx-background-image: url('/model/resources/buttonX.png');";


  /******************************************************************
  * constructor for ExitButton class, sets the height and width of 
  * icon along with the style.
  ******************************************************************/

  public ExitButton(String text) {
    setText(text);
    setButtonFont();
    setPrefWidth(100);
    setPrefHeight(100);
    setStyle(EXIT_FREE_STYLE);
    initializeButtonListener();
  }
  /******************************************************************
  * Sets the size of the button.
  ******************************************************************/
  
  private void setButtonFont() {
    try {
      setFont(Font.loadFont(new FileInputStream(FONT_PATH), 25));
    } catch (FileNotFoundException e) {
      setFont(Font.font("Verdana", 25));
    }
  }

  /******************************************************************
  * Needs more work, but the button should have a shadow around it 
  * when pressed, when released it goes back to the "freestyle".
  ******************************************************************/
  private void setExitPressedStyle() {
    setStyle(EXIT_FREE_STYLE);
    setPrefHeight(20);
    setLayoutY(getLayoutY() + 4);
  }
  
  private void setExitFreeStyle() {
    setStyle(EXIT_FREE_STYLE);
    setPrefHeight(20);
    setLayoutY(getLayoutY() - 4);
  }


  /******************************************************************
  * Will exit the program when the button is clicked.
  ******************************************************************/
  private void initializeButtonListener() {
    setOnMousePressed(new EventHandler<MouseEvent>() {

      @Override
    public void handle(MouseEvent event) {
        if (event.getButton().equals(MouseButton.PRIMARY)) {
          setExitPressedStyle();
          System.exit(1); //marked 'BAD PRACTICE' by find bugs, but its fine
          return;
        }

      }

    });
    setOnMouseReleased(new EventHandler<MouseEvent>() {

      @Override
  public void handle(MouseEvent event) {
          if (event.getButton().equals(MouseButton.PRIMARY)) {
            setExitFreeStyle();
          }

      }

    });

    /******************************************************************
    * Shadow will disappear when the button is not clicked on.
    ******************************************************************/

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
