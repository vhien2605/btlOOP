package app.controller.admin.BookTab;

import java.util.List;

import app.config.ViewConfig.FXMLResolver;
import app.domain.Book;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class CreateBookController extends HandleBook {
    @FXML
    private Button findDocomentButton, cancelButton;

    @Override
    protected void handleButtonAction(ActionEvent e) {
        if (e.getSource() == comeBackButton) {
            FXMLResolver.getInstance().renderScene("bookTab/book_tab");
        } else if (e.getSource() == cancelButton) {
            clearFields();
        } else if (e.getSource() == saveButton) {
            saveBook();
            FXMLResolver.getInstance().renderScene("bookTab/book_tab");
        } else if (e.getSource() == findDocomentButton) {
            addDataBook();
        } else if (e.getSource() == uploadFileButton) {
            RenderFileDialog();
        }
    }

    @Override
    protected void saveBook() {
        Book book = getBook();
        String image = "";
        if (selectedFile != null) {
            image = fileService.handleSaveImage(selectedFile, "book");
            book.setImagePath(image);
        }
        bookService.handleSaveBook(book);
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
                null);

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

    private void clearFields() {
        bookISBNTextField.clear();
        bookNameTextField.clear();
        bookAuthorTextField.clear();
        bookQuantityTextField.clear();
        bookPublisherTextField.clear();
        bookCategoryTextField.clear();
        bookDescriptionTextArea.clear();
    }

}
