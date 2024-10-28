package app.service.mainService;


import app.domain.BorrowReport;
import app.repository.ReportRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * All logic business with Report in application here
 */
public class ReportService {
    private final ReportRepository reportRepository;

    public ReportService(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    public ObservableList<BorrowReport> getAllReports() {
        return FXCollections.observableList(this.reportRepository.findAll());
    }
}
