package app.controller.user.HomePage;

import javax.mail.Session;

import app.config.ViewConfig.FXMLResolver;
import app.controller.helper.ShowAlert;
import app.service.authService.SessionService;
import javafx.event.ActionEvent;

public class UserButtonController {
    public static final String PROFILE = "Profile";
    public static final String CHANGE_PASSWORD = "Change password";
    public static final String SIGN_OUT = "Sign out";

    private MainHomePageController homeController;

    private SessionService sessionService;

    private ShowAlert showAlert;

    protected UserButtonController(MainHomePageController mainHomePageController) {
        this.homeController = mainHomePageController;
    }

    public void initialize() {
        homeController.userButton.getItems().addAll(PROFILE, CHANGE_PASSWORD, SIGN_OUT);
        sessionService = new SessionService();
        showAlert = new ShowAlert();
        userButtonHandleEvent();
    }



    private void userButtonHandleEvent() {
        homeController.userButton.setOnAction(ActionEvent -> {
            String selectedItem = homeController.userButton.getValue();

            if (selectedItem.equals(SIGN_OUT)) {
                Signout();
            }

        });
    }

    private void Signout() {
        showAlert.showAlert("Log out successfully!", "success");
        sessionService.clearWhenLogout();
        FXMLResolver.getInstance().renderScene("auth/login");
    }
}
