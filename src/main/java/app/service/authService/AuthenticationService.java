package app.service.authService;


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

    public String verifyRegister(User user) {
        if (isUsernameExists(user.getUsername())) {
            return "Existing username";
        } else if (isEmailExists(user.getEmail())) {
            return "Existing email";
        }
        return "Register validation successfully";
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
