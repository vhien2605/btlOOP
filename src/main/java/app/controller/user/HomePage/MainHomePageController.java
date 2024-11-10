package app.controller.user.HomePage;

import app.controller.BaseController;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

public class MainHomePageController implements BaseController{
    @FXML
    protected VBox discoverMainPage;

    protected ObservableList<String> disCoverBookSectionTitleList;

    private void DivideToOtherControllers() {
        new DiscoverTabController(this).initialize();

    }

    @Override
    public void initialize() {
        DivideToOtherControllers();
    }
    
    
}
