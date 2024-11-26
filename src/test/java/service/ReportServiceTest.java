package service;

import app.domain.BorrowReport;
import app.repository.ReportRepository;
import app.service.mainService.BookService;
import app.service.mainService.ReportService;
import app.service.mainService.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;

public class ReportServiceTest {

    @Mock
    private ReportRepository reportRepository;

    @Mock
    private UserService userService;

    @Mock
    private BookService bookService;

    @InjectMocks
    private ReportService reportService;

    @BeforeEach
    public void initialize() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getAllReportsTest() {
        List<BorrowReport> fakeData = List.of(new BorrowReport(1, "", ""
                        , "", "", "", "", ""),
                new BorrowReport(2, "", "", ""
                        , "", "", "", "")
        );
        Mockito.when(reportRepository.findAll()).thenReturn(fakeData);
        List<BorrowReport> reports = this.reportService.getAllReports();
        Assertions.assertEquals(2, reports.size());
    }


    @Test
    public void findOneColumnTest() {
        List<BorrowReport> fakeData = List.of(new BorrowReport(1, "23020064", ""
                        , "", "", "", "", ""),
                new BorrowReport(2, "23020064", "", ""
                        , "", "", "", "")
        );
        Mockito.when(reportRepository.findByOneColumn("name", "23020064")).thenReturn(fakeData);
        List<BorrowReport> reports = this.reportService.findByOneColumn("name", "23020064");
        Assertions.assertEquals(2, reports.size());
    }
}
