package app.controller.admin.BookTab;

import javafx.scene.input.KeyCode;

public class SearchBookController {
    final MainBookController mainBookCtrl;

    public SearchBookController(MainBookController mainBookCtrl) {
        this.mainBookCtrl = mainBookCtrl;
    }

    void init() {
        mainBookCtrl.searchBoxTextField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                searchBook();
            }
        });

        mainBookCtrl.choiceBoxSearchFilter.getSelectionModel().selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> {
                    searchBook();
                });

    }

    private void searchBook() {
        String col = MainBookController.DISPLAY_TO_VALUE_MAP.get(mainBookCtrl.choiceBoxSearchFilter.getValue());
        String value = mainBookCtrl.searchBoxTextField.getText();

        System.out.println(col + " LIKE %" + value + "%");

        mainBookCtrl.list = mainBookCtrl.bookService.search(col, value);
        mainBookCtrl.tableViewBook.setItems(mainBookCtrl.list);
    }

}
