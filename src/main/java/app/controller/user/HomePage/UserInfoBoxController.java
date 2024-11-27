package app.controller.user.HomePage;

import java.io.IOException;

import app.config.ViewConfig.FXMLResolver;
import app.controller.BaseController;
import app.controller.helper.ShowAlert;
import app.controller.user.HomePage.BookDiscoverSection;
import app.service.authService.AuthenticationService;
import app.service.authService.SessionService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class UserInfoBoxController {
    private MainHomePageController homeController;

    private ShowAlert showAlert;
    private SessionService sessionService;

    protected UserInfoBoxController(MainHomePageController mainHomePageController) {
        this.homeController = mainHomePageController;
    }

    public void initialize() {
        showAlert = new ShowAlert();
        sessionService = new SessionService();
        setDefault();
    }

    public void handleChangeStateUserBox() {
        if (homeController.userInfoBox.isVisible()) {
            homeController.userInfoBox.setVisible(false);
        } else {
            homeController.userInfoBox.setVisible(true);
        }
    }

    public void handleSignOut() {
        showAlert.showAlert("Log out successfully!", "success");
        sessionService.clearWhenLogout();
        FXMLResolver.getInstance().renderScene("auth/login");
    }
    
    private void setDefault() {
        homeController.userInfoBox.setVisible(false);
    }
}
