package app.service.authService;


import app.domain.DTO.PasswordChangeDTO;
import app.domain.DTO.RegisterUserDTO;
import app.domain.DTO.ReportDetail;
import app.domain.User;
import app.service.mainService.UserService;
import javafx.application.Platform;

/**
 * Authentication Service class
 */
public class AuthenticationService {
    private final SessionService sessionService;
    private final UserService userService;

    public AuthenticationService(SessionService sessionService, UserService userService) {
        this.sessionService = sessionService;
        this.userService = userService;
    }

    public String verifyLogin(String username, String password) {
        if (!isUsernameExists(username)) {
            return "Username is not found";
        }
        if (!isUsernameAndPasswordMapping(username, password)) {
            return "Password is incorrect";
        }
        return "Authentication successfully";
    }

    public String verifyRegister(RegisterUserDTO user) {
        if (isUsernameExists(user.getUsername())) {
            return "Existing username";
        } else if (isEmailExists(user.getEmail())) {
            return "Existing email";
        } else if (!user.getPassword().equals(user.getConfirmPassword())) {
            return "Confirm password is not mapping";
        }
        return "Register validation successfully";
    }

    public String verifyPasswordChangeRequest(PasswordChangeDTO user) {
        if (this.userService.findByUsernameAndPassword(user.getUsername()
                , user.getCurrentPassword()) == null) {
            return "Current password is incorrect";
        } else if (!user.getNewPassword().equals(user.getConfirmNewPassword())) {
            return "Confirm password is not mapping";
        } else if (user.getNewPassword().equals(user.getCurrentPassword())) {
            return "New password shouldn't equal to current password";
        }
        return "Change password validation successfully";
    }

    public boolean isUsernameExists(String username) {
        return this.userService.findByUsername(username) != null;
    }

    public boolean isEmailExists(String email) {
        return this.userService.findByEmail(email) != null;
    }

    public boolean isUsernameAndPasswordMapping(String username, String password) {
        User user = this.userService.findByUsernameAndPassword(username, password);
        if (user == null) {
            return false;
        }
        this.sessionService.createSession(user.getId(), user.getRole());
        return true;
    }
}
