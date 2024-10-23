package app.controller.admin.BookTab;

import app.config.ViewConfig.FXMLResolver;
import app.domain.Book;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class UpdateBookController extends HandleBook {
    @FXML
    protected TextField bookRemainingTextField;

    public void renderDataBook(Book book) {
        setTextFields(book);
    }

    @Override
    protected void handleButtonAction(ActionEvent e) {
        if (e.getSource() == comeBackButton) {
            FXMLResolver.getInstance().renderScene("bookTab/book_tab");
        } else if (e.getSource() == saveButton) {
            saveBook();
            FXMLResolver.getInstance().renderScene("bookTab/book_tab");
        }
    }

    @Override
    protected void saveBook() {

    }

    @Override
    protected Book getBook() {
        Book book = new Book(
                bookISBNTextField.getText(),
                bookNameTextField.getText(),
                bookAuthorTextField.getText(),
                bookDescriptionTextArea.getText(),
                bookCategoryTextField.getText(),
                bookPublisherTextField.getText(),
                Integer.parseInt(bookQuantityTextField.getText()),
                Integer.parseInt(bookQuantityTextField.getText()),
                imagePathTextField.getText());

        return book;
    }

    @Override
    protected void setTextFields(Book book) {
        bookISBNTextField.setText(book.getId());
        bookNameTextField.setText(book.getName());
        bookAuthorTextField.setText(book.getAuthor());
        bookPublisherTextField.setText(book.getBookPublisher());
        bookCategoryTextField.setText(book.getCategory());
        bookDescriptionTextArea.setText(book.getDescription());
        bookQuantityTextField.setText("" + book.getBookQuantity());
        bookRemainingTextField.setText("" + book.getBookRemaining());
        imagePathTextField.setText(book.getImagePath());
    }

    @Override
    public void initialize() {

    }
}
