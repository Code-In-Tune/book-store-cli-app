package com.codeintune.bookstore.printer.impl;

import com.codeintune.bookstore.configuration.printer.BookStorePrinterConfiguration;
import com.codeintune.bookstore.printer.BookStorePrinter;
import com.codeintune.bookstore.utils.constants.facade.FacadeConstants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

@ContextConfiguration(classes = {BookStorePrinterConfiguration.class})
@ExtendWith(SpringExtension.class)
class BookStorePrinterImplTest {

    private final static String CONTENT = "CONTENT";

    @Autowired
    private BookStorePrinter bookStorePrinter;

    @Test
    void printTest() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(baos));

        bookStorePrinter.print(CONTENT);

        Assertions.assertEquals(CONTENT, baos.toString().trim());
    }

    @Test
    void printSeparatorTest() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(baos));

        bookStorePrinter.printSeparator();

        Assertions.assertEquals(FacadeConstants.SEPARATOR, baos.toString().trim());
    }

    @Test
    void printQuitOptionTest() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(baos));

        bookStorePrinter.printQuitOption();
        Assertions.assertTrue(baos.toString().trim().contains(FacadeConstants.QUIT_MESSAGE));

    }
}