package app.controller.admin.UserTab;


public class DeleteUserController {
    final MainUserController mainUserCtrl;

    public DeleteUserController(MainUserController mainUserCtrl) {
        this.mainUserCtrl = mainUserCtrl;
    }

    void init() {
        mainUserCtrl.deleteButton.setOnAction(e -> deleteUser());
    }

    private void deleteUser() {
        String id = mainUserCtrl.userIdTextField.getText();

        if (id.isEmpty()) {
            mainUserCtrl.showAlert.showAlert("Thông tin user không hợp lệ!", "error");
            return;
        }

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
