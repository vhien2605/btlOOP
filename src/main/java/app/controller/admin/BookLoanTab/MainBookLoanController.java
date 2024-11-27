package app.controller.admin.BookLoanTab;

import java.io.File;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import app.config.ViewConfig.FXMLResolver;
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
    DatePicker borrowDateTextFiled, dueDateTextFiled, returnDateTextFiled;

    @FXML
    ImageView qrImageView;

    @FXML
    Button updateButton, comeBackButton, exportButton, changeStatusButton;

    @FXML
    Pane pane_data;

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

    public Pane getPaneData() {
        return pane_data;
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

        setDate();

        setChangeStatusButton();

        setImage();
    }

    void setDate() {
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
    }

    void setChangeStatusButton() {
        String initialStatus = borrowReport.getStatus();
        changeStatusButton.setText(initialStatus);

        switch (initialStatus) {
            case BorrowReport.PENDING:
                changeStatusButton.setStyle("-fx-background-color: #32e544; ");
                break;
            case BorrowReport.BORROWED:
                changeStatusButton.setStyle("-fx-background-color: #6fd1ef; ");
                break;
            case BorrowReport.RETURNED:
                changeStatusButton.setStyle("-fx-background-color: #f0ad4e; ");
                break;
        }

        // Thêm sự kiện khi người dùng thay đổi giá trị button status
        changeStatusButton.setOnAction(event -> {
            String selectedStatus = changeStatusButton.getText();

            switch (selectedStatus) {
                case BorrowReport.PENDING:
                    changeStatusButton.setStyle("-fx-background-color: #6fd1ef; ");
                    changeStatusButton.setText(BorrowReport.BORROWED);
                    break;
                case BorrowReport.BORROWED:
                    changeStatusButton.setStyle("-fx-background-color:#f0ad4e ; ");
                    changeStatusButton.setText(BorrowReport.RETURNED);
                    break;
            }
        });
    }

    void setImage() {
        String path = borrowReport.getQrcodeUrl();
        if (path == null) {
            return;
        }

        try {
            String rootPath = Paths.get("").toAbsolutePath().toString();

            String imagePath = Paths.get(rootPath, "src", "main", "resources", "image", "QRCode", path)
                    .toAbsolutePath().toString();

            File imageFile = new File(imagePath);
            if (!imageFile.exists()) {
                System.out.println("Image file not found: " + imagePath);
                return;
            }

            String imageURI = imageFile.toURI().toString();
            Image image = new Image(imageURI);

            qrImageView.setImage(image);
        } catch (Exception e) {
            System.out.println("Load image fail");
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
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

    boolean validate() {
        String status = changeStatusButton.getText();

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
