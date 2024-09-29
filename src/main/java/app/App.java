package app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

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
        FXMLLoader layout = new FXMLLoader(getClass().getResource("/app/view/DashBoard.fxml"));
        scene = new Scene(layout.load(), 1280, 640);
        stage.setScene(scene);
        stage.show();
    }

}