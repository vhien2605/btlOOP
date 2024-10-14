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

    @FXML
    private void handleButtonAction(ActionEvent e) {
        if (e.getSource() == comeBackButton) {
            FXMLResolver.getInstance().renderScene("bookTab/book_tab");
        } else if (e.getSource() == cancelButton) {
            clearFields();
        } else if (e.getSource() == saveButton) {
            System.out.println("click button save");
            Book book = new Book(
                    Integer.valueOf(bookISBNTextField.getText()),
                    bookNameTextField.getText(),
                    bookAuthorTextField.getText(),
                    bookDescriptionTextArea.getText(),
                    bookCategoryTextField.getText(),
                    bookPublisherTextField.getText(),
                    Integer.valueOf(bookQuantityTextField.getText()),
                    Integer.valueOf(bookQuantityTextField.getText()),
                    ""
            );
            bookService.handleSaveBook(book);
        }
    }

    @FXML
    private void clearFields() {
        // Xóa nội dung của các TextField
        bookISBNTextField.clear();
        bookNameTextField.clear();
        bookAuthorTextField.clear();
        bookQuantityTextField.clear();
        bookPublisherTextField.clear();
        bookCategoryTextField.clear();

        // Xóa nội dung của TextArea
        bookDescriptionTextArea.clear();
    }


    private BookService bookService;

    @Override
    public void initialize() {
        bookService = new BookService(new BookRepository());
    }
}
