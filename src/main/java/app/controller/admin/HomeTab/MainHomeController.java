package app.controller.admin.HomeTab;

import app.controller.BaseController;
import app.domain.Book;
import app.repository.BookRepository;
import app.service.mainService.BookService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.util.HashMap;
import java.util.List;

public class MainHomeController implements BaseController {

    @FXML
    StackedBarChart<String, Number> categoryChart;

    @FXML
    StackedBarChart<String, Number> bookBorrowChart;

    @FXML
    PieChart issueBookChart;

    @FXML
    LineChart userChart;

    private BookService bookService;

    @Override
    public void initialize() {
        bookService = new BookService(new BookRepository());
        addDataToCategoryChart();
        addDataToBookBorrowChart();
        addDataToIssueBookChart();
        addDataToUserChart();
    }

    private void addDataToCategoryChart() {
        XYChart.Series<String, Number> categorySeries = new XYChart.Series<>();
        categorySeries.setName("Category number");
        List<Book> listBook = this.bookService.getAllBooks();
        HashMap<String, Integer> categoryQuantity = new HashMap<>();
        for (Book book : listBook) {
            categoryQuantity.put(book.getCategory()
                    , categoryQuantity.getOrDefault(book.getCategory(), 0) + 1);
        }
        for (String key : categoryQuantity.keySet()) {
            categorySeries.getData().add(new XYChart.Data<>(key, categoryQuantity.get(key)));
        }
        categoryChart.getData().add(categorySeries);
    }

    private void addDataToBookBorrowChart() {
        // thong ke ve so quyen sach muon theo user,category
        XYChart.Series<String, Number> borrowedSeries = new XYChart.Series<>();
        borrowedSeries.setName("Books Borrowed quantity by month");
        borrowedSeries.getData().add(new XYChart.Data<>("Month 1", 30));
        borrowedSeries.getData().add(new XYChart.Data<>("Month 2", 50));
        borrowedSeries.getData().add(new XYChart.Data<>("Month 3", 20));
        borrowedSeries.getData().add(new XYChart.Data<>("Month 4", 70));
        borrowedSeries.getData().add(new XYChart.Data<>("Month 5", 90));
        bookBorrowChart.getData().add(borrowedSeries);
    }

    private void addDataToIssueBookChart() {
        // Thong ke lien quan den muon sach,tra sach
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                new PieChart.Data("Books in stock", 60),
                new PieChart.Data("Books are borrowed and in require date", 30),
                new PieChart.Data("Books are borrowed and out of require date", 10)
        );
        issueBookChart.setData(pieChartData);
    }

    private void addDataToUserChart() {
        XYChart.Series<String, Number> userSeries = new XYChart.Series<>();
        userSeries.setName("User address chart");
        userSeries.getData().add(new XYChart.Data<>("Cau Giay", 500));
        userSeries.getData().add(new XYChart.Data<>("Ba Dinh", 200));
        userSeries.getData().add(new XYChart.Data<>("Thanh Xuan", 260));
        userChart.getData().add(userSeries);
    }
}