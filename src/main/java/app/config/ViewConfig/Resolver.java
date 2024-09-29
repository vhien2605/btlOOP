package app.config.ViewConfig;

import javafx.scene.Parent;

public interface Resolver {
    Parent resolve(String viewName);

    void setPath(String path);
}
