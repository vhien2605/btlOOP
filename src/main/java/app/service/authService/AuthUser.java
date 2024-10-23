package app.service.authService;

import app.domain.User;
import app.repository.UserRepository;
import app.service.mainService.UserService;


/**
 * Get current {@link User} for service in application after Authenication.
 */
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

    /**
     * Find current user by id.
     *
     * @param id {@link User}'s id
     */
    public void findCurrentUser(String id) {
        if (user == null) {
            user = this.userService.findById(id);
        }
    }

    /**
     * get current {@link User} method.
     *
     * @return current {@link User}
     */
    public User getCurrentUser() {
        return user;
    }
}
