package app.controller.admin.IssueBookTab;

import app.config.ViewConfig.FXMLResolver;
import app.controller.admin.BookLoanTab.MainBookLoanController;
import app.domain.BorrowReport;

public class CreateIssueController {
    final MainIssueController mainIssueCtrl;

    public CreateIssueController(MainIssueController mainIssueCtrl) {
        this.mainIssueCtrl = mainIssueCtrl;
    }

    void init() {
        mainIssueCtrl.issueBookButton.setOnAction(e -> createIssue());
    }

    private void createIssue() {
        BorrowReport data = mainIssueCtrl.getData();

        if (data == null) {
            return;
        }

        if (mainIssueCtrl.reportService.handleSave(data)) {
            String currentPath = "issueBookTab/issuebook_tab";
            FXMLResolver resolver = FXMLResolver.getInstance();
            resolver.renderScene("bookLoanTab/bookloan_tab");

            mainIssueCtrl.showAlert.showAlert("Create success new borrow report!", "success");

            MainBookLoanController bookLoanController = resolver.getLoader().getController();
            bookLoanController.renderData(data, currentPath);
        } else {
            mainIssueCtrl.showAlert.showAlert("Create fail new borrow report !", "error");
        }

    }
}
