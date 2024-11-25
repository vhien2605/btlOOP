package app.controller.user.BookLoan;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.itextpdf.kernel.colors.Lab;

import app.config.ViewConfig.FXMLResolver;
import app.controller.BaseController;
import app.controller.helper.ShowAlert;
import app.domain.Book;
import app.domain.BorrowReport;
import app.domain.DTO.SurfaceUserDTO;
import app.exception.auth.SessionException;
import app.repository.BookRepository;
import app.repository.ReportRepository;
import app.repository.UserRepository;
import app.service.authService.AuthenticationService;
import app.service.authService.SessionService;
import app.service.mainService.BookService;
import app.service.mainService.ReportService;
import app.service.mainService.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class BookLoanController implements BaseController {
    @FXML
    private TextField userIdTextField;

    @FXML
    private TextField fullNameTextField;

    @FXML
    private TextField phoneNumberTextField;

    @FXML
    private TextField emailTextField;

    @FXML
    private TextField addressTextField;

    @FXML
    private TextField bookISBNTextField;

    @FXML
    private TextField bookNameTextField;

    @FXML
    private TextField bookAuthorTextField;

    @FXML
    private TextField bookPublisherTextField;

    @FXML
    private TextField bookCategoryTextField;

    @FXML
    private Button backButton;

    @FXML
    private DatePicker borrowDateTextField;

    @FXML
    private DatePicker dueDateTextField;
    
    @FXML
    private DatePicker returnDateTextField;

    @FXML
    private Label pendingStatusLabel;

    @FXML 
    private Label borrowingStatusLabel;

    @FXML
    private Label returnedStatusLabel;

    @FXML
    private Button cancelBorrowBookRequestButton;

    private BorrowReport borrowReport;

    private Book book;

    private SurfaceUserDTO user;

    private String status;

    private AuthenticationService authService;

    private ReportService reportService;

    private ShowAlert showAlert;

    public void LoadBookLoan(Book book, BorrowReport borrowReport, String status) {
        this.book = book;
        this.status = status;
        this.borrowReport = borrowReport;
        RenderUserInfo();
        RenderBookInfo();
        RenderDateAndStatus(borrowReport);
    }

    private void RenderDateAndStatus(BorrowReport borrowReport) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        if (borrowReport.getBorrowDate() != null) {
            LocalDate date = LocalDate.parse(borrowReport.getBorrowDate(), formatter);
            borrowDateTextField.setValue(date);
        }

        if (borrowReport.getExpectedReturnDate() != null) {
            LocalDate date = LocalDate.parse(borrowReport.getExpectedReturnDate(), formatter);
            dueDateTextField.setValue(date);
        }

        if (borrowReport.getReturnDate() != null) {
            LocalDate date = LocalDate.parse(borrowReport.getReturnDate(), formatter);
            returnDateTextField.setValue(date);
        }
        
        pendingStatusLabel.setVisible(false);
        borrowingStatusLabel.setVisible(false);
        returnedStatusLabel.setVisible(false);
        if (status.equals(BorrowReport.PENDING)) {
            pendingStatusLabel.setVisible(true);
        } else if (status.equals(BorrowReport.BORROWED)) {
            borrowingStatusLabel.setVisible(true);
        } else if (status.equals(BorrowReport.RETURNED)) {
            returnedStatusLabel.setVisible(true);
        }
    }

    private void RenderBookInfo() {
        bookISBNTextField.setText(book.getId());
        bookNameTextField.setText(book.getName());
        bookAuthorTextField.setText(book.getAuthor());
        bookPublisherTextField.setText(book.getBookPublisher());
        bookCategoryTextField.setText(book.getCategory());
    }

    private void RenderUserInfo() {
        userIdTextField.setText(user.getId());
        fullNameTextField.setText(user.getName());
        phoneNumberTextField.setText(user.getPhoneNumber());
        emailTextField.setText(user.getEmail());
        addressTextField.setText(user.getAddress());
    }

    public void handleEvent(ActionEvent e) {
        if (e.getSource() == backButton) {
            FXMLResolver.getInstance().renderScene("user/homeTab/home");
        } if (e.getSource() == cancelBorrowBookRequestButton) {
            DeleteBorrowReport();
            FXMLResolver.getInstance().renderScene("user/homeTab/home");
        }
    }

    private void DeleteBorrowReport() {
        reportService.handleDeleteById(borrowReport.getId());
        showAlert.showAlert("Delete borrow book request successfully!", "success");
    }

    @Override
    public void initialize() {
        showAlert = new ShowAlert();
        authService = new AuthenticationService(new SessionService(), new UserService(new UserRepository()));
        reportService = new ReportService(new ReportRepository(), new UserService(new UserRepository()), new BookService(new BookRepository()));
        getCurrentUserInfo();
    }

    private void getCurrentUserInfo() {
        try {
            user = authService.getCurrentUser();
        } catch (SessionException exception) {
            FXMLResolver.getInstance().renderScene("auth/login");
        }
    }
    
}
