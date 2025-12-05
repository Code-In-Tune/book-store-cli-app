package com.codeintune.bookstore.printer;

/**
 * Contract defining operations to be carried out by a printer implementation
 */
public interface BookStorePrinter {

    void print(String content);

    void printSeparator();

    void printQuitOption();
}
