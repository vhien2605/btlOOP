package app.controller.admin.SettingTab;

import app.controller.BaseController;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class PasswordSettingController implements BaseController {
    @FXML
    TextField currentPasswordField;

    @FXML
    TextField newPasswordField;

    @FXML
    TextField confirmNewPasswordField;

    @FXML
    public void handleSaveChangeButton() {

    }

    @Override
    public void initialize() {

    }
}
