package app.controller.user.HomePage;

import java.io.IOException;

import app.config.ViewConfig.FXMLResolver;
import app.controller.BaseController;
import app.controller.helper.ShowAlert;
import app.controller.user.HomePage.BookDiscoverSection;
import app.repository.UserRepository;
import app.service.authService.AuthenticationService;
import app.service.authService.SessionService;
import app.service.mainService.UserService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class ChangePasswordController {
    private MainHomePageController homeController;

    private ShowAlert showAlert;

    private AuthenticationService authenticationService;

    private UserService userService;

    protected ChangePasswordController(MainHomePageController mainHomePageController) {
        this.homeController = mainHomePageController;
    }

    public void handleChangePasswordBackButton() {
        homeController.ChangePasswordPage.setVisible(false);
        homeController.userInfoBox.setVisible(false);
    }

    public void handleChangePasswordButtonIsclicked() {
        homeController.ChangePasswordPage.setVisible(true);
    }

    public void handleSaveChangePassword() {
        
    }

    public void initialize() {
        homeController.ChangePasswordPage.setVisible(false);
        showAlert = new ShowAlert();
        authenticationService = new AuthenticationService(new SessionService(),
                new UserService(new UserRepository()));
        userService = new UserService(new UserRepository());
    }

}
