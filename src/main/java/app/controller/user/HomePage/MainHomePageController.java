package app.controller.user.HomePage;

import app.config.ViewConfig.FXMLResolver;
import app.controller.BaseController;
import app.domain.Book;
import app.domain.BorrowReport;
import app.domain.DTO.SurfaceUserDTO;
import app.exception.auth.SessionException;
import app.repository.BookRepository;
import app.repository.ReportRepository;
import app.repository.UserRepository;
import app.service.authService.AuthenticationService;
import app.service.authService.SessionService;
import app.service.mainService.BookService;
import app.service.mainService.ReportService;
import app.service.mainService.UserService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;

public class MainHomePageController implements BaseController{
    @FXML
    protected VBox discoverMainPage;

    @FXML
    protected TilePane yourBooksMainPage;

    @FXML
    protected Button allButtonYourBooks;

    @FXML
    protected Button pendingButtonYourBooks;

    @FXML 
    protected Button borrowingButtonYourBooks;

    @FXML
    protected Button returnedButtonYourBooks;

    protected ObservableList<String> discoverBookSectionTitleList;

    public static ObservableList<BorrowReport> allBorrowReportToThisCurrentUser;

    public static ObservableList<Book> allStatusBookList;

    public static ObservableList<Book> pendingBookList;

    public static ObservableList<Book> borrowingBookList;

    public static ObservableList<Book> returnedBookList;

    public static SurfaceUserDTO user;

    public static AuthenticationService authService;

    public static ReportService reportService;

    public static BookService bookService;


    private void DivideToOtherControllers() {
        new DiscoverTabController(this).initialize();
        new YourBooksTabController(this).initialize();
    }

    @Override
    public void initialize() {
        authService = new AuthenticationService(new SessionService(), new UserService(new UserRepository()));
        reportService = new ReportService(new ReportRepository(), new UserService(new UserRepository()), new BookService(new BookRepository()));
        bookService = new BookService(new BookRepository());
        getUserInfo();
        getAllReportToThisCurrentUser();
        TransferBorrowReportToBookLists();
        DivideToOtherControllers();
    }

    public void handleEvent(ActionEvent e) {
        if (e.getSource() == allButtonYourBooks) {
            System.out.println("All button is clicked");
        } else if (e.getSource() == pendingButtonYourBooks) {
            System.out.println("Pending button is clicked");
        } else if (e.getSource() == borrowingButtonYourBooks) {
            System.out.println("Borrowing button is clicked");
        } else if (e.getSource() == returnedButtonYourBooks) {
            System.out.println("Returned button is clicked");
        }
    }

    private void TransferBorrowReportToBookLists() {
        allStatusBookList = FXCollections.observableArrayList();
        borrowingBookList = FXCollections.observableArrayList();
        pendingBookList = FXCollections.observableArrayList();
        returnedBookList = FXCollections.observableArrayList();
        for (BorrowReport borrowReport : allBorrowReportToThisCurrentUser) {
            Book book = bookService.findByISBN(borrowReport.getBookId());
            allStatusBookList.add(book);
            if (borrowReport.getStatus() == BorrowReport.PENDING) {
                pendingBookList.add(book);
            } else if (borrowReport.getStatus() == BorrowReport.BORROWED) {
                borrowingBookList.add(book);
            } else if (borrowReport.getStatus() == BorrowReport.RETURNED) {
                returnedBookList.add(book);
            }
        }
    }

    private void getAllReportToThisCurrentUser() {
        allBorrowReportToThisCurrentUser = FXCollections.observableArrayList();
        allBorrowReportToThisCurrentUser = reportService.findByOneColumn("userID", MainHomePageController.user.getId());
    }

    private void getUserInfo() {
        try {
            user = authService.getCurrentUser();
        } catch (SessionException exception) {
            FXMLResolver.getInstance().renderScene("auth/login");
        }
    }
}
