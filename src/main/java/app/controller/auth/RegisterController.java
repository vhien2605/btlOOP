package app.controller.auth;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import app.controller.helper.ShowAlert;

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

    ShowAlert showAlert;

    public void initialize() {
        showAlert = new ShowAlert();
    }

    @FXML
    private void handleButtonAction(ActionEvent e) {

    }
}
