package app.controller.auth;

import app.config.ViewConfig.FXMLResolver;
import app.controller.BaseController;
import app.controller.helper.ShowAlert;
import app.exception.auth.DuplicateException;
import app.repository.UserRepository;
import app.service.authService.AuthenticationService;
import app.service.authService.SessionService;
import app.service.mainService.UserService;
import app.service.subService.GMailer;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.util.Random;

public class ForgetPasswordController implements BaseController {
    @FXML
    TextField emailField;
    @FXML
    TextField codeField;
    @FXML
    Button checkButton;
    @FXML
    Button sendOTPButton;

    @FXML
    Button loginRedirectButton;

    @FXML
    Button signupRedirectButton;

    ShowAlert showAlert;

    private AuthenticationService authenticationService;

    private String randomOTPcode;

    @Override
    public void initialize() {
        showAlert = new ShowAlert();
        authenticationService = new AuthenticationService(new SessionService(),
                new UserService(new UserRepository()));
    }

    @FXML
    public void handleCheckButton() {
        String email = emailField.getText();
        String otpCode = codeField.getText();
        if (email.isEmpty()) {
            showAlert.showAlert("Please fill your email!", "error");
            return;
        }
        if (otpCode.isEmpty()) {
            showAlert.showAlert("Please fill your OTP code!", "error");
            return;
        }
        if (otpCode.equals(randomOTPcode)) {
            showAlert.showAlert("Verify successfully!", "success");
            FXMLResolver.getInstance().renderScene("auth/resetPassword");
            ResetPasswordController resetPasswordController = FXMLResolver.getInstance().getLoader().getController();
            resetPasswordController.setEmail(email);
        } else {
            showAlert.showAlert("OTP code is invalid!", "error");
        }
    }

    @FXML
    public void handleOTPButton() {
        String email = emailField.getText();
        if (email.isEmpty()) {
            showAlert.showAlert("Please fill your email!", "error");
            return;
        }
        try {
            authenticationService.verifyEmail(email);
            GMailer gMailer = new GMailer(email);
            Random random = new Random();
            int randomOTP = 100000 + random.nextInt(900000);
            randomOTPcode = Integer.toString(randomOTP);
            Platform.runLater(() -> {
                try {
                    showAlert.showAlert("Send email successfully", "success");
                    gMailer.sendMail("OTP code (Please don't share it to any one)", randomOTPcode, null);
                } catch (Exception e) {
                    showAlert.showAlert("Send OTP failed,please try later!", "error");
                }
            });
        } catch (DuplicateException e) {
            showAlert.showAlert(e.getMessage(), "error");
        } catch (Exception e) {
            e.printStackTrace();
        }
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
}
