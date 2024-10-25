package app.controller.admin.UserTab;

import app.domain.User;

public class DeleteButtonCtrl {
    final MainUserController mainUserCtrl;

    public DeleteButtonCtrl(MainUserController mainUserCtrl) {
        this.mainUserCtrl = mainUserCtrl;
    }

    void init() {
        mainUserCtrl.deleteButton.setOnAction(e -> deleteUser());
    }

    private void deleteUser() {
        User user = mainUserCtrl.getUser();

        if (user == null) {
            return;
        }

        String id = user.getId();
        if (mainUserCtrl.userService.deleteUser(id)) {
            for (int i = 0; i < mainUserCtrl.listUser.size(); i++) {
                if (mainUserCtrl.listUser.get(i).getId().equals(id)) {
                    mainUserCtrl.listUser.remove(i);
                    break;
                }
            }
            mainUserCtrl.clearTextFields();
            mainUserCtrl.showAlert.showAlert("Delete user successed!", "success");
        } else {
            mainUserCtrl.showAlert.showAlert("Delete user failed!", "error");
        }
    }
}
