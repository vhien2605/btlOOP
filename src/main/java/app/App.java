package app;

import app.config.ViewConfig.FXMLResolver;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage arg0) {
        FXMLResolver.getInstance().setUp("/view", arg0, 1280, 720);
        FXMLResolver.getInstance().renderScene("home_tab");
    }
}