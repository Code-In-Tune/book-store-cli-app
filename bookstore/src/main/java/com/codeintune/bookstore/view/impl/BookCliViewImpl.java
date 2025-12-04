package com.codeintune.bookstore.view.impl;

import com.codeintune.bookstore.dto.book.*;
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
    private final ResponseFormatter<GetBooksResponseDTO> getBooksResponseFormatterResponseFormatter;

    @Override
    public String addBook() {
        AddBookResponseDTO responseDTO;
        try {
            AddBookRequestDTO addBookRequestDTO = bookInputService.buildBookRequestDTO();


            responseDTO = bookService.addBook(addBookRequestDTO);
            return addBookResponseDTOResponseFormatter.format(responseDTO);
        } catch (Exception exception) {
            exceptionHandler.handleException(exception);
            return FacadeConstants.MESSAGE_FAILURE;
        }
    }

    @Override
    public String getBookById() {
        Optional<GetBookResponseDTO> responseDTO;
        try {
            GetBookByIdRequestDTO getBookByIdRequestDTO = bookInputService.buildGetBookByIdRequestDTO();

            responseDTO = bookService.getBookById(getBookByIdRequestDTO);
            return getBookResponseDTOResponseFormatter.format(responseDTO.orElseThrow((() -> {
                ValidationErrorDTO errorDTO = new ValidationErrorDTO();
                errorDTO.setMessage(ValidationExceptionConstants.BOOK_RECORD_NOT_FOUND.formatted(getBookByIdRequestDTO.getBookRecordId()));
                return new ValidationException(errorDTO);
            })));
        } catch (Exception exception) {
            exceptionHandler.handleException(exception);
            return FacadeConstants.MESSAGE_FAILURE;
        }
    }

    @Override
    public String getByAuthor() {
        try {
            GetBooksByAuthorRequestDTO getBooksByAuthorRequestDTO = bookInputService.buildGetBooksByAuthorRequestDTO();

            GetBooksResponseDTO responseDTO = bookService.getBooksByAuthor(getBooksByAuthorRequestDTO);

            if(responseDTO.getBooks().isEmpty()){
                ValidationErrorDTO errorDTO = new ValidationErrorDTO();
                errorDTO.setMessage(ValidationExceptionConstants.BOOKS_NOT_FOUND);
                throw new ValidationException(errorDTO);
            }

            return getBooksResponseFormatterResponseFormatter.format(responseDTO);
        } catch (Exception exception) {
            exceptionHandler.handleException(exception);
            return FacadeConstants.MESSAGE_FAILURE;
        }
    }

    @Override
    public String getByTitle() {
        try {
            GetBooksByTitleRequestDTO getBooksByTitleRequestDTO = bookInputService.buildGetBooksByTitleRequestDTO();

            GetBooksResponseDTO responseDTO = bookService.getBooksByTitle(getBooksByTitleRequestDTO);

            if(responseDTO.getBooks().isEmpty()){
                ValidationErrorDTO errorDTO = new ValidationErrorDTO();
                errorDTO.setMessage(ValidationExceptionConstants.BOOKS_NOT_FOUND);
                throw new ValidationException(errorDTO);
            }

            return getBooksResponseFormatterResponseFormatter.format(responseDTO);
        } catch (Exception exception) {
            exceptionHandler.handleException(exception);
            return FacadeConstants.MESSAGE_FAILURE;
        }
    }


    @Override
    public String updateById() {
        try {
            UpdateBookByIdRequestDTO updateBookByIdRequestDTO = bookInputService.buildUpdateBookByIdRequestDTO();

            Optional<GetBookResponseDTO> responseDTO = bookService.updateBookById(updateBookByIdRequestDTO);

            return getBookResponseDTOResponseFormatter.format(responseDTO.orElseThrow(() -> {
                        ValidationErrorDTO errorDTO = new ValidationErrorDTO();
                        errorDTO.setMessage(ValidationExceptionConstants.BOOK_RECORD_NOT_FOUND.formatted(updateBookByIdRequestDTO.getBookRecordId()));
                        return new ValidationException(errorDTO);
                    }
            ));
        }catch (Exception exception) {
            exceptionHandler.handleException(exception);
            return FacadeConstants.MESSAGE_FAILURE;
        }
    }

    @Override
    public String addBookQuantity() {
        try{
            UpdateBookQuantityByIdRequestDTO updateBookQuantityByIdRequestDTO = bookInputService.buildUpdateBookQuantityByIdRequestDTO();
            Optional<GetBookResponseDTO> responseDTO = bookService.updateBookQuantityById(updateBookQuantityByIdRequestDTO);

            return getBookResponseDTOResponseFormatter.format(responseDTO.orElseThrow(() -> {
                ValidationErrorDTO errorDTO = new ValidationErrorDTO();
                errorDTO.setMessage(ValidationExceptionConstants.BOOK_RECORD_NOT_FOUND.formatted(updateBookQuantityByIdRequestDTO.getBookRecordId()));
                return new ValidationException(errorDTO);
            }));
        } catch (Exception exception) {
            exceptionHandler.handleException(exception);
            return FacadeConstants.MESSAGE_FAILURE;
        }
    }

    @Override
    public String removeBookById() {
        GetBookByIdRequestDTO requestDTO = bookInputService.buildGetBookByIdRequestDTO();
        bookService.deleteBookById(requestDTO);
        return FacadeConstants.MESSAGE_COMPLETED;
    }
}
