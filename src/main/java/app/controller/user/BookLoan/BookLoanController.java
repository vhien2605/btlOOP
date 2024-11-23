package app.controller.user.BookLoan;

import app.config.ViewConfig.FXMLResolver;
import app.controller.BaseController;
import app.domain.Book;
import app.domain.DTO.SurfaceUserDTO;
import app.exception.auth.SessionException;
import app.repository.UserRepository;
import app.service.authService.AuthenticationService;
import app.service.authService.SessionService;
import app.service.mainService.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
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

    private Book book;

    private SurfaceUserDTO user;

    private String status;

    private AuthenticationService authService;

    public void LoadBookLoanWithBookAndStatus(Book book, String status) {
        this.book = book;
        this.status = status;
        RenderUserInfo();
        RenderBookInfo();
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
        }
    }

    @Override
    public void initialize() {
        // TODO Auto-generated method stub
        authService = new AuthenticationService(new SessionService(), new UserService(new UserRepository()));
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
