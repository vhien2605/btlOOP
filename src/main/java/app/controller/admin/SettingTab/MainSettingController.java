package app.controller.admin.SettingTab;

import app.controller.BaseController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class MainSettingController implements BaseController {

    @FXML
    Label nameField;
    @FXML
    Label usernameField;
    @FXML
    Label addressField;
    @FXML
    Label phoneField;

    @Override
    public void initialize() {

    }
}
