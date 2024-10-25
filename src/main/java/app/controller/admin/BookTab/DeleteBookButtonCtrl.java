package app.controller.admin.BookTab;

import app.domain.Book;

public class DeleteBookButtonCtrl {
    final MainBookController mainBookCtrl;

    public DeleteBookButtonCtrl(MainBookController mainBookCtrl) {
        this.mainBookCtrl = mainBookCtrl;
    }

    void init() {
        mainBookCtrl.buttonDelete.setOnAction(e -> deleteBook());
    }

    void deleteBook() {
        Book selectedBook = mainBookCtrl.getSelectedBook();
        if (selectedBook == null) {
            mainBookCtrl.showAlert.showAlert("No book selected!", "error");
            return;
        }
        String selectedId = selectedBook.getId();
        if (mainBookCtrl.bookService.deleteBook(selectedId)) {
            for (int i = 0; i < mainBookCtrl.list.size(); i++) {
                if (mainBookCtrl.list.get(i).getId().equals(selectedId)) {
                    mainBookCtrl.list.remove(i);
                    break;
                }
            }
            mainBookCtrl.showAlert.showAlert("Delete book successed!", "success");
        } else {
            mainBookCtrl.showAlert.showAlert("Delete book failed!", "error");
        }
    }
}
