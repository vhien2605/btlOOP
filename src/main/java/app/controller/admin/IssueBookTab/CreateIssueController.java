package app.controller.admin.IssueBookTab;

import app.config.ViewConfig.FXMLResolver;
import app.controller.admin.BookLoanTab.MainBookLoanController;
import app.controller.helper.SendMailHelper;
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

        if (mainIssueCtrl.bookInfo.getBookRemaining() <= 0) {
            mainIssueCtrl.showAlert.showAlert("The number of books left is not enough!", "error");
            return;
        } else {
            mainIssueCtrl.bookInfo.setBookRemaining(mainIssueCtrl.bookInfo.getBookRemaining() - 1);
            if (!mainIssueCtrl.bookService.handleUpdateOne(mainIssueCtrl.bookInfo)) {
                mainIssueCtrl.showAlert.showAlert("Create fail new borrow report!", "error");
                return;
            }
        }

        if (mainIssueCtrl.reportService.handleSave(data)) {
            createQRImage(data);

            mainIssueCtrl.showAlert.showAlert("Create success new borrow report!", "success");

            String currentPath = "admin/issueBookTab/issuebook_tab";
            FXMLResolver resolver = FXMLResolver.getInstance();
            resolver.renderScene("admin/bookLoanTab/bookloan_tab");

            MainBookLoanController bookLoanController = resolver.getLoader().getController();
            bookLoanController.renderData(data, currentPath);

            String email = mainIssueCtrl.userInfo.getEmail();
            SendMailHelper.sendMail(bookLoanController, email);
        } else {
            mainIssueCtrl.showAlert.showAlert("Create fail new borrow report !", "error");
        }

    }

    private void createQRImage(BorrowReport data) {
        String path = mainIssueCtrl.fileService.createQRImage(data, "QRcode");
        if (path != null) {
            data.setQrcodeUrl(path);
            mainIssueCtrl.reportService.handleUpdateOne(data);
        } else {
            System.out.println("file service return path null");
        }
    }
}
