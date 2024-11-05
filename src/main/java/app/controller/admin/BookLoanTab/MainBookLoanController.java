package app.controller.admin.BookLoanTab;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import app.config.ViewConfig.FXMLResolver;
import app.domain.Book;
import app.domain.BorrowReport;
import app.domain.User;
import app.repository.BookRepository;
import app.repository.UserRepository;
import app.service.mainService.BookService;
import app.service.mainService.UserService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class MainBookLoanController {
    @FXML
    TextField bookISBNTextFiled, userIdTextFiled, bookNameTextFiled,
            bookAuthorTextFiled, bookPublisherTextFiled, bookCategoryTextFiled,
            fullNameTextFiled, phoneNumberTextFiled, emailTextFiled, addressTextFiled;

    @FXML
    ChoiceBox<String> statusChoiceBox;

    @FXML
    DatePicker borrowDateTextFiled, dueDateTextFiled, returnDateTextFiled;

    @FXML
    ImageView qrImageView;

    @FXML
    Button updateButton, comeBackButton, exportButton;

    public static final String PENDING_APPROVAL = "Pending appproval";
    public static final String BORROWED = "Borrowed";
    public static final String RETURNED = "Returned";

    UserService userService;

    BookService bookService;

    BorrowReport borrowReport;

    public void initialize() {
        userService = new UserService(new UserRepository());
        bookService = new BookService(new BookRepository());
        new AllSetup().init_function(this);
    }

    public void handleComeBackButton() {
        FXMLResolver.getInstance().renderScene("issueBookTab/issuebook_tab");
    }

    public void renderData(BorrowReport data) {
        this.borrowReport = data;
        setData();
    }

    void setData() {
        User user = userService.findById(borrowReport.getUserId());
        if (user != null) {
            setUserInfo(user);
        }

        Book book = bookService.findByISBN(borrowReport.getBookId());
        if (book != null) {
            setBookInfo(book);
        }

        setDateAndStatus();

        setImage();
    }

    void setDateAndStatus() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        if (borrowReport.getBorrowDate() != null) {
            LocalDate date = LocalDate.parse(borrowReport.getBorrowDate(), formatter);
            borrowDateTextFiled.setValue(date);
        }

        if (borrowReport.getExpectedReturnDate() != null) {
            LocalDate date = LocalDate.parse(borrowReport.getExpectedReturnDate(), formatter);
            dueDateTextFiled.setValue(date);
        }

        if (borrowReport.getReturnDate() != null) {
            LocalDate date = LocalDate.parse(borrowReport.getReturnDate(), formatter);
            returnDateTextFiled.setValue(date);
        }

        statusChoiceBox.getItems().addAll(PENDING_APPROVAL, BORROWED, RETURNED);
        statusChoiceBox.setValue(borrowReport.getStatus());
    }

    void setImage() {
        String imagePath = getClass().getResource("/image/QRCode.png").toExternalForm();
        Image image = new Image(imagePath);
        qrImageView.setImage(image);
    }

    void setBookInfo(Book book) {
        bookISBNTextFiled.setText(borrowReport.getBookId());
        bookNameTextFiled.setText(book.getName());
        bookAuthorTextFiled.setText(book.getAuthor());
        bookPublisherTextFiled.setText(book.getBookPublisher());
        bookCategoryTextFiled.setText(book.getCategory());
    }

    void setUserInfo(User user) {
        userIdTextFiled.setText(borrowReport.getUserId());
        fullNameTextFiled.setText(user.getName());
        phoneNumberTextFiled.setText(user.getPhoneNumber());
        emailTextFiled.setText(user.getEmail());
        addressTextFiled.setText(user.getAddress());
    }
}
