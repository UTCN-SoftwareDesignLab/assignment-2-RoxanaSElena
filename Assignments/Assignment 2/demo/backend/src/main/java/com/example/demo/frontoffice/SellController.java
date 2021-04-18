package com.example.demo.frontoffice;

import com.example.demo.frontoffice.BookService;
import com.example.demo.frontoffice.model.dto.BookDTO;
import com.example.demo.report.ReportType;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.demo.UrlMapping.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(STORE)
public class SellController {

    private final BookService bookService;

    @GetMapping
    public List<BookDTO> allItems() {
        return bookService.findAll();
    }

    @PatchMapping(AMOUNT)
    public void sell(@RequestBody BookDTO bookDTO, @PathVariable int quantity) {
        bookService.sell(bookDTO, quantity);
    }
}