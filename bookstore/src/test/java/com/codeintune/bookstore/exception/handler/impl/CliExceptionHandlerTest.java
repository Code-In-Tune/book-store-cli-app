package com.codeintune.bookstore.exception.handler.impl;

import com.codeintune.bookstore.error.ValidationErrorDTO;
import com.codeintune.bookstore.exception.OperationAbortedException;
import com.codeintune.bookstore.exception.ValidationException;
import com.codeintune.bookstore.exception.handler.ExceptionHandler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

@ContextConfiguration(classes = {CliExceptionHandler.class})
@ExtendWith(SpringExtension.class)
class CliExceptionHandlerTest {

    private final static String ERROR_MESSAGE = "ERROR_MESSAGE";

    @Autowired
    private ExceptionHandler exceptionHandler;

    @Test
    void handleValidationException() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(baos));

        ValidationErrorDTO errorDTO = new ValidationErrorDTO();
        errorDTO.setMessage(ERROR_MESSAGE);
        ValidationException exception = new ValidationException(errorDTO);

        exceptionHandler.handleException(exception);

        Assertions.assertEquals(ERROR_MESSAGE, baos.toString().trim());
    }

    @Test
    void handleOperationAbortedException() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(baos));

        OperationAbortedException exception = new OperationAbortedException(ERROR_MESSAGE);

        exceptionHandler.handleException(exception);

        Assertions.assertEquals(ERROR_MESSAGE, baos.toString().trim());
    }

    @Test
    void handleGenericException(){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(baos));

        Exception exception = new Exception(ERROR_MESSAGE);

        exceptionHandler.handleException(exception);

        Assertions.assertEquals(ERROR_MESSAGE, baos.toString().trim());

    }
}