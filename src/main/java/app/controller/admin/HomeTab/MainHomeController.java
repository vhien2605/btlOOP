package app.controller.admin.HomeTab;

import app.controller.BaseController;
import app.controller.admin.Panel.SidebarController;
import app.domain.Book;
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
import javafx.fxml.FXML;
import javafx.scene.chart.*;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
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
    StackedAreaChart<String, Number> userChart;

    @FXML
    Label dataBookLabel, dataUserLabel, dataAllIssuedabel, dataBorrowedLabel;

    private BookService bookService;
    private MultiTaskService multiTaskService;
    private ReportService reportService;

    private void setDataCard(String dataBook, String dataUser, String dataIssued, String dataBorrowed) {
        dataBookLabel.setText(dataBook);
        dataUserLabel.setText(dataUser);
        dataAllIssuedabel.setText(dataIssued);
        dataBorrowedLabel.setText(dataBorrowed);
    }

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
            categoryQuantity.put(book.getCategory(), categoryQuantity.getOrDefault(book.getCategory(), 0) + 1);
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
            nameQuantity.put(reportDetail.getBookName(), nameQuantity.getOrDefault(reportDetail.getBookName(), 0) + 1);
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
                new PieChart.Data("Books are borrowed and out of require date", 10));
        issueBookChart.setData(pieChartData);
    }

    private void addDataToUserChart() {
        XYChart.Series<String, Number> series1 = new XYChart.Series<>();
        series1.setName("Data Series 1");
        series1.getData().add(new XYChart.Data<>("Hien", 5));
        series1.getData().add(new XYChart.Data<>("Hau", 10));
        series1.getData().add(new XYChart.Data<>("Hieu", 15));
        series1.getData().add(new XYChart.Data<>("Hai", 20));
        series1.getData().add(new XYChart.Data<>("Huy", 20));
        XYChart.Series<String, Number> series2 = new XYChart.Series<>();
        series2.setName("Data Series 2");
        series2.getData().add(new XYChart.Data<>("Hien", 5));
        series2.getData().add(new XYChart.Data<>("Hau", 10));
        series2.getData().add(new XYChart.Data<>("Hieu", 15));
        series2.getData().add(new XYChart.Data<>("Hai", 20));
        series2.getData().add(new XYChart.Data<>("Huy", 20));
        userChart.getData().add(series1);
        userChart.getData().add(series2);
    }

}