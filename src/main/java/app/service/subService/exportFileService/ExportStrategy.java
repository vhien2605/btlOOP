package app.service.subService.exportFileService;

import java.io.File;
import javafx.scene.Node;

public interface ExportStrategy {
    /**
     * Export data from 1 pane to file like pdf, image, ...
     * 
     * @param pane Container in javafx.
     * @return File exported from pane.
     */
    File export(Node pane);
}
