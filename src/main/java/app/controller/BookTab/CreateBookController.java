package app.controller.BookTab;

import app.config.ViewConfig.FXMLResolver;
import app.controller.BaseController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class CreateBookController implements BaseController {
    @FXML
    private TextField bookISBNTextFiled, bookNameTextFiled, bookAuthorTextFiled, bookQuantityTextFiled,
            bookPublisherTextFiled, bookCategoryTextFiled;

    @FXML
    private TextArea bookDescriptionTextArea;

    @FXML
    private Button comeBackButton, cancelButton, saveButton;

    @FXML
    private void handdleButtonAction(ActionEvent e) {
        if (e.getSource() == comeBackButton) {
            FXMLResolver.getInstance().renderScene("book_tab");
        } else if (e.getSource() == cancelButton) {
            clearFields();
        } else if (e.getSource() == saveButton) {
            System.out.println("click button save");
        }
    }

    @FXML
    private void clearFields() {
        // Xóa nội dung của các TextField
        bookISBNTextFiled.clear();
        bookNameTextFiled.clear();
        bookAuthorTextFiled.clear();
        bookQuantityTextFiled.clear();
        bookPublisherTextFiled.clear();
        bookCategoryTextFiled.clear();

        // Xóa nội dung của TextArea
        bookDescriptionTextArea.clear();
    }

    @Override
    public void initialize() {

    }
}
