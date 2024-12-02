package app.service.mainService;

import app.domain.Book;
import app.domain.BorrowReport;
import app.domain.DTO.ReportDetail;
import app.repository.ReportRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;
import java.util.Optional;

/**
 * All logic business with Report in application here
 */
@SuppressWarnings("unused")
public class ReportService {
    private final ReportRepository reportRepository;
    private final UserService userService;
    private final BookService bookService;

    /**
     * Constructor for creation.
     *
     * @param reportRepository report Repo
     * @param userService      user service dependency
     * @param bookService      book service dependency
     */
    public ReportService(ReportRepository reportRepository,
            UserService userService,
            BookService bookService) {
        this.reportRepository = reportRepository;
        this.userService = userService;
        this.bookService = bookService;
    }

    /**
     * Find {@link BorrowReport} by id service method.
     *
     * @param id id
     * @return {@link BorrowReport}
     */
    public BorrowReport findById(int id) {
        Optional<BorrowReport> wrapperResult = this.reportRepository.findById(id);
        return wrapperResult.orElse(null);
    }

    /**
     * Get all reports service method.
     *
     * @return {@code ObservableList<BorrowReport>}
     */
    public ObservableList<BorrowReport> getAllReports() {
        return FXCollections.observableList(this.reportRepository.findAll());
    }

    /**
     * Handle save {@link BorrowReport} object to database.
     *
     * @param data target object to save
     * @return {@code boolean}
     */
    public boolean handleSave(BorrowReport data) {
        return this.reportRepository.save(data);
    }

    /**
     * Transfer entity to DTO.
     *
     * @return {@code List<ReportDetail>}
     */
    public List<ReportDetail> transferToReportDetail() {
        return this.reportRepository.getAllReportDetailDTO();
    }

    /**
     * Handle update {@link BorrowReport}.
     *
     * @param entity {@link BorrowReport}
     * @return {@code boolean}
     */
    public boolean handleUpdateOne(BorrowReport entity) {
        return this.reportRepository.updateOne(entity);
    }

    /**
     * Find {@link BorrowReport} by status.
     *
     * @param status input
     * @return {@code ObservableList<BorrowReport>}
     */
    public ObservableList<BorrowReport> findByStatus(String status) {
        return FXCollections.observableList(this.reportRepository.findByStatus(status));
    }

    /**
     * Handle delete {@link BorrowReport} by id service method.
     *
     * @param id input id
     * @return {@code boolean}
     */
    public boolean handleDeleteById(Integer id) {
        return this.reportRepository.deleteById(id);
    }

    /**
     * Find {@link BorrowReport}.
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
