package com.example.demo.frontoffice;

import com.example.demo.frontoffice.model.Book;
import com.example.demo.frontoffice.model.dto.BookDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookMapper {

    BookDTO toDto(Book book);

    Book fromDto(BookDTO book);
}
