package app.controller.admin.IssueBookTab;

import app.domain.Book;
import app.domain.BorrowReport;
import app.domain.User;
import app.repository.BookRepository;
import app.repository.ReportRepository;
import app.repository.UserRepository;
import app.service.mainService.BookService;
import app.service.mainService.ReportService;
import app.service.mainService.UserService;

import java.time.format.DateTimeFormatter;

import app.controller.helper.ShowAlert;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class MainIssueController {

    @FXML
    TextField bookISBNTextFiled, userIdTextFiled, bookNameTextFiled,
            bookAuthorTextFiled, bookPublisherTextFiled, bookCategoryTextFiled,
            fullNameTextFiled, phoneNumberTextFiled, emailTextFiled, addressTextFiled;

    @FXML
    DatePicker borrowDateTextFiled, dueDateTextFIled;

    @FXML
    Button findBookButton, findUserButton, cancelButton, issueBookButton;

    BookService bookService;

    UserService userService;

    ReportService reportService;

    ShowAlert showAlert;

    Book bookInfo;

    public void initialize() {
        bookService = new BookService(new BookRepository());
        userService = new UserService(new UserRepository());
        reportService = new ReportService(new ReportRepository(), userService, bookService);
        showAlert = new ShowAlert();
        new AllSetUp().init_function(this);
    }

    @FXML
    public void clearFields() {
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

    public String getBookISBN() {
        return bookISBNTextFiled.getText();
    }

    public String getUserId() {
        return userIdTextFiled.getText();
    }

    public void setBookInfo(Book book) {
        this.bookInfo = book;
        bookNameTextFiled.setText(book.getName());
        bookAuthorTextFiled.setText(book.getAuthor());
        bookPublisherTextFiled.setText(book.getBookPublisher());
        bookCategoryTextFiled.setText(book.getCategory());
    }

    public void setUserInfo(User user) {
        fullNameTextFiled.setText(user.getName());
        phoneNumberTextFiled.setText(user.getPhoneNumber());
        emailTextFiled.setText(user.getEmail());
        addressTextFiled.setText(user.getAddress());
    }

    BorrowReport getData() {
        if (!validateFields()) {
            return null;
        }

        return new BorrowReport(0,
                userIdTextFiled.getText(),
                bookISBNTextFiled.getText(),
                borrowDateTextFiled.getValue().format(DateTimeFormatter.ISO_LOCAL_DATE),
                null,
                dueDateTextFIled.getValue().format(DateTimeFormatter.ISO_LOCAL_DATE),
                BorrowReport.BORROWED,
                null);
    }

    boolean validateFields() {
        // Kiểm tra Book ISBN
        String bookISBN = getBookISBN();
        if (bookISBN.isEmpty()) {
            showAlert.showAlert("Book ISBN cannot be empty!", "error");
            return false;
        }

        // Kiểm tra User ID
        String userId = getUserId();
        if (userId.isEmpty()) {
            showAlert.showAlert("User ID cannot be empty!", "error");
            return false;
        }

        // Kiểm tra thông tin sách
        if (bookNameTextFiled.getText().isEmpty()) {
            showAlert.showAlert("Book information not found!", "error");
            return false;
        }

        // Kiểm tra thông tin user
        if (fullNameTextFiled.getText().isEmpty()) {
            showAlert.showAlert("User information not found!", "error");
            return false;
        }

        // Kiểm tra Borrow Date
        if (borrowDateTextFiled.getValue() == null) {
            showAlert.showAlert("Borrow Date must be selected!", "error");
            return false;
        }

        // Kiểm tra Due Date
        if (dueDateTextFIled.getValue() == null) {
            showAlert.showAlert("Due Date must be selected!", "error");
            return false;
        }

        // Kiểm tra xem Due Date có sau Borrow Date không
        if (borrowDateTextFiled.getValue().isAfter(dueDateTextFIled.getValue())) {
            showAlert.showAlert("Due Date must be after Borrow Date!", "error");
            return false;
        }
        return true;
    }
}
