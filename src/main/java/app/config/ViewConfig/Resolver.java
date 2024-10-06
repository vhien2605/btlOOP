package app.config.ViewConfig;

import javafx.stage.Stage;

public interface Resolver {
    void setUp(String path, Stage stage, int sceneWidth, int sceneHeight);

    void renderScene(String viewName);
}
