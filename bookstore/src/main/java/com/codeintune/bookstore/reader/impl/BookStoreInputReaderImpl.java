package com.codeintune.bookstore.reader.impl;

import com.codeintune.bookstore.exception.OperationAbortedException;
import com.codeintune.bookstore.reader.BookStoreInputReader;
import com.codeintune.bookstore.utils.constants.exception.OperationAbortedExceptionConstants;
import com.codeintune.bookstore.utils.constants.facade.FacadeConstants;
import lombok.RequiredArgsConstructor;

import java.util.Scanner;

@RequiredArgsConstructor
public class BookStoreInputReaderImpl implements BookStoreInputReader {

    private final Scanner scanner;

    @Override
    public String readNextLineWithQuitOption() {
        String option = scanner.nextLine().trim();
        if(FacadeConstants.QUIT_OPTION.equalsIgnoreCase(option)){
            throw new OperationAbortedException(OperationAbortedExceptionConstants.OPERATION_ABORTED);
        }
        return option;
    }

    @Override
    public String readNextLine() {
        return scanner.nextLine().trim();
    }
}
