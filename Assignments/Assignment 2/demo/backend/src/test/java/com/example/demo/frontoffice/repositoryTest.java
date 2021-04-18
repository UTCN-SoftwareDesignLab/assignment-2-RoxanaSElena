package com.example.demo.frontoffice;

import com.example.demo.TestCreationFactory;
import com.example.demo.frontoffice.model.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class repositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @BeforeEach
    public void beforeEach()
    {
        bookRepository.deleteAll();
    }

    @Test
    public void testMock()
    {
        Book bookSaved = bookRepository.save(Book.builder()
                .title("Harry Potter")
                .author("J.K.Rowling")
                .genre("Fiction")
                .quantity(200)
                .price(15.5f)
                .build());
        assertNotNull(bookSaved);

        assertThrows(DataIntegrityViolationException.class, () -> {
            bookRepository.save(Book.builder().build());
        });
    }
}
