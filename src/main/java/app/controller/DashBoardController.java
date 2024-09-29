package app.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class DashBoardController {
    @FXML
    Label headerLabel;
    @FXML
    TableView tableView;

    @FXML
    TextField textFieldRight1;
    @FXML
    TextField textFieldRight2;
    @FXML
    TextField textFieldRight3;
    @FXML
    TextField textFieldRight4;

    @FXML
    Label informationLabel;

    @FXML
    Button cancelButton;
    @FXML
    Button saveButton;
}
