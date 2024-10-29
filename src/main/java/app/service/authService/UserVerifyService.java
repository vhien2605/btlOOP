package app.service.authService;

import app.domain.User;
import app.repository.UserRepository;
import app.service.mainService.UserService;


/**
 * Authentication Service class
 */
public class UserVerifyService {
    private final UserService userService;
    private final SessionService sessionService;
    private static User currentUser;

    public UserVerifyService(UserService userService, SessionService sessionService) {
        this.userService = userService;
        this.sessionService = sessionService;
    }

    public boolean verify(String username, String password) {
        currentUser = this.userService.findByUsernameAndPassword(username, password);
        if (currentUser == null) {
            return false;
        }
        createSession(currentUser.getId(), currentUser.getRole());
        return true;
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    private void createSession(String id, String Role) {
        this.sessionService.createSession(id, Role);
    }
}
