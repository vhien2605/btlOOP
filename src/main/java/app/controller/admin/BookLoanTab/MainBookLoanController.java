package app.controller.admin.BookLoanTab;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import app.config.ViewConfig.FXMLResolver;
import app.controller.admin.Panel.SidebarController;
import app.controller.helper.ShowAlert;
import app.domain.Book;
import app.domain.BorrowReport;
import app.domain.User;
import app.repository.BookRepository;
import app.repository.ReportRepository;
import app.repository.UserRepository;
import app.service.mainService.BookService;
import app.service.mainService.ReportService;
import app.service.mainService.UserService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

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

    BorrowReport borrowReport;

    UserService userService;

    BookService bookService;

    ReportService reportService;

    ShowAlert showAlert;

    String previousTabPath;

    public void initialize() {
        userService = new UserService(new UserRepository());
        bookService = new BookService(new BookRepository());
        showAlert = new ShowAlert();
        reportService = new ReportService(new ReportRepository(), userService, bookService);
        new AllSetup().init_function(this);
    }

    public void handleComeBackButton() {
        FXMLResolver.getInstance().renderScene(previousTabPath);
    }

    public void renderData(BorrowReport data, String previousTabPath) {
        this.borrowReport = data;
        this.previousTabPath = previousTabPath;
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

        statusChoiceBox.getItems().addAll(BorrowReport.PENDING, BorrowReport.BORROWED, BorrowReport.RETURNED);
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

    boolean updateDataBorrowReport() {
        if (!validate()) {
            return false;
        }

        LocalDate borrowDate = borrowDateTextFiled.getValue();
        if (borrowDate != null) {
            borrowReport.setBorrowDate(borrowDate.format(DateTimeFormatter.ISO_LOCAL_DATE));
        }

        LocalDate dueDate = dueDateTextFiled.getValue();
        if (dueDate != null) {
            borrowReport.setExpectedReturnDate(dueDate.format(DateTimeFormatter.ISO_LOCAL_DATE));
        }

        LocalDate returnDate = returnDateTextFiled.getValue();
        if (returnDate != null) {
            borrowReport.setReturnDate(returnDate.format(DateTimeFormatter.ISO_LOCAL_DATE));
        } else {
            borrowReport.setReturnDate(null);
        }

        String status = statusChoiceBox.getValue();
        borrowReport.setStatus(status);

        return true;
    }

    boolean validate() {
        String status = statusChoiceBox.getValue();

        if (status.equals(BorrowReport.BORROWED)) {
            if (borrowDateTextFiled.getValue() == null || dueDateTextFiled.getValue() == null) {
                showAlert.showAlert("Borrow date and due date cannot be empty!", "error");
                return false;
            }
        }

        if (status.equals(BorrowReport.RETURNED)) {
            if (returnDateTextFiled.getValue() == null) {
                showAlert.showAlert("Invalid return date for status 'Returned'!", "error");
                return false;
            }
        }

        return true;
    }
}
