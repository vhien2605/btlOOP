package app.controller.admin.UserTab;

public class CancelDataController {
    final MainUserController mainUserCtrl;

    public CancelDataController(MainUserController mainUserCtrl) {
        this.mainUserCtrl = mainUserCtrl;
    }

    void init() {
        mainUserCtrl.cancelButton.setOnAction(e -> cancel());
    }

    void cancel() {
        mainUserCtrl.clearTextFields();
    }
}
