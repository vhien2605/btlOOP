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
        return this.reportRepository.getAllReportDetailDTO();
    }

    public boolean handleUpdateOne(BorrowReport entity) {
        return this.reportRepository.updateOne(entity);
    }

    public ObservableList<BorrowReport> findByStatus(String status) {
        return FXCollections.observableList(this.reportRepository.findByStatus(status));
    }

    public boolean handleDeleteById(Integer id) {
        return this.reportRepository.deleteById(id);
    }

    /**
     * Find {@link BorrowReport} .
     *
     * @param col    Col.
     * @param value  Col's value.
     * @param status Status of the Report.
     * @return {@code ObservableList<BorrowReport>}
     */
    public ObservableList<BorrowReport> findByInput(String col, String value, String status) {
        return FXCollections.observableList(this.reportRepository.findByInput(col, value, status));
    }

    /**
     * find by one column service.
     *
     * @param col   col
     * @param value value
     * @return {@code ObservableList<BorrowReport>}
     */
    public ObservableList<BorrowReport> findByOneColumn(String col, String value) {
        return FXCollections.observableList(this.reportRepository.findByOneColumn(col, value));
    }

    /**
     * Handle transaction book and report.
     *
     * @param book         book
     * @param borrowReport borrowReport
     * @return {@code boolean true/false}
     */
    public boolean updateReportAndBookTransaction(BorrowReport borrowReport, Book book) {
        return this.reportRepository.updateReportAndBookTransaction(borrowReport, book);
    }
}
