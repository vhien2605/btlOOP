package app.controller.admin.BookLoanTab;

public class UpdateIssueController {
    final MainBookLoanController mainBookLoanCtrl;

    public UpdateIssueController(MainBookLoanController mainBookLoanCtrl) {
        this.mainBookLoanCtrl = mainBookLoanCtrl;
    }

    void init() {
        mainBookLoanCtrl.updateButton.setOnAction(e -> updateIssue());
    }

    void updateIssue() {
        System.out.println("Click button update");
    }
}
