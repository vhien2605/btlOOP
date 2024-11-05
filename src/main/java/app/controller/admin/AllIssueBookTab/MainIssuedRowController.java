package app.controller.admin.AllIssueBookTab;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import app.controller.BaseController;
import app.domain.BorrowReport;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;

public class MainIssuedRowController implements BaseController {

    @FXML
    Label idLabel, userIdLabel, bookIdLabel;

    @FXML
    ChoiceBox<String> statusChoiceBox;

    @FXML
    Button detailButton, updateButton, deleteButton;

    @FXML
    DatePicker borrowDatePicker, dueDatePicker, returnDatePicker;

    BorrowReport borrowReport;

    public static final String PENDING_APPROVAL = "Pending appproval";
    public static final String BORROWED = "Borrowed";
    public static final String RETURNED = "Returned";

    @Override
    public void initialize() {

    }

    public void loadData(BorrowReport data) {
        this.borrowReport = data;
        setUpdata();
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

        statusChoiceBox.getItems().addAll(PENDING_APPROVAL, BORROWED, RETURNED);
        statusChoiceBox.setValue(borrowReport.getStatus());
    }

}
