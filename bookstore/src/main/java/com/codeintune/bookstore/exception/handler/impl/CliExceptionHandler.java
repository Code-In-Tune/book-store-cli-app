package com.codeintune.bookstore.exception.handler.impl;

import com.codeintune.bookstore.exception.OperationAbortedException;
import com.codeintune.bookstore.exception.ValidationException;
import com.codeintune.bookstore.exception.handler.ExceptionHandler;
import org.springframework.stereotype.Component;

/**
 * Command line exception handler: catches any exception in the domain
 * and prints a describing message
 */
@Component
public class CliExceptionHandler implements ExceptionHandler {

    @Override
    public void handleException(Exception exception) {
        if(exception instanceof ValidationException ex){
            System.out.println(ex.getValidationErrorDTO().getMessage());
        } else if (exception instanceof OperationAbortedException ex) {
            System.out.println(ex.getMessage());
        } else {
            System.out.println(exception.getMessage());
        }
    }
}
