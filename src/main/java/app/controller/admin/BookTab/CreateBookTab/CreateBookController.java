package app.controller.admin.BookTab.CreateBookTab;

import java.util.List;

import app.config.ViewConfig.FXMLResolver;
import app.controller.admin.BookTab.HandleBook;
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
        } else if (e.getSource() == findDocomentButton) {
            addDataBook();
        } else if (e.getSource() == uploadFileButton) {
            RenderFileDialog();
        }
    }

    @Override
    protected void saveBook() {
        Book book = getBook();
        if (book == null) {
            return;
        }

        String image = "";
        if (selectedFile != null) {
            image = fileService.handleSaveImage(selectedFile, "book");
            book.setImagePath(image);
        }
        bookService.handleSaveBook(book);
        FXMLResolver.getInstance().renderScene("bookTab/book_tab");
    }

    private void addDataBook() {
        String query = bookISBNTextField.getText();
        if (query.isEmpty()) {
            return;
        }
        List<Book> books = googleApiService.searchBooks(query);
        if (books.isEmpty()) {
            showAlert.showAlert("Book not found!", "error");
            return;
        }
        for (Book book : books) {
            setTextFields(book);
        }
    }

    @Override
    protected Book getBook() {
        if (!validateFields()) {
            return null;
        }

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

    @Override
    protected boolean validateFields() {
        // Check book ISBN
        if (bookISBNTextField.getText().isEmpty()) {
            showAlert.showAlert("Book ISBN không được để trống!", "error");
            return false;
        }

        // Check book name
        if (bookNameTextField.getText().isEmpty()) {
            showAlert.showAlert("Tên sách không được để trống!", "error");
            return false;
        }

        // Check author
        if (bookAuthorTextField.getText().isEmpty()) {
            showAlert.showAlert("Tác giả không được để trống!", "error");
            return false;
        }

        // Check book quantity
        String quantity = bookQuantityTextField.getText();
        try {
            int bookQuantity = Integer.parseInt(quantity);
            if (bookQuantity <= 0) {
                showAlert.showAlert("Số lượng sách phải lớn hơn 0!", "error");
                return false;
            }
        } catch (NumberFormatException e) {
            showAlert.showAlert("Số lượng sách không hợp lệ!", "error");
            return false;
        }

        return true;
    }

}
