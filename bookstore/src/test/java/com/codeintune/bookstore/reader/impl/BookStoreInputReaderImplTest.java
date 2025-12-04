package com.codeintune.bookstore.reader.impl;

import com.codeintune.bookstore.configuration.reader.BookStoreInputReaderConfiguration;
import com.codeintune.bookstore.exception.OperationAbortedException;
import com.codeintune.bookstore.reader.BookStoreInputReader;
import com.codeintune.bookstore.utils.constants.exception.OperationAbortedExceptionConstants;
import com.codeintune.bookstore.utils.constants.facade.FacadeConstants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

@ContextConfiguration(classes = {BookStoreInputReaderConfiguration.class})
@ExtendWith(SpringExtension.class)
class BookStoreInputReaderImplTest {

    private static final String INPUT = "input";


    @Test
    void readNextLineWithQuitOption_WhenNoExceptionIsThrown() {
        System.setIn(new ByteArrayInputStream(INPUT.getBytes()));
        Scanner in = new Scanner(System.in);
        BookStoreInputReader bookStoreInputReader = new BookStoreInputReaderImpl(in);
        String output = bookStoreInputReader.readNextLineWithQuitOption();
        Assertions.assertEquals(INPUT, output);
    }

    @Test
    void readNextLineWithQuitOption_WhenExceptionIsThrown() {
        System.setIn(new ByteArrayInputStream(FacadeConstants.QUIT_OPTION.getBytes()));
        Scanner in = new Scanner(System.in);
        BookStoreInputReader bookStoreInputReader = new BookStoreInputReaderImpl(in);
        OperationAbortedException exception = Assertions.assertThrows(OperationAbortedException.class, () -> bookStoreInputReader.readNextLineWithQuitOption());

        Assertions.assertEquals(OperationAbortedExceptionConstants.OPERATION_ABORTED, exception.getMessage());
    }

    @Test
    void readNextLineTest() {
        System.setIn(new ByteArrayInputStream(INPUT.getBytes()));
        Scanner in = new Scanner(System.in);
        BookStoreInputReader bookStoreInputReader = new BookStoreInputReaderImpl(in);
        String output = bookStoreInputReader.readNextLine();

        Assertions.assertEquals(INPUT, output);
    }
}