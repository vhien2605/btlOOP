package app.config.ViewConfig;

import javafx.stage.Stage;

/**
 * Helper interface for rendering front-end frame / static resources in application
 */
public interface Resolver {
    /**
     *
     * @param path Path to resources file
     * @param stage JavaFX 's stage
     * @param sceneWidth sceneWidth when rendering in stage
     * @param sceneHeight sceneHeight when rendering in stage
     */
    void setUp(String path, Stage stage, int sceneWidth, int sceneHeight);

    /**
     *
     * @param viewName resources fileName/viewName
     */
    void renderScene(String viewName);
}
