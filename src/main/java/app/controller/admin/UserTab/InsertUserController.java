package app.controller.admin.UserTab;

import app.domain.User;

public class InsertUserController {
    final MainUserController mainUserCtrl;

    public InsertUserController(MainUserController mainUserCtrl) {
        this.mainUserCtrl = mainUserCtrl;
    }

    void init() {
        mainUserCtrl.insertButton.setOnAction(e -> addNewUser());
    }

    void addNewUser() {
        User user = mainUserCtrl.getUser();

        if (user == null) {
            return;
        }

        if (mainUserCtrl.userService.handleSaveUser(user)) {
            mainUserCtrl.clearTextFields();
            mainUserCtrl.listUser.add(user);
            mainUserCtrl.showAlert.showAlert("Add new user successed!", "success");
        } else {
            mainUserCtrl.showAlert.showAlert("Add new user failed!", "error");
        }

    }
}
