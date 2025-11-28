package com.codeintune.bookstore.view.impl;

import com.codeintune.bookstore.dto.book.AddBookRequestDTO;
import com.codeintune.bookstore.dto.book.AddBookResponseDTO;
import com.codeintune.bookstore.exception.handler.ExceptionHandler;
import com.codeintune.bookstore.formatter.ResponseFormatter;
import com.codeintune.bookstore.formatter.impl.AddBookResponseFormatter;
import com.codeintune.bookstore.service.book.BookInputService;
import com.codeintune.bookstore.service.book.BookService;
import com.codeintune.bookstore.utils.constants.facade.FacadeConstants;
import com.codeintune.bookstore.view.BookCliView;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BookCliViewImpl implements BookCliView {

    private final BookService bookService;
    private final ExceptionHandler exceptionHandler;
    private final BookInputService bookInputService;

    @Override
    public String addBook() {
        AddBookResponseDTO responseDTO;
        ResponseFormatter<AddBookResponseDTO> responseFormatter = new AddBookResponseFormatter();
        try{
            AddBookRequestDTO addBookRequestDTO = bookInputService.buildBookRequestDTO();


            responseDTO = bookService.addBook(addBookRequestDTO);
            return responseFormatter.format(responseDTO);
        }catch (Exception exception){
            exceptionHandler.handleException(exception);
            return FacadeConstants.MESSAGE_FAILURE;
        }
    }
}
