package app.controller.admin.SettingTab;

import app.controller.BaseController;
import app.controller.admin.Panel.SidebarController;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;

public class MainSettingController implements BaseController {
    @FXML
    Pane sidebar;

    @Override
    public void initialize() {
        setStateButton();
    }

    void setStateButton() {
        SidebarController sidebarController = (SidebarController) sidebar.getProperties().get("controller");
        if (sidebarController != null) {
            sidebarController.setStateButton(5);
        }
    }
}
