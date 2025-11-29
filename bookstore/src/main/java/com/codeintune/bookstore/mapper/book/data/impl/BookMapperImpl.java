package com.codeintune.bookstore.mapper.book.data.impl;

import com.codeintune.bookstore.dto.book.AddBookRequestDTO;
import com.codeintune.bookstore.dto.book.AddBookResponseDTO;
import com.codeintune.bookstore.dto.book.GetBookResponseDTO;
import com.codeintune.bookstore.dto.book.UpdateBookByIdRequestDTO;
import com.codeintune.bookstore.mapper.book.data.BookMapper;
import com.codeintune.bookstore.model.book.Book;
import org.springframework.stereotype.Component;

@Component
public class BookMapperImpl implements BookMapper {

    @Override
    public Book toEntity(AddBookRequestDTO request) {
        Book book = new Book();
        book.setTitle(request.getTitle());
        book.setAuthor(request.getAuthor());
        book.setPublisher(request.getPublisher());
        book.setIsbn(request.getIsbn());
        return book;
    }

    @Override
    public void updateWithBookData(AddBookResponseDTO response, Book book) {
        response.setBookId(book.getBookId().toString());
        response.setTitle(book.getTitle());
        response.setAuthor(book.getAuthor());
        response.setPublisher(book.getPublisher());
        response.setIsbn(book.getIsbn());
    }

    @Override
    public void updateGetWithBookData(GetBookResponseDTO response, Book book) {
        response.setBookId(book.getBookId().toString());
        response.setTitle(book.getTitle());
        response.setAuthor(book.getAuthor());
        response.setPublisher(book.getPublisher());
        response.setIsbn(book.getIsbn());
    }

    @Override
    public void updateEntity(Book book, UpdateBookByIdRequestDTO requestDTO) {
        if(!requestDTO.getIsbn().isBlank()) {
            book.setIsbn(requestDTO.getIsbn());
        }
        if(!requestDTO.getTitle().isBlank()) {
            book.setTitle(requestDTO.getTitle());
        }
        if(!requestDTO.getAuthor().isBlank()) {
            book.setAuthor(requestDTO.getAuthor());
        }
        if(!requestDTO.getPublisher().isBlank()) {
            book.setPublisher(requestDTO.getPublisher());
        }
    }
}
