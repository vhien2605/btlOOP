package app.controller.admin.BookTab.UpdateBookTab;

import app.config.ViewConfig.FXMLResolver;
import app.controller.admin.BookTab.HandleBookController;
import app.domain.Book;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class UpdateBookController extends HandleBookController {
    @FXML
    TextField bookRemainingTextField;

    Book oldValueBook;

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
        } else if (e.getSource() == uploadFileButton) {
            RenderFileDialog();
        }
    }

    @Override
    protected void saveBook() {
        Book newValueBook = getBook();
        System.out.println(newValueBook);
        if (newValueBook == null) {
            return;
        }

        String image = "";
        if (selectedFile != null) {
            System.out.println("path: " + oldValueBook.getImagePath());
            if (oldValueBook.getImagePath() != null) {
                fileService.handleDeleteImage(oldValueBook.getImagePath(), "book");
            }

            image = fileService.handleSaveImage(selectedFile, "book");
            newValueBook.setImagePath(image);
        }
        boolean check = bookService.handleUpdateOne(newValueBook);
        FXMLResolver.getInstance().renderScene("bookTab/book_tab");
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
                imagePathTextField.getText());

        return book;
    }

    void setTextFields(Book book) {
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

        // Check book remaining
        String remaining = bookRemainingTextField.getText();
        try {
            int bookRemaining = Integer.parseInt(remaining);
            int bookQuantity = Integer.parseInt(quantity);
            if (bookRemaining <= 0) {
                showAlert.showAlert("Số lượng sách còn lại phải lớn hơn 0!", "error");
                return false;
            }
            if (bookRemaining > bookQuantity) {
                showAlert.showAlert("Số lượng sách còn lại đang lớn hơn tổng số sách!", "error");
                return false;
            }
        } catch (NumberFormatException e) {
            showAlert.showAlert("Số lượng sách còn lại không hợp lệ!", "error");
            return false;
        }

        return true;
    }

}
