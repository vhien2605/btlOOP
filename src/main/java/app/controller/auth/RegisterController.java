package app.controller.auth;

import app.config.DbConfig;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RegisterController {
    @FXML
    Button signupRedirectButton;

    @FXML
    Button loginRedirectButton;

    @FXML
    Button registerSubmitButton;

    @FXML
    TextField usernameField;

    @FXML
    TextField passwordField;

    @FXML
    TextField confirmPasswordField;

    @FXML
    TextField idFiled;

    @FXML
    TextField userNameFiled;

    @FXML
    TextField emailField;

    public void initialize() {

    }

    @FXML
    private void handleButtonAction(ActionEvent e) {

    }
}
