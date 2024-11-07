package app.controller.auth;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import app.config.ViewConfig.FXMLResolver;
import app.controller.helper.ShowAlert;
import app.domain.DTO.RegisterUserDTO;
import app.repository.UserRepository;
import app.service.authService.AuthenticationService;
import app.service.authService.SessionService;
import app.service.mainService.UserService;

public class RegisterController {
    @FXML
    Button signupRedirectButton;

    @FXML
    Button loginRedirectButton;

    @FXML
    Button registerSubmitButton;

    @FXML
    TextField fullNameFiled;

    @FXML
    PasswordField passwordField;

    @FXML
    PasswordField confirmPasswordField;

    @FXML
    TextField idField;

    @FXML
    TextField userNameField;

    @FXML
    TextField emailField;

    ShowAlert showAlert;

    AuthenticationService authService;

    UserService userService;

    public void initialize() {
        showAlert = new ShowAlert();
        authService = new AuthenticationService(new SessionService(), new UserService(new UserRepository()));
        userService = new UserService(new UserRepository());
    }

    @FXML
    private void handleButtonAction(ActionEvent e) {
        if (e.getSource() == registerSubmitButton) {
            submitRegister();
        } else if (e.getSource() == signupRedirectButton) {
            FXMLResolver.getInstance().renderScene("auth/register");
        } else if (e.getSource() == loginRedirectButton) {
            FXMLResolver.getInstance().renderScene("auth/login");
        }
    }

    private void submitRegister() {
        RegisterUserDTO data = getData();

        if (data == null) {
            return;
        }

        String result = authService.verifyRegister(data);

        if (result.equals("Existing username")) {
            showAlert.showAlert("Existing username!", "error");
            return;
        }

        if (result.equals("Existing id")) {
            showAlert.showAlert("Existing id!", "error");
            return;
        }

        if (userService.handleSaveUser(data)) {
            showAlert.showAlert("Registration successful!\nSign in to continue!", "success");
        } else {
            showAlert.showAlert("registration failed!", "error");
        }

        clearField();
    }

    private RegisterUserDTO getData() {
        if (!validate()) {
            return null;
        }

        return new RegisterUserDTO(
                idField.getText(),
                fullNameFiled.getText(),
                userNameField.getText(),
                emailField.getText(),
                passwordField.getText(),
                confirmPasswordField.getText());
    }

    private void clearField() {
        idField.clear();
        fullNameFiled.clear();
        userNameField.clear();
        emailField.clear();
        passwordField.clear();
        confirmPasswordField.clear();
    }

    private boolean validate() {
        // Kiểm tra các trường không được để trống
        if (idField.getText().isEmpty() || userNameField.getText().isEmpty() ||
                fullNameFiled.getText().isEmpty() || passwordField.getText().isEmpty() ||
                confirmPasswordField.getText().isEmpty() || emailField.getText().isEmpty()) {
            showAlert.showAlert("Please fill in all information completely!", "error");
            return false;
        }

        // Kiểm tra mật khẩu và xác nhận mật khẩu
        if (!passwordField.getText().equals(confirmPasswordField.getText())) {
            showAlert.showAlert("Password does not match!", "error");
            return false;
        }

        // Kiểm tra định dạng email
        String email = emailField.getText();
        if (!email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            showAlert.showAlert("Invalid email!", "error");
            return false;
        }

        return true;
    }

}
