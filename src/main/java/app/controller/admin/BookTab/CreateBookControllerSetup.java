package app.controller.admin.BookTab;

import app.config.ViewConfig.FXMLResolver;

public class CreateBookControllerSetup {
    final MainBookController mainBookCtrl;

    public CreateBookControllerSetup(MainBookController mainBookCtrl) {
        this.mainBookCtrl = mainBookCtrl;
    }

    void init() {
        mainBookCtrl.buttonAddBook.setOnAction(e -> createBook());
    }

    void createBook() {
        FXMLResolver.getInstance().renderScene("bookTab/create_book");
    }
}
