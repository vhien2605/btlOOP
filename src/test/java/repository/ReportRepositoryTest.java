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
        Assertions.assertEquals(80, result.size());
    }

    @Test
    public void findByStatusTest() {
        List<BorrowReport> result = this.reportRepository.findByStatus("Borrowed");
        Assertions.assertEquals(80, result.size());
    }

    @Test
    public void updateOneTest() {
        BorrowReport reportTest = new BorrowReport(15, "23020064", "1974709930"
                , "2024-11-01", "", "2024-12-01", "Borrowed", "vailon");
        boolean check = this.reportRepository.updateOne(reportTest);
        Assertions.assertTrue(check);
    }

    @Test
    public void findByOneColumnTest() {
        List<BorrowReport> list = this.reportRepository.findByOneColumn("userId", "23020064");
        Assertions.assertEquals(4, list.size());
    }
}
