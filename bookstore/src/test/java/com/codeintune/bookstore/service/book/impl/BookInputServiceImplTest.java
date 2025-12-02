package com.codeintune.bookstore.service.book.impl;

import com.codeintune.bookstore.configuration.service.book.BookInputServiceConfiguration;
import com.codeintune.bookstore.dto.book.*;
import com.codeintune.bookstore.printer.BookStorePrinter;
import com.codeintune.bookstore.reader.BookStoreInputReader;
import com.codeintune.bookstore.service.book.BookInputService;
import com.codeintune.bookstore.utils.constants.validator.ValidatorConfigurationConstants;
import com.codeintune.bookstore.validator.Validator;
import com.codeintune.bookstore.validator.field.InputField;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {
        BookInputServiceConfiguration.class
})
@ExtendWith(SpringExtension.class)
class BookInputServiceImplTest {

    private final static String ID = "1";
    private final static String TITLE = "MOCK_TITLE";
    private final static String AUTHOR = "MOCK_AUTHOR";
    private final static String ISBN = "MOCK_ISBN";
    private final static String QUANTITY = "MOCK_QUANTITY";
    private final static String PRICE = "MOCK_PRICE";
    private final static String PUBLISHER = "MOCK_PUBLISHER";


    @Autowired
    private BookInputService bookInputService;
    @MockitoBean
    private BookStoreInputReader bookStoreInputReader;
    @MockitoBean
    private BookStorePrinter bookStorePrinter;
    @MockitoBean
    @Qualifier(ValidatorConfigurationConstants.INPUT_NAME_VALIDATOR)
    private Validator<InputField> inputNameValidator;
    @MockitoBean
    @Qualifier(ValidatorConfigurationConstants.INPUT_ISBN_VALIDATOR)
    private Validator<InputField> inputIsbnValidator;
    @MockitoBean
    @Qualifier(ValidatorConfigurationConstants.INPUT_QUANTITY_VALIDATOR)
    private Validator<InputField> inputQuantityValidator;
    @MockitoBean
    @Qualifier(ValidatorConfigurationConstants.INPUT_PRICE_VALIDATOR)
    private Validator<InputField> inputPriceValidator;
    @MockitoBean
    @Qualifier(ValidatorConfigurationConstants.INPUT_ID_VALIDATOR)
    private Validator<InputField> inputIdValidator;

    @Test
    void buildBookRequestDTO() {
        Mockito.when(bookStoreInputReader.readNextLineWithQuitOption())
                .thenReturn(TITLE)
                .thenReturn(AUTHOR)
                .thenReturn(ISBN)
                .thenReturn(QUANTITY)
                .thenReturn(PRICE)
                .thenReturn(PUBLISHER);

        AddBookRequestDTO addBookRequestDTO = bookInputService.buildBookRequestDTO();

        Mockito.verify(inputNameValidator, Mockito.times(3)).validate(Mockito.any(InputField.class));
        Mockito.verify(inputQuantityValidator, Mockito.times(1)).validate(Mockito.any(InputField.class));
        Mockito.verify(inputPriceValidator, Mockito.times(1)).validate(Mockito.any(InputField.class));
        Mockito.verify(inputIsbnValidator, Mockito.times(1)).validate(Mockito.any(InputField.class));



        Assertions.assertAll("Checking fields" ,
                () -> Assertions.assertEquals(TITLE, addBookRequestDTO.getTitle(), "Title field does not match"),
                () -> Assertions.assertEquals(AUTHOR, addBookRequestDTO.getAuthor(),"Author field does not match"),
                () -> Assertions.assertEquals(ISBN, addBookRequestDTO.getIsbn(), "ISBN field does not match"),
                () -> Assertions.assertEquals(QUANTITY, addBookRequestDTO.getQuantity(), "Quantity field does not match"),
                () -> Assertions.assertEquals(PRICE, addBookRequestDTO.getPrice(), "Price field does not match"),
                () -> Assertions.assertEquals(PUBLISHER, addBookRequestDTO.getPublisher(), "Publisher field does not match")
        );
    }

    @Test
    void buildGetBookByIdRequestDTO() {
        Mockito.when(bookStoreInputReader.readNextLineWithQuitOption())
                .thenReturn(ID);

        GetBookByIdRequestDTO getBookByIdRequestDTO = bookInputService.buildGetBookByIdRequestDTO();

        Mockito.verify(inputIdValidator, Mockito.times(1)).validate(Mockito.any(InputField.class));

        Assertions.assertAll("Checking fields",
                () -> Assertions.assertEquals(Long.parseLong(ID), getBookByIdRequestDTO.getBookRecordId())
        );

    }

    @Test
    void buildGetBooksByAuthorRequestDTO() {
        Mockito.when(bookStoreInputReader.readNextLineWithQuitOption())
                .thenReturn(AUTHOR);

        GetBooksByAuthorRequestDTO getBooksByAuthorRequestDTO = bookInputService.buildGetBooksByAuthorRequestDTO();

        Mockito.verify(inputNameValidator, Mockito.times(1)).validate(Mockito.any(InputField.class));

        Assertions.assertAll("Checking fields",
                () -> Assertions.assertEquals(AUTHOR, getBooksByAuthorRequestDTO.getAuthor())
        );
    }

    @Test
    void buildGetBooksByTitleRequestDTO() {
        Mockito.when(bookStoreInputReader.readNextLineWithQuitOption())
                .thenReturn(TITLE);

        GetBooksByTitleRequestDTO getBooksByTitleRequestDTO = bookInputService.buildGetBooksByTitleRequestDTO();

        Mockito.verify(inputNameValidator, Mockito.times(1)).validate(Mockito.any(InputField.class));

        Assertions.assertAll("Checking fields",
                () -> Assertions.assertEquals(TITLE, getBooksByTitleRequestDTO.getTitle())
        );
    }

    @Test
    void buildUpdateBookByIdRequestDTO() {
        Mockito.when(bookStoreInputReader.readNextLineWithQuitOption())
                .thenReturn(ID)
                .thenReturn(TITLE)
                .thenReturn(AUTHOR)
                .thenReturn(ISBN)
                .thenReturn(PRICE)
                .thenReturn(PUBLISHER);

        UpdateBookByIdRequestDTO updateBookByIdRequestDTO = bookInputService.buildUpdateBookByIdRequestDTO();

        Mockito.verify(inputIdValidator, Mockito.times(1)).validate(Mockito.any(InputField.class));
        Mockito.verify(inputNameValidator, Mockito.times(3)).validate(Mockito.any(InputField.class));
        Mockito.verify(inputPriceValidator, Mockito.times(1)).validate(Mockito.any(InputField.class));
        Mockito.verify(inputIsbnValidator, Mockito.times(1)).validate(Mockito.any(InputField.class));

        Assertions.assertAll("Checking fields" ,
                () -> Assertions.assertEquals(ID, updateBookByIdRequestDTO.getBookRecordId()),
                () -> Assertions.assertEquals(TITLE, updateBookByIdRequestDTO.getTitle(), "Title field does not match"),
                () -> Assertions.assertEquals(AUTHOR, updateBookByIdRequestDTO.getAuthor(),"Author field does not match"),
                () -> Assertions.assertEquals(ISBN, updateBookByIdRequestDTO.getIsbn(), "ISBN field does not match"),
                () -> Assertions.assertEquals(PRICE, updateBookByIdRequestDTO.getPrice(), "Price field does not match"),
                () -> Assertions.assertEquals(PUBLISHER, updateBookByIdRequestDTO.getPublisher(), "Publisher field does not match")
        );

    }

    @Test
    void buildUpdateBookQuantityByIdRequestDTO() {
        Mockito.when(bookStoreInputReader.readNextLineWithQuitOption())
                .thenReturn(ID)
                .thenReturn(QUANTITY);


        UpdateBookQuantityByIdRequestDTO updateBookQuantityByIdRequestDTO = bookInputService.buildUpdateBookQuantityByIdRequestDTO();

        Mockito.verify(inputIdValidator, Mockito.times(1)).validate(Mockito.any(InputField.class));
        Mockito.verify(inputQuantityValidator, Mockito.times(1)).validate(Mockito.any(InputField.class));

        Assertions.assertAll("Checking fields",
                () -> Assertions.assertEquals(ID, updateBookQuantityByIdRequestDTO.getBookRecordId()),
                () -> Assertions.assertEquals(QUANTITY, updateBookQuantityByIdRequestDTO.getQuantity())
        );

    }
}