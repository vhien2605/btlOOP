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
        if (!mainBookLoanCtrl.updateDataBorrowReport()) {
            return;
        }

        if (mainBookLoanCtrl.reportService.handleUpdateOne(mainBookLoanCtrl.borrowReport)) {
            mainBookLoanCtrl.showAlert.showAlert("Updated successfully!", "success");
        } else {
            mainBookLoanCtrl.showAlert.showAlert("Update failed!", "error");
        }
    }
}
