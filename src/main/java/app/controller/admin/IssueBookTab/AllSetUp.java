package app.controller.admin.IssueBookTab;

public class AllSetUp {
    void init_function(MainIssueController issueCtrl) {
        new CreateIssueController(issueCtrl).init();
        new GetBookController(issueCtrl).init();
        new GetUserController(issueCtrl).init();
        new CancelDataController(issueCtrl).init();
    }
}
