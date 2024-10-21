package app.controller.BookTab;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import app.config.ViewConfig.FXMLResolver;
import app.controller.BaseController;
import app.domain.Book;
import app.service.mainService.BookService;
import app.service.multiTaskService.MultiTaskService;
import app.service.multiTaskService.ResultTask;
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

    @FXML
    private void handleButtonAction(ActionEvent e) {
        if (e.getSource() == buttonAddBook) {
            FXMLResolver.getInstance().renderScene("bookTab/create_book");
        } else if (e.getSource() == buttonUpdate) {
            System.out.println("click button update");
        } else if (e.getSource() == buttonDelete) {
            System.out.println("click button delete");
        }
    }

    private BookService bookService;
    private MultiTaskService multiTaskService;

    @Override
    public void initialize() {
        bookService = new BookService(new BookRepository());
        multiTaskService = new MultiTaskService(2);
        showBooks();
    }

    public void showBooks() {
        List<Callable<ResultTask<?>>> listTasks = new ArrayList<>();
        listTasks.add(() -> {
            ObservableList<Book> listBooks = bookService.getAllBooks();
            return new ResultTask<>("Observable", listBooks);
        });

        listTasks.add(() -> {
            return new ResultTask<>("int", 2);
        });

        try {
            List<Future<ResultTask<?>>> results = multiTaskService.handleTasks(listTasks);
            ObservableList<Book> list = (ObservableList<Book>) results.get(0).get().getData();
            int number = (int) results.get(1).get().getData();
            System.out.println(number);
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
        } catch (InterruptedException | ExecutionException e) {
            System.out.println(e.getMessage());
        } finally {
            multiTaskService.shutdown();
        }

//        ObservableList<Book> list = bookService.getAllBooks();
//        // Thiết lập các cột cho TableView
//        colBookISBN.setCellValueFactory(new PropertyValueFactory<Book, String>("id"));
//        colBookName.setCellValueFactory(new PropertyValueFactory<Book, String>("name"));
//        colBookAuthor.setCellValueFactory(new PropertyValueFactory<Book, String>("author"));
//        colBookDescription.setCellValueFactory(new PropertyValueFactory<Book, String>("description"));
//        colBookCategory.setCellValueFactory(new PropertyValueFactory<Book, String>("category"));
//        colBookPublisher.setCellValueFactory(new PropertyValueFactory<Book, String>("bookPublisher"));
//        colBookQuantity.setCellValueFactory(new PropertyValueFactory<Book, Integer>("bookQuantity"));
//        colBookRemaining.setCellValueFactory(new PropertyValueFactory<Book, Integer>("bookRemaining"));
//
//        // Đặt danh sách vào TableView
//        tableViewBook.setItems(list);
    }

}
