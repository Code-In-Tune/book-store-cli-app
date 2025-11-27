package com.codeintune.bookstore.exception.handler.impl;

import com.codeintune.bookstore.exception.ValidationException;
import com.codeintune.bookstore.exception.handler.ExceptionHandler;
import org.springframework.stereotype.Component;

@Component
public class CliExceptionHandler implements ExceptionHandler {

    @Override
    public void handleException(Exception exception) {
        if(exception instanceof ValidationException ex){
            System.out.println(ex.getValidationErrorDTO().getMessage());
        }else {
            System.out.println(exception.getMessage());
        }
    }
}
