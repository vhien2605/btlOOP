package app.controller.user.HomePage;

import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

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
        homeController.discoverBookSectionTitleList = FXCollections.observableArrayList();
        homeController.discoverBookSectionTitleList.addAll(
                "History",
                "Science",
                "Fiction",
                "Non-Fiction",
                "Fantasy");
    }

    public void initialize() {
        getBookSectionList();
        loadAllSection();
    }

    private void loadAllSection() {
        for (String bookSectionTitle : homeController.discoverBookSectionTitleList) {
            loadSectionByTitle(bookSectionTitle);
        }
    }

    private void loadSectionByTitle(String bookSectionTitle) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/user/homeTab/bookdiscoversection.fxml"));
            AnchorPane section = loader.load();
            homeController.discoverMainPage.getChildren().add(section);
            BookDiscoverSection controller = loader.getController();
            controller.InitBookSectionByTitle(bookSectionTitle);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
