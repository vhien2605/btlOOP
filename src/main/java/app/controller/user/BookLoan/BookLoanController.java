package app.controller.user.BookLoan;

import app.config.ViewConfig.FXMLResolver;
import app.controller.BaseController;
import app.domain.Book;
import app.domain.DTO.SurfaceUserDTO;
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

    // public BookLoanController(Book book, SurfaceUserDTO user, String status) {

    // }

    public void handleEvent(ActionEvent e) {
        if (e.getSource() == backButton) {
            FXMLResolver.getInstance().renderScene("user/homeTab/home");
        }
    }

    @Override
    public void initialize() {
        // TODO Auto-generated method stub
        
    }
    
}
