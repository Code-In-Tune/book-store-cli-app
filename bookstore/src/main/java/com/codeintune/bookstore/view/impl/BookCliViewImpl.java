package com.codeintune.bookstore.view.impl;

import com.codeintune.bookstore.dto.book.AddBookRequestDTO;
import com.codeintune.bookstore.dto.book.AddBookResponseDTO;
import com.codeintune.bookstore.dto.book.GetBookByIdRequestDTO;
import com.codeintune.bookstore.dto.book.GetBookResponseDTO;
import com.codeintune.bookstore.error.ValidationErrorDTO;
import com.codeintune.bookstore.exception.ValidationException;
import com.codeintune.bookstore.exception.handler.ExceptionHandler;
import com.codeintune.bookstore.formatter.ResponseFormatter;
import com.codeintune.bookstore.service.book.BookInputService;
import com.codeintune.bookstore.service.book.BookService;
import com.codeintune.bookstore.utils.constants.exception.ValidationExceptionConstants;
import com.codeintune.bookstore.utils.constants.facade.FacadeConstants;
import com.codeintune.bookstore.view.BookCliView;
import lombok.RequiredArgsConstructor;

import java.util.Optional;


@RequiredArgsConstructor
public class BookCliViewImpl implements BookCliView {

    private final BookService bookService;
    private final ExceptionHandler exceptionHandler;
    private final BookInputService bookInputService;
    private final ResponseFormatter<AddBookResponseDTO> addBookResponseDTOResponseFormatter;
    private final ResponseFormatter<GetBookResponseDTO> getBookResponseDTOResponseFormatter;

    @Override
    public String addBook() {
        AddBookResponseDTO responseDTO;
        try{
            AddBookRequestDTO addBookRequestDTO = bookInputService.buildBookRequestDTO();


            responseDTO = bookService.addBook(addBookRequestDTO);
            return addBookResponseDTOResponseFormatter.format(responseDTO);
        }catch (Exception exception){
            exceptionHandler.handleException(exception);
            return FacadeConstants.MESSAGE_FAILURE;
        }
    }

    @Override
    public String getBookById() {
        Optional<GetBookResponseDTO> responseDTO;
        try{
            GetBookByIdRequestDTO getBookByIdRequestDTO = bookInputService.buildGetBookByIdRequestDTO();

            responseDTO = bookService.getBookById(getBookByIdRequestDTO);
            return getBookResponseDTOResponseFormatter.format(responseDTO.orElseThrow((() -> {
                ValidationErrorDTO errorDTO = new ValidationErrorDTO();
                errorDTO.setMessage(ValidationExceptionConstants.BOOK_RECORD_NOT_FOUND.formatted(getBookByIdRequestDTO.getBookRecordId()));
                return new ValidationException(errorDTO);
            })));
        } catch (Exception exception){
            exceptionHandler.handleException(exception);
            return  FacadeConstants.MESSAGE_FAILURE;
        }
    }
}
