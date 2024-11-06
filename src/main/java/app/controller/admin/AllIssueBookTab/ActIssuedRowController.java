package app.controller.admin.AllIssueBookTab;

import app.config.ViewConfig.FXMLResolver;
import app.controller.admin.BookLoanTab.MainBookLoanController;

public class ActIssuedRowController {
    final MainIssuedRowController mainIssueRowCtrl;

    public ActIssuedRowController(MainIssuedRowController mainIssuedRowCtrl) {
        this.mainIssueRowCtrl = mainIssuedRowCtrl;
    }

    void init() {
        mainIssueRowCtrl.detailButton.setOnAction(e -> renderDetail());
        mainIssueRowCtrl.updateButton.setOnAction(e -> updateIssue());
        mainIssueRowCtrl.deleteButton.setOnAction(e -> deleteIssue());
    }

    private void renderDetail() {
        String currentPath = "allIssueBookTab/all_issuebook_tab";

        FXMLResolver resolver = FXMLResolver.getInstance();
        resolver.renderScene("bookLoanTab/bookloan_tab");

        MainBookLoanController bookLoanController = resolver.getLoader().getController();
        bookLoanController.renderData(mainIssueRowCtrl.borrowReport, currentPath);
    }

    private void updateIssue() {
        if (!mainIssueRowCtrl.updateDataBorrowReport()) {
            return;
        }

        if (mainIssueRowCtrl.reportService.handleUpdateOne(mainIssueRowCtrl.borrowReport)) {
            mainIssueRowCtrl.showAlert.showAlert("Updated successfully!", "success");
            mainIssueRowCtrl.mainAllIssueCtrl.resetData();
        } else {
            mainIssueRowCtrl.showAlert.showAlert("Update failed!", "error");
        }
    }

    void deleteIssue() {
        int id = mainIssueRowCtrl.borrowReport.getId();

        if (mainIssueRowCtrl.reportService.handleDeleteById(id)) {
            mainIssueRowCtrl.showAlert.showAlert("Deleted successfully!", "success");
            mainIssueRowCtrl.mainAllIssueCtrl.resetData();
        } else {
            mainIssueRowCtrl.showAlert.showAlert("Delete failed!", "error");
        }
    }
}
