package app.controller.admin.BookTab;

import java.util.LinkedHashMap;
import java.util.Map;

import app.controller.BaseController;
import app.controller.helper.ShowAlert;
import app.domain.Book;
import app.service.mainService.BookService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import app.repository.BookRepository;

public class MainBookController implements BaseController {
    @FXML
    TextField searchBookTextField;

    @FXML
    ChoiceBox<String> choiceBoxSearchFilter;

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

    private static final String ISBN_VALUE = "ISBN";
    private static final String TITLE_VALUE = "Title";
    private static final String AUTHOR_VALUE = "Author";
    private static final String CATEGORY_VALUE = "Category";

    private static final Map<String, String> DISPLAY_TO_VALUE_MAP = new LinkedHashMap<>();
    static {
        DISPLAY_TO_VALUE_MAP.put(ISBN_VALUE, "id");
        DISPLAY_TO_VALUE_MAP.put(TITLE_VALUE, "name");
        DISPLAY_TO_VALUE_MAP.put(AUTHOR_VALUE, "author");
        DISPLAY_TO_VALUE_MAP.put(CATEGORY_VALUE, "category");
    }

    @Override
    public void initialize() {
        bookService = new BookService(new BookRepository());
        showAlert = new ShowAlert();
        showBooks();
        new AllSetUp().init_function(this);

        ObservableList<String> displayValues = FXCollections.observableArrayList(DISPLAY_TO_VALUE_MAP.keySet());
        choiceBoxSearchFilter.setItems(displayValues);
        choiceBoxSearchFilter.setValue(ISBN_VALUE); // Thiết lập giá trị mặc định
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
