package model;

import connectsystem.MusicPlayer;
import connectsystem.SystemOptions;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;



/******************************************************************
* Creates a Music Button down-loaded from Kenney.nl
******************************************************************/

public class MusicButton extends Button {

  //CSS to use .png files as the icon path
  private static final String FONT_PATH = "kenvector_future.ttf";

  private static final String audioOn = "-fx-background-color: "
      + "transparent; -fx-background-image: url('/model/resources/"
      + "audioOn.png');";

  private static final String audioOff = "-fx-background-color: "
      + "transparent; -fx-background-image: url('/model/resources/"
      + "audioOff.png');";
  MusicPlayer audioPlayer = new MusicPlayer();
  /******************************************************************
  * constructor for MusicButton class, sets the height and width of 
  * icon along with the style.
   * @throws LineUnavailableException if issues with playing music
   * @throws IOException if file not found
   * @throws UnsupportedAudioFileException 
  ******************************************************************/
  
  public MusicButton(String text) 
      throws UnsupportedAudioFileException, IOException, LineUnavailableException {
    setText(text);
    setButtonFont();
    setPrefWidth(50);
    setPrefHeight(30);
    setStyle(audioOn);
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
  *  currently not working... will just exit the program when clicked 
  *  on, but will change to a music-off icon when toggled.
  ******************************************************************/
   
  private void initializeButtonListener() {
    setOnMousePressed(new EventHandler<MouseEvent>() {

      @Override
      public void handle(MouseEvent event) {
        if (event.getButton().equals(MouseButton.PRIMARY)) {

          //setExitPressedStyle();
          if  (getStyle() == audioOn) {
            setStyle(audioOff);
          } else if (getStyle() == audioOff) {
            setStyle(audioOn);
          }
          //SystemOptions system = new SystemOptions();
          SystemOptions.musicControl(audioPlayer); 
        }

      }

      });


  }

}
