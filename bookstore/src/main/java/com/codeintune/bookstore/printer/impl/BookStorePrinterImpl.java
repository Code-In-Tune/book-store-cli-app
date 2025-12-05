package com.codeintune.bookstore.printer.impl;

import com.codeintune.bookstore.printer.BookStorePrinter;
import com.codeintune.bookstore.utils.constants.facade.FacadeConstants;

/**
 * Custom command line printer for book store. Appends messages in console ({@code System.out})
 */
public class BookStorePrinterImpl implements BookStorePrinter {

    @Override
    public void print(String content) {
        System.out.println(content);
    }

    @Override
    public void printSeparator() {
        System.out.println(FacadeConstants.SEPARATOR);
    }

    @Override
    public void printQuitOption() {
        System.out.println(FacadeConstants.SEPARATOR);
        System.out.println(FacadeConstants.QUIT_MESSAGE);
        System.out.println(FacadeConstants.SEPARATOR);
    }
}
