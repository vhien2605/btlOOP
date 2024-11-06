package app.controller.admin.AllIssueBookTab;

public class FilterByStatusController {
    final MainAllIssueController mainAllIssueCtrl;

    public FilterByStatusController(MainAllIssueController mainAllIssueCtrl) {
        this.mainAllIssueCtrl = mainAllIssueCtrl;
    }

    void init() {
        mainAllIssueCtrl.allButton.setOnAction(e -> getAll());
        mainAllIssueCtrl.pendingButton.setOnAction(e -> getPending());
        mainAllIssueCtrl.borrowedButton.setOnAction(e -> getBorrowed());
        mainAllIssueCtrl.returnedButton.setOnAction(e -> getReturned());
    }

    void getAll() {
        mainAllIssueCtrl.getAll();
    }

    void getPending() {
        mainAllIssueCtrl.getPending();
    }

    void getBorrowed() {
        mainAllIssueCtrl.getBorrowed();
    }

    void getReturned() {
        mainAllIssueCtrl.getReturned();
    }
}
