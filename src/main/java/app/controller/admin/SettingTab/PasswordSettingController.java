package app.controller.admin.SettingTab;

import app.config.ViewConfig.FXMLResolver;
import app.controller.BaseController;
import app.controller.helper.ShowAlert;
import app.domain.DTO.PasswordChangeDTO;
import app.domain.DTO.SurfaceUserDTO;
import app.exception.auth.PasswordException;
import app.exception.auth.SessionException;
import app.repository.UserRepository;
import app.service.authService.AuthenticationService;
import app.service.authService.SessionService;
import app.service.mainService.UserService;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class PasswordSettingController implements BaseController {
    @FXML
    TextField currentPasswordField;

    @FXML
    TextField newPasswordField;

    @FXML
    TextField confirmNewPasswordField;

    private ShowAlert showAlert;

    @FXML
    public void handleSaveChangeButton() {
        if (currentPasswordField.getText().isEmpty()) {
            showAlert.showAlert("Current password can't be empty!", "error");
            clearField();
            return;
        }
        if (newPasswordField.getText().isEmpty()) {
            showAlert.showAlert("New password can't be empty!", "error");
            clearField();
            return;
        }
        if (confirmNewPasswordField.getText().isEmpty()) {
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
                        currentPasswordField.getText(),
                        newPasswordField.getText(),
                        confirmNewPasswordField.getText());
                authenticationService.verifyPasswordChangeRequest(userDTO);
                userService.handleUpdatePassword(userDTO);
                showAlert.showAlert("Change password sucessfully!", "success");
                clearField();
            } catch (PasswordException e) {
                showAlert.showAlert(e.getMessage(), "error");
            }
        } catch (SessionException e) {
            FXMLResolver.getInstance().renderScene("auth/login");
        }
    }

    private AuthenticationService authenticationService;
    private UserService userService;

    @Override
    public void initialize() {
        showAlert = new ShowAlert();
        authenticationService = new AuthenticationService(new SessionService(),
                new UserService(new UserRepository()));
        userService = new UserService(new UserRepository());
    }


    private void clearField() {
        currentPasswordField.clear();
        newPasswordField.clear();
        confirmNewPasswordField.clear();
    }
}
