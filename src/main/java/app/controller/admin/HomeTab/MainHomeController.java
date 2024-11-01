package app.controller.admin.HomeTab;

import app.controller.BaseController;
import app.domain.Book;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class MainHomeController implements BaseController {

    @FXML
    StackedBarChart categoryChart;

    @FXML
    StackedBarChart bookBorrowChart;


    @FXML
    PieChart issueBookChart;

    @Override
    public void initialize() {

    }
}