package app.controller.user.HomePage;

import java.io.IOException;
import app.controller.BaseController;
import app.controller.user.HomePage.BookDiscoverSection;
import app.service.authService.AuthenticationService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;

public class DiscoverTabController {
    private MainHomePageController homeController;

    protected DiscoverTabController(MainHomePageController mainHomePageController) {
        this.homeController = mainHomePageController;
    }

    /**
     * The function of setting up the names and order of book sections that
     * displays to mainPage
     */
    private void getBookSectionList() {
        homeController.disCoverBookSectionTitleList = FXCollections.observableArrayList();
        homeController.disCoverBookSectionTitleList.addAll(
            "Top Rated",
            "History",
            "Science",
            "Fiction",
            "Non-Fiction",
            "Fantasy"
        );
    }

    public void initialize() {
        getBookSectionList();
        loadSection();
    }

    private void loadSection() {
        for (String bookSectionTitle : homeController.disCoverBookSectionTitleList) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/user/homeTab/bookdiscoversection.fxml"));
                VBox section = loader.load();
                homeController.discoverMainPage.getChildren().add(section);
                BookDiscoverSection controller = loader.getController();
                controller.InitBookSectionByTitle(bookSectionTitle);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    
}
