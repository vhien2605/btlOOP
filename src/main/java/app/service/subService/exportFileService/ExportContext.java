package app.service.subService.exportFileService;

import java.io.File;

import javafx.scene.Node;

public class ExportContext {
    private ExportStrategy strategy;

    public ExportContext(ExportStrategy strategy) {
        this.strategy = strategy;
    }

    /**
     * Setter behavior for {@link ExportContext}
     *
     * @param strategy behavior to change
     */
    public void setStrategy(ExportStrategy strategy) {
        this.strategy = strategy;
    }

    public File export(Node pane) {
        return strategy.export(pane);
    }
}
