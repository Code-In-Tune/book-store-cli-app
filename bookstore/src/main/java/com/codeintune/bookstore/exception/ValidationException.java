package com.codeintune.bookstore.exception;

import com.codeintune.bookstore.error.ValidationErrorDTO;
import lombok.Getter;


/**
 * Validation runtime exception thrown whenever a field or entity is invalid.
 */
@Getter
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
