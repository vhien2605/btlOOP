package app.config.ViewConfig;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;

public class FXMLResolver implements Resolver {
    private String rootPath;
    private String type = "fxml";
    private static FXMLResolver fxmlResolver;

    private FXMLResolver() {

    }

    public static FXMLResolver getInstance() {
        if (fxmlResolver == null) {
            fxmlResolver = new FXMLResolver();
        }
        return fxmlResolver;
    }

    @Override
    public Parent resolve(String viewName) {
        try {
            String pathLoader = rootPath + "/" + viewName + "." + type;
            System.out.println(pathLoader);
            FXMLLoader loaderObject = new FXMLLoader(getClass().getResource(pathLoader));
            return loaderObject.load();
        } catch (IOException e) {
            System.out.println("can't find view " + viewName);
            return null;
        }
    }

    @Override
    public void setPath(String path) {
        rootPath = path;
    }
}
