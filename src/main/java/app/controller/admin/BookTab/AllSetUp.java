package app.controller.admin.BookTab;

public class AllSetUp {
    void init_function(MainBookController bookCtrl) {
        new DeleteBookButtonCtrl(bookCtrl).init();
        new UpdateBookButtonCtrl(bookCtrl).init();
        new CreateBookButtonCtrl(bookCtrl).init();
    }
}
