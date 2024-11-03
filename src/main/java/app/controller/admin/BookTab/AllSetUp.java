package app.controller.admin.BookTab;

public class AllSetUp {
    void init_function(MainBookController bookCtrl) {
        new DeleteBookController(bookCtrl).init();
        new UpdateBookControllerSetup(bookCtrl).init();
        new CreateBookControllerSetup(bookCtrl).init();
    }
}
