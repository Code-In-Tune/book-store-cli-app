package com.codeintune.bookstore.reader;

/**
 * Contract defining operations to read inputs from user in book store
 */
public interface BookStoreInputReader {

    String readNextLineWithQuitOption();
    String readNextLine();
}
