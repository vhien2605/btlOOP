package app.controller.StudentTab;

import app.controller.BaseController;
import app.domain.Student;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class MainStudentController implements BaseController {

    @FXML
    TableView<Student> studentTableView;

    @FXML
    TableColumn<Student, Integer> studentIdCol;

    @FXML
    TableColumn<Student, String> studentNameCol;

    @FXML
    TableColumn<Student, String> phoneNumberCol;

    @FXML
    TableColumn<Student, String> studentEmailCol;

    @FXML
    TableColumn<Student, String> studentAddressCol;

    @FXML
    TextField studentIdTextField, studentNameTextField, phoneNumberTextField, studentEmailTextField,
            studentAddressTextField, searchStudentTextField;

    @FXML
    Button cencelButton, insertButton, updateButton, deleteButton, importDataButton;

    @FXML
    private void handleButtonAction(ActionEvent e) {
        if (e.getSource() == cencelButton) {
            System.out.println("click button cencel");
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
