package app.controller.admin.UserTab;

public class AllSetUp {
    void init_function(MainUserController userCtrl) {
        new CancelDataController(userCtrl).init();
        new DeleteUserController(userCtrl).init();
        new InsertUserController(userCtrl).init();
        new UpdateUserController(userCtrl).init();
        new ImportDataController(userCtrl).init();
        new SearchUserController(userCtrl).init();
    }
}
