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

public class ViewProfileController {
    private MainHomePageController homeController;

    protected ViewProfileController(MainHomePageController mainHomePageController) {
        this.homeController = mainHomePageController;
    }

    public void handleBackButton() {
        homeController.ViewUserProfilePage.setVisible(false);
    }

    public void handleViewProfilePage() {
        homeController.ViewUserProfilePage.setVisible(true);
    }

    private void renderData() {
        homeController.userProfileName.setText(MainHomePageController.user.getName());
        homeController.userProfileUsername.setText(MainHomePageController.user.getUsername());
        homeController.userProfileAddress.setText(MainHomePageController.user.getAddress());
        homeController.userProfileEmail.setText(MainHomePageController.user.getEmail());
        homeController.userProfilePhoneNumber.setText(MainHomePageController.user.getPhoneNumber());
    }

    public void initialize() {
        homeController.ViewUserProfilePage.setVisible(false);
        renderData();
    }

}
