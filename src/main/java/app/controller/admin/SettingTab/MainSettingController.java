package app.controller.admin.SettingTab;

import app.config.ViewConfig.FXMLResolver;
import app.controller.BaseController;
import app.domain.User;
import app.exception.auth.SessionException;
import app.repository.UserRepository;
import app.service.authService.AuthenticationService;
import app.service.authService.SessionService;
import app.service.mainService.ReportService;
import app.service.mainService.UserService;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class MainSettingController implements BaseController {

    @FXML
    Label nameField;
    @FXML
    Label usernameField;
    @FXML
    Label addressField;
    @FXML
    Label phoneField;
    @FXML
    Label emailField;

    private AuthenticationService authenticationService;

    @Override
    public void initialize() {
        authenticationService = new AuthenticationService(new SessionService()
                , new UserService(new UserRepository()));
        currentUser();
    }

    private void currentUser() {
        try {
            User user = this.authenticationService.getCurrentUser();
            nameField.setText(user.getName());
            usernameField.setText(user.getUsername());
            addressField.setText(user.getAddress());
            emailField.setText(user.getEmail());
            phoneField.setText(user.getPhoneNumber());
        } catch (SessionException e) {
            FXMLResolver.getInstance().renderScene("auth/login");
        }
    }
}
