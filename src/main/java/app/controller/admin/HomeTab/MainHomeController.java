package app.controller.admin.HomeTab;

import app.controller.BaseController;
import app.domain.Book;
import app.domain.BorrowReport;
import app.domain.DTO.ReportDetail;
import app.domain.User;
import app.exception.auth.SessionException;
import app.repository.BookRepository;
import app.repository.ReportRepository;
import app.repository.UserRepository;
import app.service.authService.AuthenticationService;
import app.service.authService.SessionService;
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

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;

import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

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
    Label dataBookLabel, dataUserLabel, dataAllIssueLabel, dataBorrowedLabel;

    @FXML
    Label clockLabel;

    @FXML
    Label helloLabel;

    private BookService bookService;
    private MultiTaskService multiTaskService;
    private ReportService reportService;
    private AuthenticationService authenticationService;
    private UserService userService;

    private void setUpDependencies() {
        authenticationService = new AuthenticationService(new SessionService(), new UserService(new UserRepository()));
        bookService = new BookService(new BookRepository());
        reportService = new ReportService(new ReportRepository(),
                new UserService(new UserRepository()), new BookService(new BookRepository()));
        userService = new UserService(new UserRepository());
        multiTaskService = new MultiTaskService(5);
    }

    private void addLayoutTaskToMultiThread() {
        multiTaskService.addTasks(() -> new ResultTask<>("List", this.bookService.getAllBooks()));
        multiTaskService.addTasks(() -> new ResultTask<>("List", this.reportService.transferToReportDetail()));
        multiTaskService.addTasks(() -> new ResultTask<>("List", this.userService.getAllUsers()));
        multiTaskService
                .addTasks(() -> new ResultTask<>("List", this.reportService.findByOneColumn("status", "Borrowed")));
        multiTaskService
                .addTasks(() -> new ResultTask<>("List", this.reportService.findByOneColumn("status", "Pending")));
    }

    @Override
    public void initialize() {
        setUpDependencies();
        setupClock();
        setHelloLabel();
        addLayoutTaskToMultiThread();
        try {
            List<ResultTask<?>> tasksResults = multiTaskService.handleTasks();
            List<Book> listBook = (List<Book>) tasksResults.get(0).getData();
            List<ReportDetail> listReport = ((List<ReportDetail>) tasksResults.get(1).getData());
            List<User> users = (List<User>) tasksResults.get(2).getData();
            List<BorrowReport> borrowedReports = (List<BorrowReport>) tasksResults.get(3).getData();
            List<BorrowReport> pendingReports = (List<BorrowReport>) tasksResults.get(4).getData();
            Platform.runLater(() -> {
                addDataToCategoryChart(listBook);
                addDataToBookBorrowChart(listReport);
                addDataToIssueBookChart(listBook, borrowedReports, pendingReports);
                addDataToUserChart(listReport);
                setDataCard(listBook, users, listReport, borrowedReports);
            });
        } catch (InterruptedException | ExecutionException e) {
            System.out.println(e.getMessage());
        }
    }

    private void setHelloLabel() {
        try {
            helloLabel.setText("Welcome " + this.authenticationService.getCurrentUser().getName() + " !");
        } catch (SessionException e) {
            helloLabel.setText("Welcome Guest !");
        }
    }

    private void setupClock() {
        clockLabel.setText("00:00:00");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.scheduleAtFixedRate(() -> {
            Platform.runLater(() -> {
                clockLabel.setText(LocalTime.now().format(formatter));
            });
        }, 0, 1, TimeUnit.SECONDS);
    }

    private void setDataCard(List<Book> books, List<User> users, List<ReportDetail> reports,
            List<BorrowReport> borrowedReports) {
        int totalBookRemaining = 0;
        for (Book book : books) {
            totalBookRemaining += book.getBookRemaining();
        }
        dataBookLabel.setText(String.valueOf(totalBookRemaining));
        dataUserLabel.setText(String.valueOf(users.size()));
        dataAllIssueLabel.setText(String.valueOf(reports.size()));
        dataBorrowedLabel.setText(String.valueOf(borrowedReports.size()));
    }

    private void addDataToCategoryChart(List<Book> listBook) {
        XYChart.Series<String, Number> categorySeries = new XYChart.Series<>();
        categorySeries.setName("Category number");
        HashMap<String, Integer> categoryQuantity = new HashMap<>();
        for (Book book : listBook) {
            categoryQuantity.put(book.getCategory(),
                    categoryQuantity.getOrDefault(book.getBookQuantity(), 0) + book.getBookQuantity());
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

    private void addDataToIssueBookChart(List<Book> listBook,
            List<BorrowReport> borrowedReports,
            List<BorrowReport> pendingReports) {
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                new PieChart.Data("Books in stock", listBook.size()),
                new PieChart.Data("Books are borrowed", borrowedReports.size()),
                new PieChart.Data("Books are in pending", pendingReports.size()));
        issueBookChart.setData(pieChartData);
    }

    private void addDataToUserChart(List<ReportDetail> listReport) {
        XYChart.Series<String, Number> series1 = new XYChart.Series<>();
        series1.setName("Borrow date");
        Map<String, Integer> map1 = new HashMap<>();
        for (ReportDetail report : listReport) {
            if (report.getStatus().equals("Borrowed")) {
                String borrowedDate = report.getBorrowDate();
                map1.put(borrowedDate, map1.getOrDefault(borrowedDate, 0) + 1);
                System.out.println(borrowedDate);
            }
        }
        if (!map1.isEmpty()) {
            for (String key : map1.keySet()) {
                series1.getData().add(new XYChart.Data<>(key, map1.get(key)));
            }
            userChart.getData().add(series1);
        }
        XYChart.Series<String, Number> series2 = new XYChart.Series<>();
        series2.setName("Return date");
        Map<String, Integer> map2 = new HashMap<>();
        for (ReportDetail report : listReport) {
            if (report.getStatus().equals("Returned")) {
                String borrowedDate = report.getBorrowDate();
                System.out.println(borrowedDate);
                map2.put(borrowedDate, map2.getOrDefault(borrowedDate, 0) + 1);
            }
        }
        if (!map2.isEmpty()) {
            for (String key : map2.keySet()) {
                series2.getData().add(new XYChart.Data<>(key, map2.get(key)));
            }
            userChart.getData().add(series2);
        }

    }
}