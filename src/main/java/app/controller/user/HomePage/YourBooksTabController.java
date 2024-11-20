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

    protected YourBooksTabController(MainHomePageController mainHomePageController) {
        this.homeController = mainHomePageController;
    }

    public void initialize() {
        
    }


}
