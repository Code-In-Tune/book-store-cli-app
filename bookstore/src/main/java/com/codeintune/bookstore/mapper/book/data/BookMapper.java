package com.codeintune.bookstore.mapper.book.data;

import com.codeintune.bookstore.dto.book.AddBookRequestDTO;
import com.codeintune.bookstore.dto.book.AddBookResponseDTO;
import com.codeintune.bookstore.dto.book.GetBookResponseDTO;
import com.codeintune.bookstore.model.book.Book;

public interface BookMapper {

    Book toEntity(AddBookRequestDTO request);
    void updateWithBookData(AddBookResponseDTO response, Book book);
    void updateGetWithBookData(GetBookResponseDTO response, Book book);
}
