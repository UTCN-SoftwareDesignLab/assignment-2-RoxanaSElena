package com.example.demo.report;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.example.demo.report.ReportType.CSV;
import static com.example.demo.report.ReportType.PDF;

@SpringBootTest
public class ReportTest {

    @Autowired
    private ReportServiceFactory reportServiceFactory;

    @Test
    void getReportService() {
        ReportService csvReportService = reportServiceFactory.getReportService(CSV);
        Assertions.assertEquals("OutOfStockBooks.csv", csvReportService.export());
        ReportService pdfReportService = reportServiceFactory.getReportService(PDF);
        Assertions.assertEquals("OutOfStockBooks.pdf", pdfReportService.export());
    }

}
