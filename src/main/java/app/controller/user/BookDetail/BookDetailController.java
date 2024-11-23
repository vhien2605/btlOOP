package app.controller.user.BookDetail;

import java.io.InputStream;
import java.time.format.DateTimeFormatter;

import com.itextpdf.kernel.colors.Lab;

import app.config.ViewConfig.FXMLResolver;
import app.controller.BaseController;
import app.controller.helper.ShowAlert;
import app.controller.user.BookLoan.BookLoanController;
import app.controller.user.HomePage.Card;
import app.controller.user.HomePage.MainHomePageController;
import app.domain.Book;
import app.domain.BorrowReport;
import app.domain.DTO.SurfaceUserDTO;
import app.domain.User;
import app.exception.auth.SessionException;
import app.repository.ReportRepository;
import app.service.mainService.ReportService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class BookDetailController implements BaseController {
    @FXML
    private ImageView imageURL;

    @FXML
    private Button backToHomeButton;

    @FXML
    private Button sendBorrowBookRequestButton;

    @FXML
    private DatePicker borrowDatePicker;

    @FXML
    private DatePicker expectedReturnDatePicker;

    @FXML
    private Label bookNameLabel;

    @FXML
    private Label authorNameLabel;

    @FXML
    private Label categoryLabel;

    @FXML
    private Label publisherLabel;

    @FXML
    private TextArea descriptionArea;

    @FXML
    public AnchorPane borrowRequestBox;

    @FXML
    public AnchorPane pendingBox;

    @FXML
    public AnchorPane borrowingBox;

    @FXML
    private Button viewBookLoanBorrowingButton;

    @FXML
    private Button viewBookLoanPendingButton;

    private ShowAlert showAlert;

    private ReportService reportService;

    private Book book;

    private String status;

    public void loadBookWithStatus(Book book, String status) {
        this.book = book;
        this.status = status;
        loadImage();
        loadData();
        // loadBox();
    }

    private void loadData() {
        bookNameLabel.setText(book.getName());
        authorNameLabel.setText(book.getAuthor());
        categoryLabel.setText(book.getCategory());
        publisherLabel.setText(book.getBookPublisher());
        descriptionArea.setText(book.getDescription());
    }

    private void loadImage() {
        InputStream inputStream = getClass().getResourceAsStream("/image/book/" + book.getImagePath());
        if (inputStream == null) {
            imageURL.setImage(new Image(getClass().getResourceAsStream("/image/book/book-default-cover.jpg")));
        } else {
            imageURL.setImage(new Image(inputStream));

        }
    }

    public void handleEvent(ActionEvent e) {
        if (e.getSource() == backToHomeButton) {
            FXMLResolver.getInstance().renderScene("user/homeTab/home");
        } else if (e.getSource() == sendBorrowBookRequestButton) {
            sendBorrowBookRequest();
        } else if (e.getSource() == viewBookLoanBorrowingButton) {
            FXMLResolver.getInstance().renderScene("user/bookloan/bookloan");
            BookLoanController controller = FXMLResolver.getInstance().getLoader().getController();
            controller.LoadBookLoanWithBookAndStatus(book, status);
        } else if (e.getSource() == viewBookLoanPendingButton) {
            FXMLResolver.getInstance().renderScene("user/bookloan/bookloan");
            BookLoanController controller = FXMLResolver.getInstance().getLoader().getController();
            controller.LoadBookLoanWithBookAndStatus(book, status);
        }
    }

    private void sendBorrowBookRequest() {
        if (!validateFields()) {
            return;
        }
        BorrowReport borrowReport = new BorrowReport(
                0,
                MainHomePageController.user.getId(),
                book.getId(),
                borrowDatePicker.getValue().format(DateTimeFormatter.ISO_LOCAL_DATE),
                null,
                expectedReturnDatePicker.getValue().format(DateTimeFormatter.ISO_LOCAL_DATE),
                BorrowReport.PENDING,
                ""
        );
        if (reportService.handleSave(borrowReport)) {
            showAlert.showAlert("Create new borrow book request successfully!", "success");
        } else {
            showAlert.showAlert("Create new borrow book request fail!", "error");
        }
    }

    private boolean validateFields() {
        if (borrowDatePicker.getValue() == null) {
            showAlert.showAlert("Borrow Date must be selected!", "error");
            return false;
        }

        if (expectedReturnDatePicker.getValue() == null) {
            showAlert.showAlert("Expected Return Date must be selected!", "error");
            return false;
        }

        if (!expectedReturnDatePicker.getValue().isAfter(borrowDatePicker.getValue())) {
            showAlert.showAlert("Expected return date must be after borrow date!", "error");
            return false;
        }
        return true;
    }

    @Override
    public void initialize() {
        showAlert = new ShowAlert();
        reportService = new ReportService(new ReportRepository(), null, null);
    }

    // private void loadBox() {
    //     borrowRequestBox.setVisible(false);
    //     pendingBox.setVisible(false);
    //     borrowingBox.setVisible(false);
    //     if (status.equals(Card.RETURNED_STATUS)) {
    //         borrowRequestBox.setVisible(true);
    //     } else if (status.equals(Card.PENDING_STATUS)) {
    //         pendingBox.setVisible(true);
    //     } else if (status.equals(Card.BORROWING_STATUS)) {
    //         borrowingBox.setVisible(true);
    //     }
    // }
}
