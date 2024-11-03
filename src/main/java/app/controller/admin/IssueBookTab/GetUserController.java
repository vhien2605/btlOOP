package app.controller.admin.IssueBookTab;

import app.domain.User;

public class GetUserController {
    final MainIssueController mainIssueCtrl;

    public GetUserController(MainIssueController mainIssueCtrl) {
        this.mainIssueCtrl = mainIssueCtrl;
    }

    void init() {
        mainIssueCtrl.findUserButton.setOnAction(e -> getUserInfo());
    }

    private void getUserInfo() {
        String userId = mainIssueCtrl.getUserId();

        if (userId.isEmpty()) {
            mainIssueCtrl.showAlert.showAlert("Please enter user id!", "error");
            return;
        }

        User user = mainIssueCtrl.userService.findById(userId);

        if (user == null) {
            mainIssueCtrl.showAlert.showAlert("User not found!", "error");
            return;
        }

        mainIssueCtrl.setUserInfo(user);
    }
}
