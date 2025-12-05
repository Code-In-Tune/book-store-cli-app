package com.codeintune.bookstore.facade.impl;

import com.codeintune.bookstore.configuration.facade.FacadeConfiguration;
import com.codeintune.bookstore.facade.CliFacade;
import com.codeintune.bookstore.printer.BookStorePrinter;
import com.codeintune.bookstore.reader.BookStoreInputReader;
import com.codeintune.bookstore.utils.constants.facade.FacadeConstants;
import com.codeintune.bookstore.utils.constants.view.ViewConfigurationConstants;
import com.codeintune.bookstore.view.BookCliView;
import com.codeintune.bookstore.view.SaleCliView;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {FacadeConfiguration.class})
@ExtendWith(SpringExtension.class)
class BookCliFacadeTest {

    private final static String MESSAGE_MOCK = "MESSAGE_MOCK";

    @Autowired
    private CliFacade bookCliFacade;
    @MockitoBean
    private BookStoreInputReader bookStoreInputReader;
    @MockitoBean
    private BookStorePrinter bookStorePrinter;
    @MockitoBean
    @Qualifier(ViewConfigurationConstants.BOOK_CLI_VIEW)
    private BookCliView bookCliView;
    @MockitoBean
    @Qualifier(ViewConfigurationConstants.SALE_CLI_VIEW)
    private SaleCliView saleCliView;


    @Test
    void startStore_WhenFirstOptionSelected() {
        Mockito.when(bookStoreInputReader.readNextLine()).thenReturn(FacadeConstants.ADD_BOOK_OPTION)
                .thenReturn(FacadeConstants.QUIT_OPTION);
        Mockito.when(bookCliView.addBook()).thenReturn(MESSAGE_MOCK);

        Mockito.doAnswer((invocationOnMock -> {
            String message = invocationOnMock.getArgument(0, String.class);

            Assertions.assertEquals(MESSAGE_MOCK, message);

            return null;
        })).when(bookStorePrinter).print(MESSAGE_MOCK);

        bookCliFacade.startStore();

        Mockito.verify(bookStorePrinter, Mockito.times(1)).print(MESSAGE_MOCK);
    }

    @Test
    void startStore_WhenSecondOptionSelected() {
        Mockito.when(bookStoreInputReader.readNextLine()).thenReturn(FacadeConstants.GET_BOOK_BY_ID_OPTION)
                .thenReturn(FacadeConstants.QUIT_OPTION);
        Mockito.when(bookCliView.getBookById()).thenReturn(MESSAGE_MOCK);

        Mockito.doAnswer((invocationOnMock -> {
            String message = invocationOnMock.getArgument(0, String.class);

            Assertions.assertEquals(MESSAGE_MOCK, message);

            return null;
        })).when(bookStorePrinter).print(MESSAGE_MOCK);

        bookCliFacade.startStore();

        Mockito.verify(bookStorePrinter, Mockito.times(1)).print(MESSAGE_MOCK);
    }

    @Test
    void startStore_WhenThirdOptionSelected() {
        Mockito.when(bookStoreInputReader.readNextLine()).thenReturn(FacadeConstants.GET_BOOKS_BY_AUTHOR_OPTION)
                .thenReturn(FacadeConstants.QUIT_OPTION);
        Mockito.when(bookCliView.getByAuthor()).thenReturn(MESSAGE_MOCK);

        Mockito.doAnswer((invocationOnMock -> {
            String message = invocationOnMock.getArgument(0, String.class);

            Assertions.assertEquals(MESSAGE_MOCK, message);

            return null;
        })).when(bookStorePrinter).print(MESSAGE_MOCK);

        bookCliFacade.startStore();

        Mockito.verify(bookStorePrinter, Mockito.times(1)).print(MESSAGE_MOCK);
    }


    @Test
    void startStore_WhenFourthOptionSelected() {
        Mockito.when(bookStoreInputReader.readNextLine()).thenReturn(FacadeConstants.GET_BOOKS_BY_TITLE_OPTION)
                .thenReturn(FacadeConstants.QUIT_OPTION);
        Mockito.when(bookCliView.getByTitle()).thenReturn(MESSAGE_MOCK);

        Mockito.doAnswer((invocationOnMock -> {
            String message = invocationOnMock.getArgument(0, String.class);

            Assertions.assertEquals(MESSAGE_MOCK, message);

            return null;
        })).when(bookStorePrinter).print(MESSAGE_MOCK);

        bookCliFacade.startStore();

        Mockito.verify(bookStorePrinter, Mockito.times(1)).print(MESSAGE_MOCK);
    }


    @Test
    void startStore_WhenFifthOptionSelected() {
        Mockito.when(bookStoreInputReader.readNextLine()).thenReturn(FacadeConstants.UPDATE_BOOK_OPTION)
                .thenReturn(FacadeConstants.QUIT_OPTION);
        Mockito.when(bookCliView.updateById()).thenReturn(MESSAGE_MOCK);

        Mockito.doAnswer((invocationOnMock -> {
            String message = invocationOnMock.getArgument(0, String.class);

            Assertions.assertEquals(MESSAGE_MOCK, message);

            return null;
        })).when(bookStorePrinter).print(MESSAGE_MOCK);

        bookCliFacade.startStore();

        Mockito.verify(bookStorePrinter, Mockito.times(1)).print(MESSAGE_MOCK);
    }

    @Test
    void startStore_WhenSixthOptionSelected() {
        Mockito.when(bookStoreInputReader.readNextLine()).thenReturn(FacadeConstants.ADD_BOOK_QUANTITY)
                .thenReturn(FacadeConstants.QUIT_OPTION);
        Mockito.when(bookCliView.addBookQuantity()).thenReturn(MESSAGE_MOCK);

        Mockito.doAnswer((invocationOnMock -> {
            String message = invocationOnMock.getArgument(0, String.class);

            Assertions.assertEquals(MESSAGE_MOCK, message);

            return null;
        })).when(bookStorePrinter).print(MESSAGE_MOCK);

        bookCliFacade.startStore();

        Mockito.verify(bookStorePrinter, Mockito.times(1)).print(MESSAGE_MOCK);
    }


    @Test
    void startStore_WhenSeventhOptionSelected() {
        Mockito.when(bookStoreInputReader.readNextLine()).thenReturn(FacadeConstants.REMOVE_BOOK_BY_ID_OPTION)
                .thenReturn(FacadeConstants.QUIT_OPTION);
        Mockito.when(bookCliView.removeBookById()).thenReturn(MESSAGE_MOCK);

        Mockito.doAnswer((invocationOnMock -> {
            String message = invocationOnMock.getArgument(0, String.class);

            Assertions.assertEquals(MESSAGE_MOCK, message);

            return null;
        })).when(bookStorePrinter).print(MESSAGE_MOCK);

        bookCliFacade.startStore();

        Mockito.verify(bookStorePrinter, Mockito.times(1)).print(MESSAGE_MOCK);
    }

    @Test
    void startStore_WhenEighthOptionSelected() {
        Mockito.when(bookStoreInputReader.readNextLine()).thenReturn(FacadeConstants.REGISTER_SALE_OPTION)
                .thenReturn(FacadeConstants.QUIT_OPTION);
        Mockito.when(saleCliView.registerSale()).thenReturn(MESSAGE_MOCK);

        Mockito.doAnswer((invocationOnMock -> {
            String message = invocationOnMock.getArgument(0, String.class);

            Assertions.assertEquals(MESSAGE_MOCK, message);

            return null;
        })).when(bookStorePrinter).print(MESSAGE_MOCK);

        bookCliFacade.startStore();

        Mockito.verify(bookStorePrinter, Mockito.times(1)).print(MESSAGE_MOCK);
    }


    @Test
    void startStore_WhenNinthOptionSelected() {
        Mockito.when(bookStoreInputReader.readNextLine()).thenReturn(FacadeConstants.SHOW_SALES_OPTION)
                .thenReturn(FacadeConstants.QUIT_OPTION);
        Mockito.when(saleCliView.showSales()).thenReturn(MESSAGE_MOCK);

        Mockito.doAnswer((invocationOnMock -> {
            String message = invocationOnMock.getArgument(0, String.class);

            Assertions.assertEquals(MESSAGE_MOCK, message);

            return null;
        })).when(bookStorePrinter).print(MESSAGE_MOCK);

        bookCliFacade.startStore();

        Mockito.verify(bookStorePrinter, Mockito.times(1)).print(MESSAGE_MOCK);
    }
}