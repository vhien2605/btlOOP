package app.service.authService;

import app.domain.User;
import app.repository.UserRepository;
import app.service.mainService.UserService;

public class AuthUser {
    private User user;
    private static AuthUser authUser;
    private final UserService userService;

    private AuthUser(UserService userService) {
        this.userService = userService;
    }

    public static AuthUser getInstance() {
        if (authUser == null) {
            authUser = new AuthUser(new UserService(new UserRepository()));
            return authUser;
        }
        return authUser;
    }

    public void createCurrentUser(String id) {
        if (user == null) {
            user = this.userService.findById(id);
        }
    }

    public User getCurrentUser() {
        return user;
    }
}
