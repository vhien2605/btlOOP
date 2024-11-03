package app.controller.admin.BookLoanTab;

public class AllSetup {
    void init_function(MainBookLoanController bookLoanCtrl) {
        new ExportDataController(bookLoanCtrl).init();
        new UpdateIssueController(bookLoanCtrl).init();
    }
}
