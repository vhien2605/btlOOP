package app.controller.admin.BookTab;

import app.config.ViewConfig.FXMLResolver;
import app.domain.Book;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class UpdateBookController extends HandleBook {
    @FXML
    private TextField bookRemainingTextField;

    protected Book oldValueBook;

    public void renderDataBook(Book book) {
        this.oldValueBook = book;
        this.setTextFields(book);
    }

    @Override
    protected void handleButtonAction(ActionEvent e) {
        if (e.getSource() == comeBackButton) {
            FXMLResolver.getInstance().renderScene("bookTab/book_tab");
        } else if (e.getSource() == saveButton) {
            saveBook();
            FXMLResolver.getInstance().renderScene("bookTab/book_tab");
        } else if (e.getSource() == uploadFileButton) {
            RenderFileDialog();
        }
    }

    @Override
    protected void saveBook() {
        Book newValueBook = getBook();
        String image = "";
        if (selectedFile != null) {
            System.out.println("path: " + oldValueBook.getImagePath());
            if (oldValueBook.getImagePath() != null) {
                fileService.handleDeleteImage(oldValueBook.getImagePath(), "book");
            }

            image = fileService.handleSaveImage(selectedFile, "book");
            newValueBook.setImagePath(image);
        }
        bookService.handleUpdateOne(newValueBook);
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

    private void setTextFields(Book book) {
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

}
