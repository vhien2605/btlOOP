package app.controller.admin.BookLoanTab;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import app.controller.helper.BookLoanHelper;
import app.controller.helper.SendMailHelper;
import app.domain.Book;
import app.domain.BorrowReport;
import app.domain.User;

public class UpdateIssueController {
    final MainBookLoanController mainBookLoanCtrl;

    private String oldStatus;
    private String newStatus;

    public UpdateIssueController(MainBookLoanController mainBookLoanCtrl) {
        this.mainBookLoanCtrl = mainBookLoanCtrl;
    }

    void init() {
        mainBookLoanCtrl.updateButton.setOnAction(e -> updateIssue());
    }

    void updateIssue() {
        if (!updateDataBorrowReport()) {
            return;
        }

        boolean success = false;

        if (oldStatus.equals(newStatus)) {
            success = mainBookLoanCtrl.reportService.handleUpdateOne(mainBookLoanCtrl.borrowReport);
        } else {
            Book book = getNewBookAfterUpdateReport(oldStatus, newStatus);
            BorrowReport report = mainBookLoanCtrl.borrowReport;

            if (book != null && mainBookLoanCtrl.reportService.updateReportAndBookTransaction(report, book)) {
                success = true;
                sendMail();
            }
        }

        if (success) {
            mainBookLoanCtrl.showAlert.showAlert("Updated successfully!", "success");
        } else {
            mainBookLoanCtrl.showAlert.showAlert("Update failed!", "error");
        }
    }

    boolean updateDataBorrowReport() {
        if (!mainBookLoanCtrl.validate()) {
            return false;
        }

        LocalDate borrowDate = mainBookLoanCtrl.borrowDateTextFiled.getValue();
        if (borrowDate != null) {
            mainBookLoanCtrl.borrowReport.setBorrowDate(borrowDate.format(DateTimeFormatter.ISO_LOCAL_DATE));
        }

        LocalDate dueDate = mainBookLoanCtrl.dueDateTextFiled.getValue();
        if (dueDate != null) {
            mainBookLoanCtrl.borrowReport.setExpectedReturnDate(dueDate.format(DateTimeFormatter.ISO_LOCAL_DATE));
        }

        LocalDate returnDate = mainBookLoanCtrl.returnDateTextFiled.getValue();
        if (returnDate != null) {
            mainBookLoanCtrl.borrowReport.setReturnDate(returnDate.format(DateTimeFormatter.ISO_LOCAL_DATE));
        } else {
            mainBookLoanCtrl.borrowReport.setReturnDate(null);
        }

        oldStatus = mainBookLoanCtrl.borrowReport.getStatus();
        newStatus = mainBookLoanCtrl.changeStatusButton.getText();

        mainBookLoanCtrl.borrowReport.setStatus(newStatus);

        return true;
    }

    private Book getNewBookAfterUpdateReport(String oldStatus, String newStatus) {
        Book book = mainBookLoanCtrl.bookService.findByISBN(mainBookLoanCtrl.borrowReport.getBookId());
        if (book == null) {
            return null;
        }

        if (oldStatus.equals(BorrowReport.PENDING) && newStatus.equals(BorrowReport.BORROWED)) {
            book.setBookRemaining(book.getBookRemaining() - 1);
        } else if (oldStatus.equals(BorrowReport.BORROWED) && newStatus.equals(BorrowReport.RETURNED)) {
            book.setBookRemaining(book.getBookRemaining() + 1);
        }

        return book;
    }

    private void sendMail() {
        if (oldStatus.equals(BorrowReport.PENDING) && newStatus.equals(BorrowReport.BORROWED)) {
            String userId = mainBookLoanCtrl.borrowReport.getUserId();
            User user = mainBookLoanCtrl.userService.findById(userId);

            if (user == null) {
                return;
            }

            MainBookLoanController controller = BookLoanHelper
                    .getBookLoanController(mainBookLoanCtrl.borrowReport);

            if (controller == null) {
                return;
            }

            SendMailHelper.sendMail(controller, user.getEmail());
        }
    }
}
