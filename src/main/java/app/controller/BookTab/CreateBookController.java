package app.controller.BookTab;

import app.config.ViewConfig.FXMLResolver;
import app.controller.BaseController;
import app.domain.Book;
import app.repository.BookRepository;
import app.service.BookService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class CreateBookController implements BaseController {
    @FXML
    private TextField bookISBNTextField, bookNameTextField, bookAuthorTextField, bookQuantityTextField,
            bookPublisherTextField, bookCategoryTextField;

    @FXML
    private TextArea bookDescriptionTextArea;

    @FXML
    private Button comeBackButton, cancelButton, saveButton;

    private BookService bookService;

    @FXML
    private void handleButtonAction(ActionEvent e) {
        if (e.getSource() == comeBackButton) {
            FXMLResolver.getInstance().renderScene("bookTab/book_tab");
        } else if (e.getSource() == cancelButton) {
            clearFields();
        } else if (e.getSource() == saveButton) {
            bookService.handleSaveBook(getBook());
            FXMLResolver.getInstance().renderScene("bookTab/book_tab");
        }
    }

    private Book getBook() {
        Book book = new Book(
                Integer.valueOf(bookISBNTextField.getText()),
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
    }
}
