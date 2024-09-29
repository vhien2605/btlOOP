package app;

import app.config.ViewConfig.FXMLResolver;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    private static Stage stage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage arg0) throws Exception {
        stage = arg0;
        FXMLResolver.getInstance().setPath("/app/view");
        scene = new Scene(FXMLResolver.getInstance().resolve("staticLayout/header"), 1280, 640);
        stage.setScene(scene);
        stage.show();
    }
}