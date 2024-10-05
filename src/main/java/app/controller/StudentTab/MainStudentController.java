package app.controller.StudentTab;

import app.controller.BaseController;
import app.domain.Student;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class MainStudentController implements BaseController {

    @FXML
    TableView studentTableView;

    @FXML
    TableColumn<Student, Integer> idColumn;

    @FXML
    TableColumn<Student, String> nameColumn;

    @FXML
    TableColumn<Student, String> addressColumn;

    @FXML
    TableColumn<Student, String> emailColumn;

    @FXML
    TableColumn<Student, String> phoneNumberColumn;
    @FXML
    TextField idTextField;
    @FXML
    TextField nameTextField;
    @FXML
    TextField addressTextField;
    @FXML
    TextField emailTextField;
    @FXML
    TextField phoneTextField;

    @FXML
    Button addButton;
    @FXML
    Button deleteButton;
    @FXML
    Button updateButton;
    @FXML
    Button deleteAllButton;
    @FXML
    TextField searchTextField;
    @FXML
    Button searchButton;
    @FXML
    Label studentManageLabel;

    public void studentAddButton() {
        System.out.println(1);
    }

    public void studentUpdateButton() {
        System.out.println(2);
    }

    public void studentDeleteButton() {
        System.out.println(3);
    }

    public void studentDeleteAllButton() {
        System.out.println(4);
    }

    public void handleSearchButton() {
        System.out.println(5);
    }

    @Override
    public void initialize() {

    }
}
