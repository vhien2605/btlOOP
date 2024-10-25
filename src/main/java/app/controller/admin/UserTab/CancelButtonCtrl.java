package app.controller.admin.UserTab;

public class CancelButtonCtrl {
    final MainUserController mainUserCtrl;

    public CancelButtonCtrl(MainUserController mainUserCtrl) {
        this.mainUserCtrl = mainUserCtrl;
    }

    void init() {
        mainUserCtrl.cancelButton.setOnAction(e -> cancel());
    }

    void cancel() {
        mainUserCtrl.clearTextFields();
    }
}
