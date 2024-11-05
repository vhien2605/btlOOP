package app.controller.admin.AllIssueBookTab;

import app.controller.BaseController;
import app.domain.BorrowReport;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;

public class MainIssuedRowController implements BaseController {

    @FXML
    Label idLabel, userIdLabel, bookIdLabel, borrowDateLabel, dueDateLabel, returnDateLabel;

    @FXML
    ChoiceBox<String> statusChoiceBox;

    @FXML
    Button detailButton, updateButton, deleteButton;

    BorrowReport borrowReport;

    @Override
    public void initialize() {

    }

}
