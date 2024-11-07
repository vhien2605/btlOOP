package app.controller.auth;

import app.config.ViewConfig.FXMLResolver;
import app.controller.helper.ShowAlert;
import app.repository.UserRepository;
import app.service.authService.AuthenticationService;
import app.service.authService.SessionService;
import app.service.mainService.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {
    @FXML
    Button signupRedirectButton;

    @FXML
    Button loginRedirectButton;

    @FXML
    Button loginSubmitButton;

    @FXML
    TextField usernameField;

    @FXML
    PasswordField passwordField;

    ShowAlert showAlert;

    AuthenticationService authService;

    SessionService sessionService;

    public void initialize() {
        showAlert = new ShowAlert();
        authService = new AuthenticationService(new SessionService(), new UserService(new UserRepository()));
        sessionService = new SessionService();
    }

    @FXML
    private void handleButtonAction(ActionEvent e) {
        if (e.getSource() == loginSubmitButton) {
            submitLogin();
        } else if (e.getSource() == signupRedirectButton) {
            FXMLResolver.getInstance().renderScene("auth/register");
        } else if (e.getSource() == loginRedirectButton) {
            FXMLResolver.getInstance().renderScene("auth/login");
        }
    }

    private void submitLogin() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (username.isEmpty() || password.isEmpty()) {
            showAlert.showAlert("Username and password cannot be empty!", "error");
            return;
        }

        // Kiểm tra tính hợp lệ của tài khoản
        String result = authService.verifyLogin(username, password);

        if (result.equals("Username is not found")) {
            showAlert.showAlert("Username is not found!", "error");
            return;
        }

        if (result.equals("Password is incorrect")) {
            showAlert.showAlert("Password is incorrect!", "error");
            return;
        }

        appRedirection();
    }

    private void appRedirection() {
        // Check render app admin or user
        String sessionData = sessionService.verifySession();

        if (!sessionData.equals("Session not found") ||
                !sessionData.equals("Session is invalid")) {
            String role = sessionData.split(" ")[1];
            if (role.equals("ADMIN")) {
                setUpAppAdmin();
            } else {
                setUpAppUser();
            }
        } else {
            showAlert.showAlert(sessionData, "error");
        }
    }

    private void setUpAppAdmin() {
        FXMLResolver.getInstance().setRootPath("/view/admin");
        FXMLResolver.getInstance().renderScene("homeTab/home_tab");
    }

    private void setUpAppUser() {
        FXMLResolver.getInstance().setRootPath("/view/user");
        FXMLResolver.getInstance().renderScene("homeTab/home");
    }
}
