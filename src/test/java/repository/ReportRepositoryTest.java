package repository;

import app.domain.BorrowReport;
import app.repository.ReportRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ReportRepositoryTest {
    private ReportRepository reportRepository;

    @BeforeEach
    public void initialize() {
        reportRepository = new ReportRepository();
    }

    @Test
    public void findAllReportTest() {
        List<BorrowReport> result = this.reportRepository.findAll();
        Assertions.assertNotEquals(0, result.size());
    }

    @Test
    public void findAllReportByUserNameTest() {
        List<BorrowReport> result = this.reportRepository.findReportByUsername("Vũ Minh Hiến");
        Assertions.assertEquals(2, result.size());
    }
}
