package app.controller.user.HomePage;

public class ViewProfileController {
    private MainHomePageController homeController;

    protected ViewProfileController(MainHomePageController mainHomePageController) {
        this.homeController = mainHomePageController;
    }

    public void handleBackButton() {
        homeController.ViewUserProfilePage.setVisible(false);
        homeController.userInfoBox.setVisible(false);
    }

    public void handleViewProfilePage() {
        homeController.ViewUserProfilePage.setVisible(true);
    }

    private void renderData() {
        homeController.userProfileName.setText(MainHomePageController.user.getName());
        homeController.userProfileUsername.setText(MainHomePageController.user.getUsername());
        homeController.userProfileAddress.setText(MainHomePageController.user.getAddress());
        homeController.userProfileEmail.setText(MainHomePageController.user.getEmail());
        homeController.userProfilePhoneNumber.setText(MainHomePageController.user.getPhoneNumber());
    }

    public void initialize() {
        homeController.ViewUserProfilePage.setVisible(false);
        renderData();
    }

}
