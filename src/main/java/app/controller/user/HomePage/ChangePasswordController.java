package app.controller.user.HomePage;

import java.io.IOException;

import app.config.ViewConfig.FXMLResolver;
import app.controller.BaseController;
import app.controller.helper.ShowAlert;
import app.controller.user.HomePage.BookDiscoverSection;
import app.domain.DTO.PasswordChangeDTO;
import app.domain.DTO.SurfaceUserDTO;
import app.exception.auth.PasswordException;
import app.exception.auth.SessionException;
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
        if (homeController.currentPasswordField.getText().isEmpty()) {
            showAlert.showAlert("Current password can't be empty!", "error");
            clearField();
            return;
        }
        if (homeController.newPasswordField.getText().isEmpty()) {
            showAlert.showAlert("New password can't be empty!", "error");
            clearField();
            return;
        }
        if (homeController.confirmNewPasswordField.getText().isEmpty()) {
            showAlert.showAlert("Confirm password can't be empty!", "error");
            clearField();
            return;
        }
        handleSubmitSave();
    }

    private void handleSubmitSave() {
        try {
            SurfaceUserDTO user = authenticationService.getCurrentUser();
            try {
                PasswordChangeDTO userDTO = new PasswordChangeDTO(user.getUsername(),
                        homeController.currentPasswordField.getText(),
                        homeController.newPasswordField.getText(),
                        homeController.confirmNewPasswordField.getText());
                authenticationService.verifyPasswordChangeRequest(userDTO);
                userService.handleUpdatePassword(userDTO);
                showAlert.showAlert("Change password sucessfully!", "success");
                clearField();
            } catch (PasswordException e) {
                clearField();
                showAlert.showAlert(e.getMessage(), "error");
            }
        } catch (SessionException e) {
            FXMLResolver.getInstance().renderScene("auth/login");
        }
    }

    private void clearField() {
        homeController.currentPasswordField.clear();
        homeController.newPasswordField.clear();
        homeController.confirmNewPasswordField.clear();
    }

    public void initialize() {
        homeController.ChangePasswordPage.setVisible(false);
        showAlert = new ShowAlert();
        authenticationService = new AuthenticationService(new SessionService(),
                new UserService(new UserRepository()));
        userService = new UserService(new UserRepository());
    }

}
