package app.controller.admin.Panel;

import java.util.List;

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

    List<Button> buttons;

    private static int PROFILE_BUTTON = 0;
    private static int PASSWORD_BUTTON = 1;

    private static int currButton = PROFILE_BUTTON;

    @FXML
    public void handleProfileButton() {
        currButton = PROFILE_BUTTON;
        FXMLResolver.getInstance().renderScene("admin/settingTab/setting_tab");
    }

    @FXML
    public void handlePasswordButton() {
        currButton = PASSWORD_BUTTON;
        FXMLResolver.getInstance().renderScene("admin/settingTab/change_password");
    }

    @FXML
    public void handleBackButton() {
        currButton = PROFILE_BUTTON;
        FXMLResolver.getInstance().renderScene("admin/homeTab/home_tab");
    }

    @Override
    public void initialize() {
        buttons = List.of(profileButton, passwordButton);
        setStateButton();
    }

    void setStateButton() {
        buttons.get(currButton).getStyleClass().add("curr-button");
    }
}
