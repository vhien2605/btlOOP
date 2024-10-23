package app.controller.admin.HomeTab;

import app.controller.BaseController;
import app.domain.Book;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class MainHomeController implements BaseController {
    @FXML
    private TextField bookIdTextFiled, bookTitleTextFiled, bookAuthorTextFiled, bookPublisherTextFiled,
            bookQuantityTextFiled, bookRemainingTextFiled;

    @FXML
    private Button buttonSave, buttonUpdate, buttonDelete, buttonCencel;

    @FXML
    private TableView<Book> tableViewBook;

    @FXML
    private TableColumn<Book, Integer> colBookId;

    @FXML
    private TableColumn<Book, String> colBookTitle;

    @FXML
    private TableColumn<Book, String> colBookAuthor;

    @FXML
    private TableColumn<Book, String> colBookPublisher;

    @FXML
    private TableColumn<Book, Integer> colBookQuantity;

    @FXML
    private TableColumn<Book, Integer> colBookRemaining;

    @FXML
    private void handdleButtonAction(ActionEvent e) {
        System.out.println("ok");
    }

    @Override
    public void initialize() {

    }

}