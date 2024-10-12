package app.controller.BookTab;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import app.config.DbConfig;
import app.controller.BaseController;
import app.domain.Book;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import app.service.BookService;
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
            System.out.println("click button add book");
        } else if (e.getSource() == buttonUpdate) {
            System.out.println("click button update");
        } else if (e.getSource() == buttonDelete) {
            System.out.println("click button delete");
        }
    }
    
    private static BookRepository bookRepository;
    public void showBooks() {
        // Tạo danh sách các đối tượng Book
        ObservableList<Book> list = FXCollections.observableArrayList(
        new Book(1, "Book Title 1", "Author 1", "Description for book 1", "Fiction",
        "Publisher 1", 10, 8, "////"),
        new Book(2, "Book Title 2", "Author 2", "Description for book 2",
        "Non-Fiction", "Publisher 2", 5, 5, "////"),
        new Book(3, "Book Title 3", "Author 3", "Description for book 3", "Science",
        "Publisher 3", 7, 6, "////"),
        new Book(4, "Book Title 4", "Author 4", "Description for book 4", "History",
        "Publisher 4", 12, 10, "///"),
        new Book(5, "Book Title 5", "Author 5", "Description for book 5", "Fantasy",
        "Publisher 5", 15, 14, "///"));

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


    @Override
    public void initialize() {
        showBooks();
    }

}
