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
    private TextField searchBookTextField;

    @FXML
    private Button buttonUpdate, buttonDelete, buttonAddBook;

    @FXML
    private TableView<Book> tableViewBook;

    @FXML
    private TableColumn<Book, String> colBookISBN;

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

    ObservableList<Book> list;

    private ShowAlert showAlert;

    @FXML
    private void handleButtonAction(ActionEvent e) {
        if (e.getSource() == buttonAddBook) {
            FXMLResolver.getInstance().renderScene("bookTab/create_book");
        } else if (e.getSource() == buttonUpdate) {
            updateBook();
        } else if (e.getSource() == buttonDelete) {
            deleteBook();
        }
    }

    private BookService bookService;

    @Override
    public void initialize() {
        bookService = new BookService(new BookRepository());
        showAlert = new ShowAlert();
        showBooks();
    }

    private void showBooks() {
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

    private void deleteBook() {
        Book selectedBook = getSelectedBook();
        if (selectedBook == null) {
            showAlert.showAlert("No book selected!", "error");
            return;
        }
        String selectedId = selectedBook.getId();
        if (bookService.deleteBook(selectedId)) {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getId().equals(selectedId)) {
                    list.remove(i);
                    break;
                }
            }
            showAlert.showAlert("Delete book successed!", "success");
        } else {
            showAlert.showAlert("Delete book failed!", "error");
        }
    }

    private void updateBook() {
        Book selectedBook = getSelectedBook();
        if (selectedBook == null) {
            showAlert.showAlert("No book selected!", "error");
        } else {
            FXMLResolver resolver = FXMLResolver.getInstance();
            resolver.renderScene("bookTab/update_book");

            UpdateBookController updateBookController = resolver.getLoader().getController();
            updateBookController.renderDataBook(selectedBook);
        }
    }

    public Book getSelectedBook() {
        return tableViewBook.getSelectionModel().getSelectedItem();
    }
}
