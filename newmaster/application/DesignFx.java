package application;

import javafx.application.Application;
import javafx.stage.Stage;
import view.ViewManager;


/******************************************************************
* Creates an instance of ViewManager and shows the initial screen 
* of the application.
******************************************************************/
public class DesignFx extends Application {
  @Override
  public void start(Stage primaryStage) {
    try {
 
      ViewManager manager = new ViewManager();
      primaryStage = manager.getMainStage();
      primaryStage.show();

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    launch(args);
  }
}



