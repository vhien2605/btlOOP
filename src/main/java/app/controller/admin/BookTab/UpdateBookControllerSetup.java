package app.controller.admin.BookTab;

import app.config.ViewConfig.FXMLResolver;
import app.controller.admin.BookTab.UpdateBookTab.UpdateBookController;
import app.domain.Book;

public class UpdateBookControllerSetup {
    final MainBookController mainBookCtrl;

    public UpdateBookControllerSetup(MainBookController mainBookCtrl) {
        this.mainBookCtrl = mainBookCtrl;
    }

    void init() {
        mainBookCtrl.buttonUpdate.setOnAction(e -> updateBook());
    }

    void updateBook() {
        Book selectedBook = mainBookCtrl.getSelectedBook();
        if (selectedBook == null) {
            mainBookCtrl.showAlert.showAlert("No book selected!", "error");
        } else {
            FXMLResolver resolver = FXMLResolver.getInstance();
            resolver.renderScene("bookTab/update_book");

            UpdateBookController updateBookController = resolver.getLoader().getController();
            updateBookController.renderDataBook(selectedBook);
        }
    }
}
