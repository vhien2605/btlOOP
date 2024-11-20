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
            BorrowReport borrowReport = mainAllIssueCtrl.fileService.scanQRCode(selectedFile);
            displayData(borrowReport);
        } else {
            System.out.println("loi");
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
