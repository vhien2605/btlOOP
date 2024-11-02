package app.controller.admin.IssueBookTab;

import app.domain.Book;

public class GetBookController {
    final MainIssueController mainIssueCtrl;

    public GetBookController(MainIssueController mainIssueCtrl) {
        this.mainIssueCtrl = mainIssueCtrl;
    }

    void init() {
        mainIssueCtrl.findBookButton.setOnAction(e -> getBookInfo());
    }

    private void getBookInfo() {
        String bookISBN = mainIssueCtrl.getBookISBN();

        if (bookISBN.isEmpty()) {
            mainIssueCtrl.showAlert.showAlert("Please enter book ISBN!", "error");
            return;
        }

        Book book = mainIssueCtrl.bookService.findByISBN(bookISBN);

        if (book == null) {
            mainIssueCtrl.showAlert.showAlert("Book not found!", "error");
            return;
        }

        mainIssueCtrl.setBookInfo(book);
    }
}
