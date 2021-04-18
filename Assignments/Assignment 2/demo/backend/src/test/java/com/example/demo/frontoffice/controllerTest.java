package com.example.demo.frontoffice;

import com.example.demo.BaseControllerTest;
import com.example.demo.frontoffice.model.dto.BookDTO;
import com.example.demo.report.CSVReportService;
import com.example.demo.report.PdfReportService;
import com.example.demo.report.ReportServiceFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static com.example.demo.TestCreationFactory.*;
import static com.example.demo.UrlMapping.*;
import static com.example.demo.report.ReportType.CSV;
import static com.example.demo.report.ReportType.PDF;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class controllerTest extends BaseControllerTest {

    @InjectMocks
    private FrontOfficeController frontOfficeController;

    @Mock
    private BookService bookService;

    @Mock
    private ReportServiceFactory reportServiceFactory;

    @Mock
    private CSVReportService csvReportService;

    @Mock
    private PdfReportService pdfReportService;

    @BeforeEach
    protected void setUp()
    {
        super.setUp();
        frontOfficeController = new FrontOfficeController(bookService,reportServiceFactory);
        mockMvc = MockMvcBuilders.standaloneSetup(frontOfficeController).build();
    }

   @Test
    void export() throws Exception {
        when(reportServiceFactory.getReportService(PDF)).thenReturn(pdfReportService);
        when(reportServiceFactory.getReportService(CSV)).thenReturn(csvReportService);
        String pdfResponse = "";
        when(pdfReportService.export()).thenReturn(pdfResponse);
        String csvResponse = "";
        when(csvReportService.export()).thenReturn(csvResponse);

        ResultActions pdfExport = mockMvc.perform(get(FRONT_OFFICE + EXPORT_REPORT, PDF.name()));
        ResultActions csvExport = mockMvc.perform(get(FRONT_OFFICE + EXPORT_REPORT, CSV.name()));

        pdfExport.andExpect(status().isOk())
                .andExpect(content().string(pdfResponse));
        csvExport.andExpect(status().isOk())
                .andExpect(content().string(csvResponse));
    }

   /* @Test
    void edit() throws Exception {
        long id = randomLong();
        BookDTO reqItem = BookDTO.builder()
                .id(id)
                .title(randomString())
                .author(randomString())
                .genre(randomString())
                .quantity(randomBoundedInt(100))
                .price(randomBoundedInt(100))
                .build();

       // when(bookService.edit(id, reqItem)).thenReturn(reqItem);

        ResultActions result = performPutWithRequestBodyAndPathVariable(FRONT_OFFICE + ENTITY, reqItem, id);
        result.andExpect(status().isOk())
                .andExpect(jsonContentToBe(reqItem));
    }
*/

}
