package app.controller.admin.AllIssueBookTab;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import app.controller.BaseController;
import app.controller.helper.ShowAlert;
import app.domain.BorrowReport;
import app.repository.BookRepository;
import app.repository.ReportRepository;
import app.service.mainService.BookService;
import app.service.mainService.ReportService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;

public class MainIssuedRowController implements BaseController {

    @FXML
    Label idLabel, userIdLabel, bookIdLabel;

    @FXML
    Button detailButton, updateButton, deleteButton, changeStatusButton;

    @FXML
    DatePicker borrowDatePicker, dueDatePicker, returnDatePicker;

    BorrowReport borrowReport;

    ReportService reportService;

    BookService bookService;

    ShowAlert showAlert;

    MainAllIssueController mainAllIssueCtrl;

    void setMainAllIssueController(MainAllIssueController mainAllIssueCtrl) {
        this.mainAllIssueCtrl = mainAllIssueCtrl;
    }

    @Override
    public void initialize() {
        showAlert = new ShowAlert();
        reportService = new ReportService(new ReportRepository(), null, null);
        bookService = new BookService(new BookRepository());
        new AllSetUp().initMainIssueRowCtrl(this);
    }

    public void loadData(BorrowReport data) {
        this.borrowReport = data;
        setUpdata();
        setChangeStatusButton();
    }

    void setChangeStatusButton() {
        String initialStatus = borrowReport.getStatus();
        changeStatusButton.setText(initialStatus);

        switch (initialStatus) {
            case BorrowReport.PENDING:
                changeStatusButton.setStyle("-fx-background-color: #32e544; ");
                break;
            case BorrowReport.BORROWED:
                changeStatusButton.setStyle("-fx-background-color: #6fd1ef; ");
                break;
            case BorrowReport.RETURNED:
                changeStatusButton.setStyle("-fx-background-color: #f0ad4e; ");
                break;
        }

        // Thêm sự kiện khi người dùng thay đổi giá trị button status
        changeStatusButton.setOnAction(event -> {
            String selectedStatus = changeStatusButton.getText();

            switch (selectedStatus) {
                case BorrowReport.PENDING:
                    changeStatusButton.setStyle("-fx-background-color: #6fd1ef; ");
                    changeStatusButton.setText(BorrowReport.BORROWED);
                    break;
                case BorrowReport.BORROWED:
                    changeStatusButton.setStyle("-fx-background-color:#f0ad4e ; ");
                    changeStatusButton.setText(BorrowReport.RETURNED);
                    break;
            }
        });
    }

    void setUpdata() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        if (borrowReport.getBorrowDate() != null) {
            LocalDate date = LocalDate.parse(borrowReport.getBorrowDate(), formatter);
            borrowDatePicker.setValue(date);
        }

        if (borrowReport.getExpectedReturnDate() != null) {
            LocalDate date = LocalDate.parse(borrowReport.getExpectedReturnDate(), formatter);
            dueDatePicker.setValue(date);
        }

        if (borrowReport.getReturnDate() != null) {
            LocalDate date = LocalDate.parse(borrowReport.getReturnDate(), formatter);
            returnDatePicker.setValue(date);
        }

        idLabel.setText(String.valueOf(borrowReport.getId()));
        userIdLabel.setText(borrowReport.getUserId());
        bookIdLabel.setText(borrowReport.getBookId());

    }

    boolean validate() {
        String status = changeStatusButton.getText();

        if (status.equals(BorrowReport.BORROWED)) {
            if (borrowDatePicker.getValue() == null || dueDatePicker.getValue() == null) {
                showAlert.showAlert("Borrow date and due date cannot be empty!", "error");
                return false;
            }
        }

        if (status.equals(BorrowReport.RETURNED)) {
            if (returnDatePicker.getValue() == null) {
                showAlert.showAlert("Invalid return date for status 'Returned'!", "error");
                return false;
            }
        }

        return true;
    }

}
