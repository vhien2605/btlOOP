package app.controller.admin.Panel;

import app.config.ViewConfig.FXMLResolver;
import app.controller.BaseController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class SettingsSidebarController implements BaseController {

    @FXML
    Button profileButton;

    @FXML
    Button passwordButton;
    @FXML
    Label nameLabel;

    @FXML
    Button backButton;

    @FXML
    public void handleProfileButton() {
        FXMLResolver.getInstance().renderScene("admin/settingTab/setting_tab");
    }

    @FXML
    public void handlePasswordButton() {
        FXMLResolver.getInstance().renderScene("admin/settingTab/change_password");
    }

    @FXML
    public void handleBackButton() {
        FXMLResolver.getInstance().renderScene("admin/homeTab/home_tab");
    }

    @Override
    public void initialize() {

    }
}
