package app.controller.admin.AllIssueBookTab;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import app.config.ViewConfig.FXMLResolver;
import app.controller.admin.BookLoanTab.MainBookLoanController;
import app.controller.helper.BookLoanHelper;
import app.controller.helper.SendMailHelper;
import app.domain.Book;
import app.domain.BorrowReport;
import app.domain.User;

public class ActIssuedRowController {
    final MainIssuedRowController mainIssueRowCtrl;

    private String oldStatus;
    private String newStatus;

    public ActIssuedRowController(MainIssuedRowController mainIssuedRowCtrl) {
        this.mainIssueRowCtrl = mainIssuedRowCtrl;
    }

    void init() {
        mainIssueRowCtrl.detailButton.setOnAction(e -> renderDetail());
        mainIssueRowCtrl.updateButton.setOnAction(e -> updateIssue());
        mainIssueRowCtrl.deleteButton.setOnAction(e -> deleteIssue());
    }

    void renderDetail() {
        String currentPath = "admin/allIssueBookTab/all_issuebook_tab";

        FXMLResolver resolver = FXMLResolver.getInstance();
        resolver.renderScene("admin/bookLoanTab/bookloan_tab");

        MainBookLoanController bookLoanController = resolver.getLoader().getController();
        bookLoanController.renderData(mainIssueRowCtrl.borrowReport, currentPath);
    }

    void updateIssue() {
        if (!updateDataBorrowReport()) {
            return;
        }

        boolean success = false;
        BorrowReport copyBorrowReport = new BorrowReport(mainIssueRowCtrl.borrowReport);

        if (oldStatus.equals(newStatus)) {
            success = mainIssueRowCtrl.reportService.handleUpdateOne(mainIssueRowCtrl.borrowReport);
        } else {
            Book book = mainIssueRowCtrl.bookService.findByISBN(mainIssueRowCtrl.borrowReport.getBookId());
            BorrowReport report = mainIssueRowCtrl.borrowReport;

            if (book == null) {
                showResult(success, copyBorrowReport);
                return;
            }

            if (book.getBookRemaining() <= 0) {
                mainIssueRowCtrl.showAlert.showAlert("The number of books left is not enough!", "error");
                return;
            }

            updateBookAfterUpdateReport(book);

            if (mainIssueRowCtrl.reportService.updateReportAndBookTransaction(report, book)) {
                success = true;
                sendMail();
            }
        }

        showResult(success, copyBorrowReport);
    }

    void deleteIssue() {
        if (mainIssueRowCtrl.borrowReport.getStatus().equals(BorrowReport.BORROWED)) {
            mainIssueRowCtrl.showAlert.showAlert("You cannot delete while it is in borrowed state!", "error");
            return;
        }

        int id = mainIssueRowCtrl.borrowReport.getId();

        if (mainIssueRowCtrl.reportService.handleDeleteById(id)) {
            mainIssueRowCtrl.showAlert.showAlert("Deleted successfully!", "success");
            mainIssueRowCtrl.mainAllIssueCtrl.resetData();
        } else {
            mainIssueRowCtrl.showAlert.showAlert("Delete failed!", "error");
        }
    }

    boolean updateDataBorrowReport() {
        if (!mainIssueRowCtrl.validate()) {
            return false;
        }

        LocalDate borrowDate = mainIssueRowCtrl.borrowDatePicker.getValue();
        if (borrowDate != null) {
            mainIssueRowCtrl.borrowReport.setBorrowDate(borrowDate.format(DateTimeFormatter.ISO_LOCAL_DATE));
        }

        LocalDate dueDate = mainIssueRowCtrl.dueDatePicker.getValue();
        if (dueDate != null) {
            mainIssueRowCtrl.borrowReport.setExpectedReturnDate(dueDate.format(DateTimeFormatter.ISO_LOCAL_DATE));
        }

        LocalDate returnDate = mainIssueRowCtrl.returnDatePicker.getValue();
        if (returnDate != null) {
            mainIssueRowCtrl.borrowReport.setReturnDate(returnDate.format(DateTimeFormatter.ISO_LOCAL_DATE));
        } else {
            mainIssueRowCtrl.borrowReport.setReturnDate(null);
        }

        oldStatus = mainIssueRowCtrl.borrowReport.getStatus();
        newStatus = mainIssueRowCtrl.changeStatusButton.getText();

        mainIssueRowCtrl.borrowReport.setStatus(newStatus);

        return true;
    }

    private void updateBookAfterUpdateReport(Book book) {
        if (oldStatus.equals(BorrowReport.PENDING) && newStatus.equals(BorrowReport.BORROWED)) {
            book.setBookRemaining(book.getBookRemaining() - 1);
        } else if (oldStatus.equals(BorrowReport.BORROWED) && newStatus.equals(BorrowReport.RETURNED)) {
            book.setBookRemaining(book.getBookRemaining() + 1);
        }
    }

    private void sendMail() {
        if (oldStatus.equals(BorrowReport.PENDING) && newStatus.equals(BorrowReport.BORROWED)) {
            String userId = mainIssueRowCtrl.borrowReport.getUserId();
            User user = mainIssueRowCtrl.userService.findById(userId);

            if (user == null) {
                return;
            }

            MainBookLoanController controller = BookLoanHelper
                    .getBookLoanController(mainIssueRowCtrl.borrowReport);

            if (controller == null) {
                return;
            }

            SendMailHelper.sendMail(controller, user.getEmail());
        }
    }

    void showResult(boolean success, BorrowReport copyBorrowReport) {
        if (success) {
            mainIssueRowCtrl.showAlert.showAlert("Updated successfully!", "success");
            mainIssueRowCtrl.mainAllIssueCtrl.resetData();
        } else {
            mainIssueRowCtrl.showAlert.showAlert("Update failed!", "error");
            mainIssueRowCtrl.borrowReport = copyBorrowReport;
        }
    }

}
