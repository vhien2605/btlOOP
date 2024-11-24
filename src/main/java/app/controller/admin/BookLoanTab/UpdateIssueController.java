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

        if (mainBookLoanCtrl.reportService.handleUpdateOne(mainBookLoanCtrl.borrowReport)) {
            mainBookLoanCtrl.showAlert.showAlert("Updated successfully!", "success");

            // Send mail
            if (oldStatus.equals(BorrowReport.PENDING) && newStatus.equals(BorrowReport.BORROWED)) {
                String userId = mainBookLoanCtrl.borrowReport.getUserId();
                User user = mainBookLoanCtrl.userService.findById(userId);
                if (user != null) {
                    MainBookLoanController controller = BookLoanHelper
                            .getBookLoanController(mainBookLoanCtrl.borrowReport);
                    if (controller != null) {
                        SendMailHelper.sendMail(controller, user.getEmail());
                    }
                }
            }
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

        if (!oldStatus.equals(newStatus)) {
            mainBookLoanCtrl.borrowReport.setStatus(newStatus);
            return updateBookQuantity(oldStatus, newStatus);
        }

        return true;
    }

    private Boolean updateBookQuantity(String oldStatus, String newStatus) {
        Book book = mainBookLoanCtrl.bookService.findByISBN(mainBookLoanCtrl.borrowReport.getBookId());
        if (book == null) {
            return false;
        }

        if (oldStatus.equals(BorrowReport.PENDING) && newStatus.equals(BorrowReport.BORROWED)) {
            book.setBookRemaining(book.getBookRemaining() - 1);
            return mainBookLoanCtrl.bookService.handleUpdateOne(book);
        } else if (oldStatus.equals(BorrowReport.BORROWED) && newStatus.equals(BorrowReport.RETURNED)) {
            book.setBookRemaining(book.getBookRemaining() + 1);
            return mainBookLoanCtrl.bookService.handleUpdateOne(book);
        }

        return true;
    }
}
