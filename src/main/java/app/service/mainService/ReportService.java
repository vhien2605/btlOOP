package app.service.mainService;

import app.domain.Book;
import app.domain.BorrowReport;
import app.domain.DTO.ReportDetail;
import app.domain.User;
import app.repository.ReportRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * All logic business with Report in application here
 */
public class ReportService {
    private final ReportRepository reportRepository;
    private final UserService userService;
    private final BookService bookService;

    public ReportService(ReportRepository reportRepository,
                         UserService userService,
                         BookService bookService) {
        this.reportRepository = reportRepository;
        this.userService = userService;
        this.bookService = bookService;
    }

    public ObservableList<BorrowReport> getAllReports() {
        return FXCollections.observableList(this.reportRepository.findAll());
    }

    public boolean handleSave(BorrowReport data) {
        return this.reportRepository.save(data);
    }

    public List<ReportDetail> transferToReportDetail() {
        List<BorrowReport> borrowReports = this.reportRepository.findAll();
        List<ReportDetail> DTOreports = new ArrayList<>();
        List<User> users = this.userService.getAllUsers();
        List<Book> books = this.bookService.getAllBooks();
        HashMap<String, String> userMap = new HashMap<>();
        HashMap<String, String> bookMap = new HashMap<>();
        for (User user : users) {
            userMap.put(user.getId(), user.getName());
        }
        for (Book book : books) {
            bookMap.put(book.getId(), book.getName());
        }
        for (BorrowReport borrowReport : borrowReports) {
            DTOreports.add(new ReportDetail(
                    borrowReport.getId(),
                    userMap.get(borrowReport.getUserId()),
                    bookMap.get(borrowReport.getBookId()),
                    borrowReport.getBorrowDate(),
                    borrowReport.getReturnDate(),
                    borrowReport.getExpectedReturnDate(),
                    borrowReport.getStatus()
            ));
        }
        return DTOreports;
    }
}
