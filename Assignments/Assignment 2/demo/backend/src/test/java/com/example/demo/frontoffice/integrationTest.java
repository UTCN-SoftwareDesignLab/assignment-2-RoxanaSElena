package com.example.demo.frontoffice;


import com.example.demo.frontoffice.model.Book;
import com.example.demo.frontoffice.model.dto.BookDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class integrationTest {

    @Autowired
    private BookService bookService;

    @Autowired
    private BookRepository bookRepository;

    @BeforeEach
    void setUp()
    {
        bookRepository.deleteAll();
    }

    @Test
    void createAll()
    {
        int nrBooks = 10;
        List<Book>  books = new ArrayList<>();
        for(int i =0; i< nrBooks; i++)
        {
            Book book = Book.builder()
                    .title("Title" + i)
                    .author("Author" + i)
                    .genre("Genre" + i)
                    .quantity(i)
                    .price(i)
                    .build();
            books.add(book);
            bookRepository.save(book);
        }

        List<BookDTO> bookDTOS = bookService.findAll();
        for(int i=0;i<nrBooks;i++)
        {
            assertEquals(books.get(i).getId(),bookDTOS.get(i).getId());
        }
    }
}
