package com.example.demo.frontoffice;
import com.example.demo.frontoffice.model.dto.BookDTO;
import com.example.demo.report.ReportServiceFactory;
import com.example.demo.report.ReportType;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import static com.example.demo.UrlMapping.*;

@RestController
@RequestMapping(FRONT_OFFICE)
@RequiredArgsConstructor

public class FrontOfficeController {
    private final BookService bookService;
    private final ReportServiceFactory reportServiceFactory;

    @GetMapping
    public List<BookDTO> allItems() {
        return bookService.findAll();
    }

    @PostMapping
    public BookDTO create(@RequestBody BookDTO item) {
        return bookService.create(item);
    }

    @PutMapping
    public BookDTO edit( @RequestBody BookDTO item) {
        return bookService.edit(item);
    }

    @DeleteMapping(ENTITY)
    public void delete(@PathVariable long id) {
        System.out.println("controller here");
        bookService.delete(id);
    }
    @GetMapping(EXPORT_REPORT)
    public void export(@PathVariable ReportType type)
    {
        System.out.println("export");
        reportServiceFactory.getReportService(type).export();
    }
}
