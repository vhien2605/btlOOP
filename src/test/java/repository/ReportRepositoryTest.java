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
        System.setProperty("db.config", "database-testing.properties");
        reportRepository = new ReportRepository();
    }

    @Test
    public void findAllReportTest() {
        List<BorrowReport> result = this.reportRepository.findAll();
        Assertions.assertEquals(5, result.size());
    }

    @Test
    public void findAllReportByUserNameTest() {
        List<BorrowReport> result = this.reportRepository.findReportByUsername("John Doe");
        Assertions.assertEquals(1, result.size());
    }

    @Test
    public void findAllReportByUserNameTest2() {
        List<BorrowReport> result = this.reportRepository.findReportByUsername("Jane Smith");
        Assertions.assertEquals(1, result.size());
    }

    @Test
    public void findByStatusTest() {
        List<BorrowReport> result = this.reportRepository.findByStatus("Returned");
        Assertions.assertEquals(2, result.size());
    }

    @Test
    public void updateOneTest() {
        BorrowReport reportTest = new BorrowReport(5, "3", "1"
                , "2024-11-01", "", "2024-12-01", "Borrowed", "vailon");
        boolean check = this.reportRepository.updateOne(reportTest);
        Assertions.assertTrue(check);
    }

    @Test
    public void findByOneColumnTest() {
        List<BorrowReport> list = this.reportRepository.findByOneColumn("status", "Not Returned");
        Assertions.assertEquals(2, list.size());
    }
}
