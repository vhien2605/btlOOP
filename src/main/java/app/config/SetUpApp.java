package app.config;

import app.config.ViewConfig.FXMLResolver;
import app.controller.helper.ShowAlert;
import app.controller.user.HomePage.MainHomePageController;
import app.exception.auth.SessionException;
import app.repository.UserRepository;
import app.service.authService.AuthenticationService;
import app.service.authService.SessionService;
import app.service.mainService.UserService;

public class SetUpApp {

    private static SessionService sessionService = new SessionService();

    public static void setUpApp() {
        try {
            String sessionData = sessionService.verifySession();
            String role = sessionData.split(" ")[1];
            if (role.equals("ADMIN")) {
                setUpAppAdmin();
            } else {
                setUpAppUser();
            }
        } catch (SessionException e) {
            FXMLResolver.getInstance().renderScene("auth/login");
        }
    }

    private static void setUpAppAdmin() {
        FXMLResolver.getInstance().renderScene("admin/homeTab/home_tab");
    }

    private static void setUpAppUser() {
        MainHomePageController
                .getAuthService(new AuthenticationService(new SessionService(), new UserService(new UserRepository())));
        FXMLResolver.getInstance().renderScene("user/homeTab/home");
    }
}
