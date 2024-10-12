package app.controller.BookTab;

import app.controller.BaseController;
import app.domain.Book;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class MainBookController implements BaseController {
    @FXML
    private TextField searchBookTextFiled;

    @FXML
    private Button buttonUpdate, buttonDelete, buttonAddBook;

    @FXML
    private TableView<Book> tableViewBook;

    @FXML
    private TableColumn<Book, Integer> colBookISBN;

    @FXML
    private TableColumn<Book, String> colBookTitle;

    @FXML
    private TableColumn<Book, String> colBookAuthor;

    @FXML
    private TableColumn<Book, String> colDescription;

    @FXML
    private TableColumn<Book, String> colCategory;

    @FXML
    private TableColumn<Book, String> colBookPublisher;

    @FXML
    private TableColumn<Book, Integer> colBookQuantity;

    @FXML
    private TableColumn<Book, Integer> colBookRemaining;

    @FXML
    private TableColumn<Book, String> colBookCategory;

    @FXML
    private void handdleButtonAction(ActionEvent e) {
        if (e.getSource() == buttonAddBook) {
            System.out.println("click button add book");
        } else if (e.getSource() == buttonUpdate) {
            System.out.println("click button update");
        } else if (e.getSource() == buttonDelete) {
            System.out.println("click button delete");
        }
    }

    @Override
    public void initialize() {

    }

}
