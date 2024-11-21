package app.controller.user.HomePage;

import com.itextpdf.layout.borders.OutsetBorder;

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
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.PopupWindow.AnchorLocation;

public class MainHomePageController implements BaseController{
    @FXML
    public ChoiceBox<String> userButton;

    @FXML
    public VBox discoverMainPage;

    @FXML
    public TilePane yourBooksMainPage;

    @FXML
    public Button allButtonYourBooks;

    @FXML
    public Button pendingButtonYourBooks;

    @FXML 
    public Button borrowingButtonYourBooks;

    @FXML
    public Button returnedButtonYourBooks;

    public ObservableList<String> discoverBookSectionTitleList;

    public ObservableList<BorrowReport> allBorrowReportToThisCurrentUser;

    public ObservableList<Book> allStatusBookList;

    public ObservableList<Book> pendingBookList;

    public ObservableList<Book> borrowingBookList;

    public ObservableList<Book> returnedBookList;

    public static SurfaceUserDTO user;

    public AuthenticationService authService;

    public ReportService reportService;

    public BookService bookService;

    private DiscoverTabController discoverTabController;

    private YourBooksTabController yourBooksTabController;

    private UserButtonController userButtonController;

    private void DivideToOtherControllers() {
        discoverTabController = new DiscoverTabController(this);
        discoverTabController.initialize();

        yourBooksTabController = new YourBooksTabController(this);
        yourBooksTabController.initialize();

        userButtonController = new UserButtonController(this);
        userButtonController.initialize();
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
            yourBooksTabController.handleAllButtonIsClicked();
        } else if (e.getSource() == pendingButtonYourBooks) {
            yourBooksTabController.handlePendingButtonIsClicked();
        } else if (e.getSource() == borrowingButtonYourBooks) {
            yourBooksTabController.handleBorrowingButtonIsClicked();
        } else if (e.getSource() == returnedButtonYourBooks) {
            yourBooksTabController.handleReturnedButtonIsClicked();
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
            if (borrowReport.getStatus().equals(BorrowReport.PENDING)) {
                pendingBookList.add(book);
            } else if (borrowReport.getStatus().equals(BorrowReport.BORROWED)) {
                borrowingBookList.add(book);
            } else if (borrowReport.getStatus().equals(BorrowReport.RETURNED)) {
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
