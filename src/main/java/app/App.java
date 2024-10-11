package app;

import app.config.ViewConfig.FXMLResolver;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * JavaFX App
 */
public class App extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        stage.initStyle(StageStyle.UNDECORATED);
        FXMLResolver.getInstance().setUp("/view", stage, 1100, 650);
        FXMLResolver.getInstance().renderScene("home_tab");
    }
}