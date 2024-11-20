package app.controller.user.HomePage;

import app.domain.Book;
import app.domain.BorrowReport;
import app.repository.BookRepository;
import app.repository.ReportRepository;
import app.repository.UserRepository;
import app.service.mainService.BookService;
import app.service.mainService.ReportService;
import app.service.mainService.UserService;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

public class YourBooksTabController {
    private MainHomePageController homeController;

    private ObservableList<BorrowReport> allReportToThisCurrentUser;

    private ObservableList<Book> allStatusBookList;

    private ObservableList<Book> pendingBookList;

    private ObservableList<Book> borrowingBookList;
    
    private ObservableList<Book> returnedBookList;

    private BookService bookService;

    private ReportService reportService;


    protected YourBooksTabController(MainHomePageController mainHomePageController) {
        this.homeController = mainHomePageController;
    }

    public void initialize() {
        bookService = new BookService(new BookRepository());
        reportService = new ReportService(new ReportRepository(), new UserService(new UserRepository()), new BookService(new BookRepository()));
        getAllReportToThisCurrentUser();
        getAllStatusBookList();
    }

    private void getAllStatusBookList() {
        allStatusBookList = FXCollections.observableArrayList();
        allStatusBookList = getBookListFromReportList(allReportToThisCurrentUser);
    }

    private void getAllReportToThisCurrentUser() {
        allReportToThisCurrentUser = FXCollections.observableArrayList();
        allReportToThisCurrentUser = reportService.findByInput("userID", MainHomePageController.user.getId(), null);
    }

    private ObservableList<Book> getBookListFromReportList(ObservableList<BorrowReport> borrowReportReports) {
        ObservableList<Book> bookList = FXCollections.observableArrayList();
        for (BorrowReport borrowReport : borrowReportReports) {
            bookList.add(bookService.findByISBN(borrowReport.getBookId()));
        }
        return bookList;
    }

}
