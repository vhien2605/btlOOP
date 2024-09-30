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
    public void start(Stage arg0) throws Exception {
        FXMLResolver.getInstance().setUp("/app/view", arg0);
        FXMLResolver.getInstance().renderScene("DashBoard", 1280, 640);
    }
}