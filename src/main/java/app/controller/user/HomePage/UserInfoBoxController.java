package app.controller.user.HomePage;

import app.config.ViewConfig.FXMLResolver;

import app.controller.helper.ShowAlert;

import app.domain.DTO.SurfaceUserDTO;
import app.repository.UserRepository;
import app.service.authService.AuthenticationService;
import app.service.authService.SessionService;
import app.service.mainService.UserService;

public class UserInfoBoxController {
    private MainHomePageController homeController;

    private ShowAlert showAlert;
    private SessionService sessionService;

    private SurfaceUserDTO user;

    private AuthenticationService authenticationService;

    protected UserInfoBoxController(MainHomePageController mainHomePageController) {
        this.homeController = mainHomePageController;
    }

    public void initialize() {
        showAlert = new ShowAlert();
        sessionService = new SessionService();
        authenticationService = new AuthenticationService(new SessionService(), new UserService(new UserRepository()));
        GetUserInfo();
        setDefault();
        setLabelInfo();
    }

    public void handleChangeStateUserBox() {
        if (homeController.userInfoBox.isVisible()) {
            homeController.userInfoBox.setVisible(false);
        } else {
            homeController.userInfoBox.setVisible(true);
        }
    }

    public void handleSignOut() {
        showAlert.showAlert("Log out successfully!", "success");
        sessionService.clearWhenLogout();
        FXMLResolver.getInstance().renderScene("auth/login");
    }

    private void setLabelInfo() {
        homeController.usernameLabelUserInfoBox.setText(user.getName());
        homeController.emailLabelUserInfoBox.setText(user.getEmail());
    }

    private void setDefault() {
        homeController.userInfoBox.setVisible(false);
    }

    private void GetUserInfo() {
        authenticationService = new AuthenticationService(new SessionService(), new UserService(new UserRepository()));
        try {
            user = authenticationService.getCurrentUser();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
