package app.controller.StudentTab;

import app.controller.BaseController;
import app.domain.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class MainStudentController implements BaseController {

    @FXML
    TableView<User> studentTableView;

    @FXML
    TableColumn<User, Integer> studentIdCol;

    @FXML
    TableColumn<User, String> studentNameCol;

    @FXML
    TableColumn<User, String> phoneNumberCol;

    @FXML
    TableColumn<User, String> studentEmailCol;

    @FXML
    TableColumn<User, String> studentAddressCol;

    @FXML
    TextField studentIdTextField, studentNameTextField, phoneNumberTextField, studentEmailTextField,
            studentAddressTextField, searchStudentTextField;

    @FXML
    Button cencelButton, insertButton, updateButton, deleteButton, importDataButton;

    @FXML
    private void handleButtonAction(ActionEvent e) {
        if (e.getSource() == cencelButton) {
            System.out.println("click button cancel");
        } else if (e.getSource() == insertButton) {
            System.out.println("click button insert");
        } else if (e.getSource() == updateButton) {
            System.out.println("click button update");
        } else if (e.getSource() == deleteButton) {
            System.out.println("click button delete");
        } else if (e.getSource() == importDataButton) {
            System.out.println("click button import data");
        }
    }

    @Override
    public void initialize() {

    }
}
