package com.example.demo.frontoffice;

import com.example.demo.frontoffice.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Book findByTitle(String title);

    Book findBookByTitleOrAuthorOrGenre(String title, String author, String genre);

    List<Book> findBookByQuantity(int quantity);


}
