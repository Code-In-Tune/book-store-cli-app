package com.codeintune.bookstore.view.impl;

import com.codeintune.bookstore.configuration.view.BookViewConfiguration;
import com.codeintune.bookstore.dto.book.*;
import com.codeintune.bookstore.error.ValidationErrorDTO;
import com.codeintune.bookstore.exception.ValidationException;
import com.codeintune.bookstore.exception.handler.ExceptionHandler;
import com.codeintune.bookstore.formatter.ResponseFormatter;
import com.codeintune.bookstore.service.book.BookInputService;
import com.codeintune.bookstore.service.book.BookService;
import com.codeintune.bookstore.testutils.seeder.book.dto.*;
import com.codeintune.bookstore.utils.constants.exception.ValidationExceptionConstants;
import com.codeintune.bookstore.utils.constants.facade.FacadeConstants;
import com.codeintune.bookstore.view.BookCliView;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

@ContextConfiguration(classes = {BookViewConfiguration.class})
@ExtendWith(SpringExtension.class)
class BookCliViewImplTest {

    private final static String MOCKED_FORMATTER_OUTPUT = "FORMATTED_STRING";
    private final static String ERROR_MESSAGE_MOCK = "ERROR_MESSAGE_MOCK";

    @Autowired
    private BookCliView bookCliView;
    @MockitoBean
    private ResponseFormatter<AddBookResponseDTO> addBookResponseDTOResponseFormatter;
    @MockitoBean
    private ResponseFormatter<GetBookResponseDTO> getBookResponseDTOResponseFormatter;
    @MockitoBean
    private ResponseFormatter<GetBooksResponseDTO> getBooksResponseDTOResponseFormatter;
    @MockitoBean
    private BookService bookService;
    @MockitoBean
    private ExceptionHandler exceptionHandler;
    @MockitoBean
    private BookInputService bookInputService;

    @Test
    void addBookTest_WhenNoExceptionThrown() {
        AddBookRequestDTO addBookRequestDTO = AddBookRequestDTOSeeder.generateRequest();
        AddBookResponseDTO addBookResponseDTO = AddBookResponseDTOSeeder.generateResponse();

        Mockito.when(bookInputService.buildBookRequestDTO())
                .thenReturn(addBookRequestDTO);
        Mockito.when(bookService.addBook(Mockito.any(AddBookRequestDTO.class)))
                .thenReturn(addBookResponseDTO);
        Mockito.when(addBookResponseDTOResponseFormatter.format(addBookResponseDTO))
                .thenReturn(MOCKED_FORMATTER_OUTPUT);

        String message = bookCliView.addBook();

        Assertions.assertEquals(MOCKED_FORMATTER_OUTPUT, message);

    }

    @Test
    public void addBookTest_WhenValidationExceptionThrown(){

        ValidationErrorDTO validationErrorDTO = new ValidationErrorDTO();
        validationErrorDTO.setMessage(ERROR_MESSAGE_MOCK);

        Mockito.when(bookInputService.buildBookRequestDTO())
                .thenThrow(new ValidationException(validationErrorDTO));

        Mockito.doAnswer((invocationOnMock -> {
            ValidationException exception = invocationOnMock.getArgument(0, ValidationException.class);

            Assertions.assertEquals(ERROR_MESSAGE_MOCK, exception.getValidationErrorDTO().getMessage());

            return null;
        })).when(exceptionHandler).handleException(Mockito.any(Exception.class));

        String message =  bookCliView.addBook();

        Mockito.verify(exceptionHandler, Mockito.times(1)).handleException(Mockito.any(Exception.class));

        Assertions.assertEquals(FacadeConstants.MESSAGE_FAILURE, message);

    }

    @Test
    void getBookByIdTest_whenNoExceptionThrown() {
        GetBookByIdRequestDTO getBookByIdRequestDTO = GetBookByIdRequestDTOSeeder.generateRequest();
        GetBookResponseDTO getBookResponseDTO = GetBookResponseDTOSeeder.generateResponse();

        Mockito.when(bookInputService.buildGetBookByIdRequestDTO())
                .thenReturn(getBookByIdRequestDTO);
        Mockito.when(bookService.getBookById(Mockito.any(GetBookByIdRequestDTO.class)))
                .thenReturn(Optional.of(getBookResponseDTO));
        Mockito.when(getBookResponseDTOResponseFormatter.format(getBookResponseDTO))
                .thenReturn(MOCKED_FORMATTER_OUTPUT);

        String message = bookCliView.getBookById();

        Assertions.assertEquals(MOCKED_FORMATTER_OUTPUT, message);
    }

    @Test
    public void getBookByIdTest_WhenNoBookFound(){
        GetBookByIdRequestDTO getBookByIdRequestDTO = GetBookByIdRequestDTOSeeder.generateRequest();
        Mockito.when(bookInputService.buildGetBookByIdRequestDTO())
                .thenReturn(getBookByIdRequestDTO);
        Mockito.when(bookService.getBookById(Mockito.any(GetBookByIdRequestDTO.class)))
                .thenReturn(Optional.empty());

        Mockito.doAnswer((invocationOnMock) -> {
            ValidationException exception = invocationOnMock.getArgument(0, ValidationException.class);

            Assertions.assertEquals(ValidationExceptionConstants.BOOK_RECORD_NOT_FOUND.formatted(getBookByIdRequestDTO.getBookRecordId()), exception.getValidationErrorDTO().getMessage());
            return null;
        }).when(exceptionHandler).handleException(Mockito.any(Exception.class));

        String message = bookCliView.getBookById();

        Mockito.verify(exceptionHandler, Mockito.times(1))
                .handleException(Mockito.any(Exception.class));

        Assertions.assertEquals(FacadeConstants.MESSAGE_FAILURE, message);
    }

    @Test
    void getByAuthorTest_WhenNoExceptionThrown() {
        GetBooksByAuthorRequestDTO getBooksByAuthorRequestDTO = GetBooksByAuthorRequestDTOSeeder.generateRequest();
        GetBookResponseDTO getBookResponseDTO = GetBookResponseDTOSeeder.generateResponse();
        GetBooksResponseDTO responses = new GetBooksResponseDTO();
        responses.setBooks(List.of(getBookResponseDTO));

        Mockito.when(bookInputService.buildGetBooksByAuthorRequestDTO())
                .thenReturn(getBooksByAuthorRequestDTO);
        Mockito.when(bookService.getBooksByAuthor(Mockito.any(GetBooksByAuthorRequestDTO.class)))
                .thenReturn(responses);
        Mockito.when(getBooksResponseDTOResponseFormatter.format(Mockito.any(GetBooksResponseDTO.class)))
                .thenReturn(MOCKED_FORMATTER_OUTPUT);

        String message = bookCliView.getByAuthor();
        Assertions.assertEquals(MOCKED_FORMATTER_OUTPUT, message);
    }

    @Test
    public void getByAuthorTest_WhenNoBookFound(){
        GetBooksByAuthorRequestDTO getBooksByAuthorRequestDTO = GetBooksByAuthorRequestDTOSeeder.generateRequest();
        GetBooksResponseDTO responses = new GetBooksResponseDTO();
        responses.setBooks(List.of());

        Mockito.when(bookInputService.buildGetBooksByAuthorRequestDTO())
                .thenReturn(getBooksByAuthorRequestDTO);
        Mockito.when(bookService.getBooksByAuthor(Mockito.any(GetBooksByAuthorRequestDTO.class)))
                .thenReturn(responses);

        Mockito.doAnswer((invocationOnMock -> {
            ValidationException exception = invocationOnMock.getArgument(0, ValidationException.class);

            Assertions.assertEquals(ValidationExceptionConstants.BOOKS_NOT_FOUND, exception.getValidationErrorDTO().getMessage());

            return null;
        })).when(exceptionHandler).handleException(Mockito.any(Exception.class));

        String message = bookCliView.getByAuthor();

        Mockito.verify(exceptionHandler, Mockito.times(1))
                .handleException(Mockito.any(Exception.class));

        Assertions.assertEquals(FacadeConstants.MESSAGE_FAILURE, message);
    }

    @Test
    void getByTitleTest_WhenNoExceptionThrown() {
        GetBooksByTitleRequestDTO getBooksByTitleRequestDTO = GetBooksByTitleRequestDTOSeeder.generateRequest();
        GetBookResponseDTO getBookResponseDTO = GetBookResponseDTOSeeder.generateResponse();
        GetBooksResponseDTO responses = new GetBooksResponseDTO();
        responses.setBooks(List.of(getBookResponseDTO));

        Mockito.when(bookInputService.buildGetBooksByTitleRequestDTO())
                .thenReturn(getBooksByTitleRequestDTO);
        Mockito.when(bookService.getBooksByTitle(Mockito.any(GetBooksByTitleRequestDTO.class)))
                .thenReturn(responses);
        Mockito.when(getBooksResponseDTOResponseFormatter.format(Mockito.any(GetBooksResponseDTO.class)))
                .thenReturn(MOCKED_FORMATTER_OUTPUT);

        String message = bookCliView.getByTitle();

        Assertions.assertEquals(MOCKED_FORMATTER_OUTPUT, message);
    }

    @Test
    void getByTitleTest_WhenNoBookFound() {
        GetBooksByTitleRequestDTO getBooksByTitleRequestDTO = GetBooksByTitleRequestDTOSeeder.generateRequest();
        GetBooksResponseDTO responses = new GetBooksResponseDTO();
        responses.setBooks(List.of());

        Mockito.when(bookInputService.buildGetBooksByTitleRequestDTO())
                .thenReturn(getBooksByTitleRequestDTO);
        Mockito.when(bookService.getBooksByTitle(Mockito.any(GetBooksByTitleRequestDTO.class)))
                .thenReturn(responses);

        Mockito.doAnswer((invocationOnMock -> {
            ValidationException exception = invocationOnMock.getArgument(0, ValidationException.class);

            Assertions.assertEquals(ValidationExceptionConstants.BOOKS_NOT_FOUND, exception.getValidationErrorDTO().getMessage());

            return null;
        })).when(exceptionHandler).handleException(Mockito.any(Exception.class));

        String message = bookCliView.getByTitle();

        Mockito.verify(exceptionHandler, Mockito.times(1))
                .handleException(Mockito.any(Exception.class));

        Assertions.assertEquals(FacadeConstants.MESSAGE_FAILURE, message);
    }

    @Test
    void updateByIdTest_WhenNoExceptionThrown() {
        UpdateBookByIdRequestDTO updateBookByIdRequestDTO = UpdateBookByIdRequestDTOSeeder.generateRequest();
        GetBookResponseDTO getBookResponseDTO = GetBookResponseDTOSeeder.generateResponse();

        Mockito.when(bookInputService.buildUpdateBookByIdRequestDTO())
                .thenReturn(updateBookByIdRequestDTO);
        Mockito.when(bookService.updateBookById(Mockito.any(UpdateBookByIdRequestDTO.class)))
                .thenReturn(Optional.of(getBookResponseDTO));
        Mockito.when(getBookResponseDTOResponseFormatter.format(Mockito.any(GetBookResponseDTO.class)))
                .thenReturn(MOCKED_FORMATTER_OUTPUT);

        String message = bookCliView.updateById();

        Assertions.assertEquals(MOCKED_FORMATTER_OUTPUT, message);
    }

    @Test
    void updateByIdTest_WhenNoBookFound() {
        UpdateBookByIdRequestDTO updateBookByIdRequestDTO = UpdateBookByIdRequestDTOSeeder.generateRequest();

        Mockito.when(bookInputService.buildUpdateBookByIdRequestDTO())
                .thenReturn(updateBookByIdRequestDTO);
        Mockito.when(bookService.updateBookById(Mockito.any(UpdateBookByIdRequestDTO.class)))
                .thenReturn(Optional.empty());

        Mockito.doAnswer((invocationOnMock -> {
            ValidationException exception = invocationOnMock.getArgument(0, ValidationException.class);

            Assertions.assertEquals(ValidationExceptionConstants.BOOK_RECORD_NOT_FOUND.formatted(updateBookByIdRequestDTO.getBookRecordId()), exception.getValidationErrorDTO().getMessage());

            return null;
        })).when(exceptionHandler).handleException(Mockito.any(Exception.class));

        String message = bookCliView.updateById();

        Mockito.verify(exceptionHandler, Mockito.times(1))
                .handleException(Mockito.any(Exception.class));

        Assertions.assertEquals(FacadeConstants.MESSAGE_FAILURE, message);
    }

    @Test
    void addBookQuantityTest_WhenNoExceptionThrown() {
        UpdateBookQuantityByIdRequestDTO updateBookQuantityByIdRequestDTO = UpdateBookQuantityByIdRequestDTOSeeder.generateRequest();
        GetBookResponseDTO getBookResponseDTO = GetBookResponseDTOSeeder.generateResponse();

        Mockito.when(bookInputService.buildUpdateBookQuantityByIdRequestDTO())
                .thenReturn(updateBookQuantityByIdRequestDTO);
        Mockito.when(bookService.updateBookQuantityById(Mockito.any(UpdateBookQuantityByIdRequestDTO.class)))
                .thenReturn(Optional.of(getBookResponseDTO));
        Mockito.when(getBookResponseDTOResponseFormatter.format(Mockito.any(GetBookResponseDTO.class)))
                .thenReturn(MOCKED_FORMATTER_OUTPUT);

        String message = bookCliView.addBookQuantity();

        Assertions.assertEquals(MOCKED_FORMATTER_OUTPUT, message);
    }

    @Test
    void addBookQuantityTest_WhenNoBookFound() {
        UpdateBookQuantityByIdRequestDTO updateBookQuantityByIdRequestDTO = UpdateBookQuantityByIdRequestDTOSeeder.generateRequest();

        Mockito.when(bookInputService.buildUpdateBookQuantityByIdRequestDTO())
                .thenReturn(updateBookQuantityByIdRequestDTO);
        Mockito.when(bookService.updateBookQuantityById(Mockito.any(UpdateBookQuantityByIdRequestDTO.class)))
                .thenReturn(Optional.empty());

        Mockito.doAnswer((invocationOnMock -> {
            ValidationException exception = invocationOnMock.getArgument(0, ValidationException.class);

            Assertions.assertEquals(ValidationExceptionConstants.BOOK_RECORD_NOT_FOUND.formatted(updateBookQuantityByIdRequestDTO.getBookRecordId()), exception.getValidationErrorDTO().getMessage());

            return null;
        })).when(exceptionHandler).handleException(Mockito.any(Exception.class));

        String message = bookCliView.addBookQuantity();

        Mockito.verify(exceptionHandler, Mockito.times(1))
                .handleException(Mockito.any(Exception.class));

        Assertions.assertEquals(FacadeConstants.MESSAGE_FAILURE, message);

    }

    @Test
    void removeBookByIdTest_WhenNoExceptionThrown() {
        GetBookByIdRequestDTO getBookByIdRequestDTO = GetBookByIdRequestDTOSeeder.generateRequest();

        Mockito.when(bookInputService.buildGetBookByIdRequestDTO())
                .thenReturn(getBookByIdRequestDTO);

        String message = bookCliView.removeBookById();

        Mockito.verify(bookService,Mockito.times(1)).deleteBookById(Mockito.any(GetBookByIdRequestDTO.class));

        Assertions.assertEquals(FacadeConstants.MESSAGE_COMPLETED, message);
    }
}