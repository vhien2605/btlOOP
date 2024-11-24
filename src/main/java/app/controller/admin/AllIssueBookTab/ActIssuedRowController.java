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

    private void renderDetail() {
        String currentPath = "admin/allIssueBookTab/all_issuebook_tab";

        FXMLResolver resolver = FXMLResolver.getInstance();
        resolver.renderScene("admin/bookLoanTab/bookloan_tab");

        MainBookLoanController bookLoanController = resolver.getLoader().getController();
        bookLoanController.renderData(mainIssueRowCtrl.borrowReport, currentPath);
    }

    private void updateIssue() {
        if (!updateDataBorrowReport()) {
            return;
        }

        if (mainIssueRowCtrl.reportService.handleUpdateOne(mainIssueRowCtrl.borrowReport)) {
            mainIssueRowCtrl.showAlert.showAlert("Updated successfully!", "success");
            mainIssueRowCtrl.mainAllIssueCtrl.resetData();

            // Send mail
            if (oldStatus.equals(BorrowReport.PENDING) && newStatus.equals(BorrowReport.BORROWED)) {
                String userId = mainIssueRowCtrl.borrowReport.getUserId();
                User user = mainIssueRowCtrl.userService.findById(userId);
                if (user != null) {
                    MainBookLoanController controller = BookLoanHelper
                            .getBookLoanController(mainIssueRowCtrl.borrowReport);
                    if (controller != null) {
                        SendMailHelper.sendMail(controller, user.getEmail());
                    }
                }
            }
        } else {
            mainIssueRowCtrl.showAlert.showAlert("Update failed!", "error");
        }
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

        if (!oldStatus.equals(newStatus)) {
            mainIssueRowCtrl.borrowReport.setStatus(newStatus);
            return updateBookQuantity(oldStatus, newStatus);
        }

        return true;
    }

    private Boolean updateBookQuantity(String oldStatus, String newStatus) {
        Book book = mainIssueRowCtrl.bookService.findByISBN(mainIssueRowCtrl.borrowReport.getBookId());
        if (book == null) {
            return false;
        }

        if (oldStatus.equals(BorrowReport.PENDING) && newStatus.equals(BorrowReport.BORROWED)) {
            book.setBookRemaining(book.getBookRemaining() - 1);
            return mainIssueRowCtrl.bookService.handleUpdateOne(book);
        } else if (oldStatus.equals(BorrowReport.BORROWED) && newStatus.equals(BorrowReport.RETURNED)) {
            book.setBookRemaining(book.getBookRemaining() + 1);
            return mainIssueRowCtrl.bookService.handleUpdateOne(book);
        }

        return true;
    }

}
