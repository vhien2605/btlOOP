package app.config.ViewConfig;

import javafx.stage.Stage;

public interface Resolver {
    void setUp(String path, Stage stage);

    void renderScene(String viewName, int width, int height);
}
