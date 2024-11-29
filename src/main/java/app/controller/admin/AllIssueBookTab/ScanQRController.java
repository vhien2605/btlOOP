package app.controller.admin.AllIssueBookTab;

import java.io.File;

import app.config.ViewConfig.FXMLResolver;
import app.controller.admin.BookLoanTab.MainBookLoanController;
import app.domain.BorrowReport;
import javafx.stage.FileChooser;

public class ScanQRController {
    final MainAllIssueController mainAllIssueCtrl;

    public ScanQRController(MainAllIssueController mainAllIssueCtrl) {
        this.mainAllIssueCtrl = mainAllIssueCtrl;
    }

    void init() {
        mainAllIssueCtrl.scanQRButton.setOnAction(e -> scanAndDisplayQRCode());
    }

    private void scanAndDisplayQRCode() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters()
                .add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));
        File selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile != null) {
            BorrowReport data = mainAllIssueCtrl.fileService.scanQRCode(selectedFile);

            if (data == null) {
                mainAllIssueCtrl.showAlert.showAlert("No data found!", "error");
                return;
            }

            // Tìm đúng data tương ứng với id
            int id = data.getId();
            BorrowReport borrowReport = mainAllIssueCtrl.reportService.findById(id);

            if (borrowReport == null) {
                mainAllIssueCtrl.showAlert.showAlert("No data found!", "error");
                return;
            }

            displayData(borrowReport);
        }
    }

    private void displayData(BorrowReport borrowReport) {
        String currentPath = "admin/allIssueBookTab/all_issuebook_tab";

        FXMLResolver resolver = FXMLResolver.getInstance();
        resolver.renderScene("admin/bookLoanTab/bookloan_tab");

        MainBookLoanController bookLoanController = resolver.getLoader().getController();
        bookLoanController.renderData(borrowReport, currentPath);
    }

}
