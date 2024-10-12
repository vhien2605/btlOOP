package app.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class DashBoardController implements BaseController {
    @FXML
    private Label headerLabel;
    @FXML
    private TableView tableView;

    @FXML
    private TextField textFieldRight1;
    @FXML
    private TextField textFieldRight2;
    @FXML
    private TextField textFieldRight3;
    @FXML
    private TextField textFieldRight4;

    @FXML
    private Label informationLabel;

    @FXML
    private Button cancelButton;
    @FXML
    private Button saveButton;

    @FXML
    @Override
    public void initialize() {

    }
}
