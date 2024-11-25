package app.controller.auth;

import app.config.ViewConfig.FXMLResolver;
import app.controller.BaseController;
import app.controller.helper.ShowAlert;
import app.repository.UserRepository;
import app.service.mainService.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;


public class ResetPasswordController implements BaseController {
    @FXML
    Button loginRedirectButton;
    @FXML
    Button signupRedirectButton;

    @FXML
    TextField passwordField;

    @FXML
    TextField confirmPasswordField;
    @FXML
    Button resetButton;
    ShowAlert showAlert;
    private UserService userService;

    private String targetEmail;

    @Override
    public void initialize() {
        showAlert = new ShowAlert();
        userService = new UserService(new UserRepository());
    }

    public void setEmail(String email) {
        targetEmail = email;
    }

    @FXML
    private void handleButtonAction(ActionEvent e) {
        if (e.getSource() == signupRedirectButton) {
            FXMLResolver.getInstance().renderScene("auth/register");
        }
        if (e.getSource() == loginRedirectButton) {
            FXMLResolver.getInstance().renderScene("auth/login");
        }
    }

    @FXML
    public void handleResetButton() {
        String newPassword = passwordField.getText();
        String confirmNewPassword = confirmPasswordField.getText();
        if (newPassword.isEmpty()) {
            showAlert.showAlert("Please enter new password!", "error");
            return;
        }
        if (confirmNewPassword.isEmpty()) {
            showAlert.showAlert("Please enter confirm password!", "error");
            return;
        }
        if (!newPassword.equals(confirmNewPassword)) {
            showAlert.showAlert("Confirm password is not mapping!", "error");
            return;
        }
        userService.updatePasswordByEmail(targetEmail, newPassword);
        showAlert.showAlert("Reset password successfully!, now redirect to Login page", "success");
        FXMLResolver.getInstance().renderScene("auth/login");
    }
}
