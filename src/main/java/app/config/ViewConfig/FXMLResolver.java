package app.config.ViewConfig;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * FXMlResolver used for rendering FXML resources
 * Use Singleton design pattern for one stage which can switch many scenes
 */
public class FXMLResolver implements Resolver {
    private int sceneWidth;
    private int sceneHeight;

    private String rootPath;
    private String type = "fxml";
    private static FXMLResolver fxmlResolver;
    private Stage primaryStage;
    private Scene scene;

    /**
     * private Constructor for Singleton design pattern
     */
    private FXMLResolver() {

    }

    /**
     * getInstance function get the current instance for singleton or create new
     * instance
     *
     * @return Instance of FXMLResolver
     */
    public static FXMLResolver getInstance() {
        if (fxmlResolver == null) {
            fxmlResolver = new FXMLResolver();
        }
        return fxmlResolver;
    }

    /**
     *
     * @param viewName resources fileName/viewName
     * @return Superclass Object of Javafx 's component
     */
    private Parent resolve(String viewName) {
        try {
            String pathLoader = rootPath + "/" + viewName + "." + type;
            System.out.println(pathLoader);
            FXMLLoader loaderObject = new FXMLLoader(getClass().getResource(pathLoader));
            return loaderObject.load();
        } catch (IOException e) {
            System.out.println("can't find view " + viewName);
            // System.out.println(e.getMessage());
            return null;
        }
    }

    /**
     *
     * @param path        Path to resources file
     * @param stage       JavaFX 's stage
     * @param sceneWidth  sceneWidth when rendering in stage
     * @param sceneHeight sceneHeight when rendering in stage
     */
    @Override
    public void setUp(String path, Stage stage, int sceneWidth, int sceneHeight) {
        this.rootPath = path;
        this.primaryStage = stage;
        this.sceneWidth = sceneWidth;
        this.sceneHeight = sceneHeight;
    }

    /**
     *
     * @param viewName resources fileName/viewName
     */
    @Override
    public void renderScene(String viewName) {
        scene = new Scene(resolve(viewName), sceneWidth, sceneHeight);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
