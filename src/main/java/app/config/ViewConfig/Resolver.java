package app.config.ViewConfig;

import javafx.stage.Stage;

/**
 * Interface {@link Resolver} for rendering front-end frame / static resources in application
 */
public interface Resolver {
    /**
     * setup for instance of {@link Resolver} implements
     *
     * @param path        Path to resources file
     * @param stage       JavaFX 's stage
     * @param sceneWidth  sceneWidth when rendering in stage
     * @param sceneHeight sceneHeight when rendering in stage
     */
    void setUp(String path, Stage stage, int sceneWidth, int sceneHeight);

    /**
     * Render View from input to the current Scene
     *
     * @param viewName resources fileName/viewName
     */
    void renderScene(String viewName);
}
