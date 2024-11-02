package app.controller.admin.IssueBookTab;

import app.repository.BookRepository;
import app.repository.UserRepository;
import app.service.mainService.BookService;
import app.service.mainService.UserService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class MainIssueController {

    @FXML
    private TextField bookISBNTextFiled, userIdTextFiled, bookNameTextFiled,
            bookAuthorTextFiled, bookPublisherTextFiled, bookCategoryTextFiled,
            fullNameTextFiled, phoneNumberTextFiled, emailTextFiled, addressTextFiled;

    @FXML
    private DatePicker borrowDateTextFiled, dueDateTextFIled;

    @FXML
    private Button findBookButton, findUserButton, cancelButton, issueBookButton;

    BookService bookService;

    UserService userService;

    public void initialize() {
        bookService = new BookService(new BookRepository());
        userService = new UserService(new UserRepository());
    }

    @FXML
    private void handleCancel() {
        clearFields();
    }

    @FXML
    private void clearFields() {
        bookISBNTextFiled.clear();
        userIdTextFiled.clear();
        bookNameTextFiled.clear();
        bookAuthorTextFiled.clear();
        bookPublisherTextFiled.clear();
        bookCategoryTextFiled.clear();
        fullNameTextFiled.clear();
        phoneNumberTextFiled.clear();
        emailTextFiled.clear();
        addressTextFiled.clear();

        borrowDateTextFiled.setValue(null);
        dueDateTextFIled.setValue(null);
    }

}
