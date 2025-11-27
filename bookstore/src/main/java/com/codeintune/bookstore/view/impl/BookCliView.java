package com.codeintune.bookstore.view.impl;

import com.codeintune.bookstore.dto.book.AddBookRequestDTO;
import com.codeintune.bookstore.exception.handler.ExceptionHandler;
import com.codeintune.bookstore.service.book.BookInputService;
import com.codeintune.bookstore.service.book.BookService;
import com.codeintune.bookstore.view.CliView;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BookCliView implements CliView {

    private final BookService bookService;
    private final ExceptionHandler exceptionHandler;
    private final BookInputService bookInputService;

    @Override
    public void addBook() {
        AddBookRequestDTO addBookRequestDTO;
        try{
            addBookRequestDTO = bookInputService.buildBookRequestDTO();
            bookService.addBook(addBookRequestDTO);
        }catch (Exception exception){
            exceptionHandler.handleException(exception);
        }
    }
}
