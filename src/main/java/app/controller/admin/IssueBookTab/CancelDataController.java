package app.controller.admin.IssueBookTab;

public class CancelDataController {
    final MainIssueController mainIssueCtrl;

    public CancelDataController(MainIssueController mainIssueCtrl) {
        this.mainIssueCtrl = mainIssueCtrl;
    }

    void init() {
        mainIssueCtrl.cancelButton.setOnAction(e -> cancelData());
    }

    private void cancelData() {
        mainIssueCtrl.clearFields();
    }
}
