package app.controller.BookTab;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import app.config.ViewConfig.FXMLResolver;
import app.controller.BaseController;
import app.domain.Book;
import app.service.BookService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import app.repository.BookRepository;

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
    private TableColumn<Book, String> colBookName;

    @FXML
    private TableColumn<Book, String> colBookAuthor;

    @FXML
    private TableColumn<Book, String> colBookDescription;

    @FXML
    private TableColumn<Book, String> colBookCategory;

    @FXML
    private TableColumn<Book, String> colBookPublisher;

    @FXML
    private TableColumn<Book, Integer> colBookQuantity;

    @FXML
    private TableColumn<Book, Integer> colBookRemaining;

    @FXML
    private void handdleButtonAction(ActionEvent e) {
        if (e.getSource() == buttonAddBook) {
            FXMLResolver.getInstance().renderScene("bookTab/create_book");
        } else if (e.getSource() == buttonUpdate) {
            System.out.println("click button update");
        } else if (e.getSource() == buttonDelete) {
            System.out.println("click button delete");
        }
    }

    private BookService bookService;

    @Override
    public void initialize() {
        bookService = new BookService(new BookRepository());
        showBooks();
    }

    public void showBooks() {
        ObservableList<Book> list = bookService.getAllBooks();
        // Thiết lập các cột cho TableView
        colBookISBN.setCellValueFactory(new PropertyValueFactory<Book, Integer>("id"));
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

}
