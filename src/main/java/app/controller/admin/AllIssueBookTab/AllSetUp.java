package app.controller.admin.AllIssueBookTab;

public class AllSetUp {
    void initMainIssueRowCtrl(MainIssuedRowController mainIssuedRowCtrl) {
        new ActIssuedRowController(mainIssuedRowCtrl).init();
    }

    void initMainAllIssuedCtrl(MainAllIssueController mainAllIssueCtrl) {
        new FilterByStatusController(mainAllIssueCtrl).init();
        new SearchIssueController(mainAllIssueCtrl).init();
        new ScanQRController(mainAllIssueCtrl).init();
    }
}
