package app.controller.admin.HomeTab;

import app.controller.BaseController;
import app.domain.Book;
import app.domain.BorrowReport;
import app.domain.DTO.ReportDetail;
import app.repository.BookRepository;
import app.repository.ReportRepository;
import app.repository.UserRepository;
import app.service.mainService.BookService;
import app.service.mainService.ReportService;
import app.service.mainService.UserService;
import app.service.subService.multiTaskService.MultiTaskService;
import app.service.subService.multiTaskService.ResultTask;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.*;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class MainHomeController implements BaseController {

    @FXML
    StackedBarChart<String, Number> categoryChart;

    @FXML
    StackedBarChart<String, Number> bookBorrowChart;

    @FXML
    PieChart issueBookChart;

    @FXML
    StackedAreaChart<Integer, Integer> userChart;

    private BookService bookService;
    private MultiTaskService multiTaskService;
    private ReportService reportService;

    @Override
    public void initialize() {
        bookService = new BookService(new BookRepository());
        reportService = new ReportService(new ReportRepository(),
                new UserService(new UserRepository()), new BookService(new BookRepository()));
        multiTaskService = new MultiTaskService(5);
        multiTaskService.addTasks(() -> new ResultTask<>("List", this.bookService.getAllBooks()));
        multiTaskService.addTasks(() -> new ResultTask<>("List", this.reportService.transferToReportDetail()));
        try {
            List<ResultTask<?>> tasksResults = multiTaskService.handleTasks();
            List<Book> listBook = (List<Book>) tasksResults.get(0).getData();
            List<ReportDetail> listReport = ((List<ReportDetail>) tasksResults.get(1).getData());
            Platform.runLater(() -> {
                addDataToCategoryChart(listBook);
                addDataToBookBorrowChart(listReport);
                addDataToIssueBookChart();
                addDataToUserChart();
            });
        } catch (InterruptedException | ExecutionException e) {
            System.out.println(e.getMessage());
        }
    }

    private void addDataToCategoryChart(List<Book> listBook) {
        XYChart.Series<String, Number> categorySeries = new XYChart.Series<>();
        categorySeries.setName("Category number");
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

    private void addDataToBookBorrowChart(List<ReportDetail> listReport) {
        XYChart.Series<String, Number> borrowedSeries = new XYChart.Series<>();
        borrowedSeries.setName("Borrowed Books quantity by book name");
        HashMap<String, Integer> nameQuantity = new HashMap<>();
        for (ReportDetail reportDetail : listReport) {
            nameQuantity.put(reportDetail.getBookName()
                    , nameQuantity.getOrDefault(reportDetail.getBookName(), 0) + 1);
        }
        for (String key : nameQuantity.keySet()) {
            borrowedSeries.getData().add(new XYChart.Data<>(key, nameQuantity.get(key)));
        }
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
        XYChart.Series<Integer, Integer> series1 = new XYChart.Series<>();
        series1.setName("Data Series 1");
        series1.getData().add(new XYChart.Data<>(1, 5));
        series1.getData().add(new XYChart.Data<>(2, 10));
        series1.getData().add(new XYChart.Data<>(3, 15));
        series1.getData().add(new XYChart.Data<>(4, 20));
        XYChart.Series<Integer, Integer> series2 = new XYChart.Series<>();
        series2.setName("Data Series 2");
        series2.getData().add(new XYChart.Data<>(1, 8));
        series2.getData().add(new XYChart.Data<>(2, 12));
        series2.getData().add(new XYChart.Data<>(3, 18));
        series2.getData().add(new XYChart.Data<>(4, 22));
        userChart.getData().add(series1);
        userChart.getData().add(series2);
    }
}