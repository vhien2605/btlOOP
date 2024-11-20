package app.config;

import app.config.ViewConfig.FXMLResolver;
import app.controller.helper.ShowAlert;
import app.controller.user.HomePage.MainHomePageController;
import app.exception.auth.SessionException;
import app.repository.UserRepository;
import app.service.authService.AuthenticationService;
import app.service.authService.SessionService;
import app.service.mainService.UserService;

/**
 * This class is responsible for setting up and redirecting the application
 * based on the session state.
 * It determines the user's role (ADMIN or USER) and redirects to the
 * appropriate page.
 */
public class SetUpApp {

    private static SessionService sessionService = new SessionService();

    /**
     * This method verifies the session and redirects the user to the appropriate
     * page
     * based on their role. If the session is invalid, the user is redirected to the
     * login page.
     */
    public static void setUpApp() {
        try {
            // Retrieve the current session data
            String sessionData = sessionService.verifySession();
            String role = sessionData.split(" ")[1];
            if (role.equals("ADMIN")) {
                setUpAppAdmin();
            } else {
                setUpAppUser();
            }
        } catch (SessionException e) {
            // If there is an issue with the session, redirect to the login page
            FXMLResolver.getInstance().renderScene("auth/login");
        }
    }

    /**
     * Redirects the user to the admin page.
     */
    private static void setUpAppAdmin() {
        FXMLResolver.getInstance().renderScene("admin/homeTab/home_tab");
    }

    /**
     * Redirects the user to the user page.
     * It also sets up the authentication service for the user page.
     */
    private static void setUpAppUser() {
        FXMLResolver.getInstance().renderScene("user/homeTab/home");
    }
}
