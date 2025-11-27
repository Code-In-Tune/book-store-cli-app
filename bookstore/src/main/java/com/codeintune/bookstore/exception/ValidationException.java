package com.codeintune.bookstore.exception;

import com.codeintune.bookstore.error.ValidationErrorDTO;

public class ValidationException extends RuntimeException{

    private final ValidationErrorDTO validationErrorDTO;

    public ValidationException(ValidationErrorDTO validationErrorDTO){
        this.validationErrorDTO = validationErrorDTO;
    }

    public ValidationException(String message,  ValidationErrorDTO validationErrorDTO){
        super(message);
        this.validationErrorDTO = validationErrorDTO;
    }
}
