package app.controller.admin.UserTab;

public class AllSetUp {
    void init_function(MainUserController userCtrl) {
        new CancelButtonCtrl(userCtrl).init();
        new DeleteButtonCtrl(userCtrl).init();
        new InsertButtonCtrl(userCtrl).init();
        new UpdateButtonCtrl(userCtrl).init();
        new ImportDataButtonCtrl(userCtrl).init();
    }
}
