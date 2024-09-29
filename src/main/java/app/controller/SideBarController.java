package app.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class SideBarController implements BaseController {
    @FXML
    private Button homeButton;

    @FXML
    private Button booksButton;

    @FXML
    private Button studentsButton;

    @FXML
    private Button issueBooksButton;

    @FXML
    private Button returnBooksButton;

    @FXML
    private Button settingsButton;

    @FXML
    private Button closeButton;

    @FXML
    private void handleSideBarHome() {
        System.out.println(1);
    }

    @FXML
    private void handleSideBarBooks() {
        System.out.println(2);
    }

    @FXML
    private void handleSideBarStudents() {
        System.out.println(3);
    }

    @FXML
    private void handleSideBarIssueBooks() {
        System.out.println(4);
    }

    @FXML
    private void handleSideBarReturnBooks() {
        System.out.println(5);
    }

    @FXML
    private void handleSideBarSettings() {
        System.out.println(6);
    }

    @FXML
    private void handleSideBarClose() {
        System.out.println(7);
    }

    @Override
    public void initialize() {

    }
}
