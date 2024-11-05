package app.controller.admin.AllIssueBookTab;

public class AllSetUp {
    void initMainIssueRowCtrl(MainIssuedRowController mainIssuedRowCtrl) {
        new ActIssuedRowController(mainIssuedRowCtrl).init();
    }

}
