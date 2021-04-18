package com.example.demo.frontoffice;

import com.example.demo.TestCreationFactory;
import com.example.demo.frontoffice.model.Book;
import com.example.demo.frontoffice.model.dto.BookDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.mockito.Mockito.when;

public class serviceTest {

    @InjectMocks
    private BookService bookService;

    @Mock
    private BookRepository bookRepository;

    @Mock
    private BookMapper bookMapper;

    @BeforeEach
    void setUp()
    {
        MockitoAnnotations.openMocks(this);
        bookService = new BookService(bookRepository,bookMapper);
    }

    @Test
    void findAll()
    {
        List<Book> books = TestCreationFactory.listOf(Book.class);
        when(bookRepository.findAll()).thenReturn(books);

        List<BookDTO> all = bookService.findAll();
        Assertions.assertEquals(books.size(), all.size());
    }

    @Test
    void create()
    {
        BookDTO book = BookDTO.builder()
                .author("author")
                .title("title")
                .genre("genre")
                .quantity(1)
                .price(1)
                .build();
        when(bookService.create(book)).thenReturn(book);
        Assertions.assertNotNull(book);
    }

    @Test
    void findByField()
    {
        BookDTO book = BookDTO.builder()
                .author("author")
                .title("title")
                .genre("genre")
                .quantity(1)
                .price(1)
                .build();
        BookDTO booktoFind = bookService.create(book);
        BookDTO bookFound = bookService.findByField("author",null, null);
        Assertions.assertEquals(booktoFind,bookFound);
    }
}
