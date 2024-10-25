package app.controller.admin.BookTab;

import app.config.ViewConfig.FXMLResolver;
import app.controller.BaseController;
import app.domain.Book;
import app.helper.ShowAlert;
import app.service.mainService.BookService;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import app.repository.BookRepository;

public class MainBookController implements BaseController {
    @FXML
    TextField searchBookTextField;

    @FXML
    Button buttonUpdate, buttonDelete, buttonAddBook;

    @FXML
    TableView<Book> tableViewBook;

    @FXML
    TableColumn<Book, String> colBookISBN;

    @FXML
    TableColumn<Book, String> colBookName;

    @FXML
    TableColumn<Book, String> colBookAuthor;

    @FXML
    TableColumn<Book, String> colBookDescription;

    @FXML
    TableColumn<Book, String> colBookCategory;

    @FXML
    TableColumn<Book, String> colBookPublisher;

    @FXML
    TableColumn<Book, Integer> colBookQuantity;

    @FXML
    TableColumn<Book, Integer> colBookRemaining;

    ObservableList<Book> list;

    ShowAlert showAlert;

    BookService bookService;

    @Override
    public void initialize() {
        bookService = new BookService(new BookRepository());
        showAlert = new ShowAlert();
        showBooks();
        new AllSetUp().init_function(this);
    }

    void showBooks() {
        list = bookService.getAllBooks();
        // Thiết lập các cột cho TableView
        colBookISBN.setCellValueFactory(new PropertyValueFactory<Book, String>("id"));
        colBookName.setCellValueFactory(new PropertyValueFactory<Book, String>("name"));
        colBookAuthor.setCellValueFactory(new PropertyValueFactory<Book, String>("author"));
        colBookDescription.setCellValueFactory(new PropertyValueFactory<Book, String>("description"));
        colBookCategory.setCellValueFactory(new PropertyValueFactory<Book, String>("category"));
        colBookPublisher.setCellValueFactory(new PropertyValueFactory<Book, String>("bookPublisher"));
        colBookQuantity.setCellValueFactory(new PropertyValueFactory<Book, Integer>("bookQuantity"));
        colBookRemaining.setCellValueFactory(new PropertyValueFactory<Book, Integer>("bookRemaining"));

        // Đặt danh sách vào TableView
        tableViewBook.setItems(list);
    }

    Book getSelectedBook() {
        return tableViewBook.getSelectionModel().getSelectedItem();
    }
}
