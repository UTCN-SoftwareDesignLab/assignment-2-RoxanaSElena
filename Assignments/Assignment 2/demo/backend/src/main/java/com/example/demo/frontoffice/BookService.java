package com.example.demo.frontoffice;

import com.example.demo.frontoffice.model.Book;
import com.example.demo.frontoffice.model.dto.BookDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    public BookDTO create (BookDTO book)
    {
        System.out.println("hei");
        /*System.out.println(bookRepository.save(
                bookMapper.fromDto(book)));*/
        return bookMapper.toDto(bookRepository.save(
                bookMapper.fromDto(book)
        ));
        /*BookDTO b = new BookDTO();
        b.setAuthor("JKROWLING");
        b.setTitle("hp");
        b.setPrice(1);
        b.setQuantity(100);
        return b;*/
    }

    public Book findById(Long id)
    {
        return bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Book not found: "+ id));
    }

    public List<BookDTO> findAll() {
        return bookRepository.findAll().stream()
                .map(bookMapper::toDto)
                .collect(Collectors.toList());
    }

    public BookDTO findByField(String author,String title, String genre)
    {
        return bookMapper.toDto(bookRepository.findBookByTitleOrAuthorOrGenre(author,title,genre));
    }

    public List<BookDTO> findByQuantity(int quantity) {
        return bookRepository.findBookByQuantity(quantity).stream()
                .map(bookMapper::toDto)
                .collect(Collectors.toList());
    }

    public BookDTO edit( BookDTO book)
    {
        Book b = findById(book.getId());
        b.setTitle(book.getTitle());
        b.setAuthor(book.getAuthor());
        b.setPrice(book.getPrice());
        return bookMapper.toDto(
                bookRepository.save(b)
        );
    }

    public void delete(long id)
    {
        System.out.println("serviceeee");
        bookRepository.deleteById(id);
    }

    public void sell (BookDTO book, int quantity)
    {
        Book bookToSell = bookRepository.findByTitle(book.getTitle());
        if(bookToSell.getQuantity() - quantity >= 0) {
            bookToSell.setQuantity(bookToSell.getQuantity() - quantity);
            bookRepository.save(bookToSell);
        }
    }

}
