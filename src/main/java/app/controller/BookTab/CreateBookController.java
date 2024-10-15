package app.controller.BookTab;

import java.util.List;

import app.config.ViewConfig.FXMLResolver;
import app.controller.BaseController;
import app.domain.Book;
import app.repository.BookRepository;
import app.service.BookService;
import app.service.GoogleApiService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class CreateBookController implements BaseController {
    @FXML
    private TextField bookISBNTextField, bookNameTextField, bookAuthorTextField, bookQuantityTextField,
            bookPublisherTextField, bookCategoryTextField, fileSeletedTextField;

    @FXML
    private TextArea bookDescriptionTextArea;

    @FXML
    private Button comeBackButton, cancelButton, saveButton, uploadFileButton, findDocomentButton;

    private BookService bookService;
    private GoogleApiService googleApiService;

    @FXML
    private void handleButtonAction(ActionEvent e) {
        if (e.getSource() == comeBackButton) {
            FXMLResolver.getInstance().renderScene("bookTab/book_tab");
        } else if (e.getSource() == cancelButton) {
            clearFields();
        } else if (e.getSource() == saveButton) {
            bookService.handleSaveBook(getBook());
            FXMLResolver.getInstance().renderScene("bookTab/book_tab");
        } else if (e.getSource() == findDocomentButton) {
            addDataBook();
        } else if (e.getSource() == uploadFileButton) {
            System.out.println("Click button uploadFileButton");
        }
    }

    private void addDataBook() {
        String query = bookISBNTextField.getText();
        if (query.isEmpty()) {
            return;
        }
        List<Book> books = googleApiService.searchBooks(query);

        if (books.isEmpty()) {
            System.out.println("khong tim thay ban ghi");
            // làm cái hiện thông báo...
            return;
        }

        for (Book book : books) {
            setTextFields(book);
        }
    }

    private Book getBook() {
        Book book = new Book(
                bookISBNTextField.getText(),
                bookNameTextField.getText(),
                bookAuthorTextField.getText(),
                bookDescriptionTextArea.getText(),
                bookCategoryTextField.getText(),
                bookPublisherTextField.getText(),
                Integer.valueOf(bookQuantityTextField.getText()),
                Integer.valueOf(bookQuantityTextField.getText()),
                "");

        return book;
    }

    private void setTextFields(Book book) {
        bookISBNTextField.setText(book.getId());
        bookNameTextField.setText(book.getName());
        bookAuthorTextField.setText(book.getAuthor());
        bookPublisherTextField.setText(book.getBookPublisher());
        bookCategoryTextField.setText(book.getCategory());
        bookDescriptionTextArea.setText(book.getDescription());
    }

    @FXML
    private void clearFields() {
        bookISBNTextField.clear();
        bookNameTextField.clear();
        bookAuthorTextField.clear();
        bookQuantityTextField.clear();
        bookPublisherTextField.clear();
        bookCategoryTextField.clear();
        bookDescriptionTextArea.clear();
    }

    @Override
    public void initialize() {
        bookService = new BookService(new BookRepository());
        googleApiService = new GoogleApiService();
    }
}
