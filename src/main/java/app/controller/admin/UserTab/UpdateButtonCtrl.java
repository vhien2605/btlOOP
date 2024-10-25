package app.controller.admin.UserTab;

import app.domain.User;

public class UpdateButtonCtrl {
    final MainUserController mainUserCtrl;

    public UpdateButtonCtrl(MainUserController mainUserCtrl) {
        this.mainUserCtrl = mainUserCtrl;
    }

    void init() {
        mainUserCtrl.updateButton.setOnAction(e -> updateUser());
    }

    void updateUser() {
        User user = mainUserCtrl.getUser();

        if (user == null) {
            return;
        }

        if (mainUserCtrl.userService.handleUpdateOne(user)) {
            for (int i = 0; i < mainUserCtrl.listUser.size(); i++) {
                if (mainUserCtrl.listUser.get(i).getId().equals(user.getId())) {
                    mainUserCtrl.listUser.set(i, user);
                    break;
                }
            }
            mainUserCtrl.showAlert.showAlert("Update user successed!", "success");
        } else {
            mainUserCtrl.showAlert.showAlert("Update user failed!", "error");
        }
    }
}
