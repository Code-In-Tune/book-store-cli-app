package com.codeintune.bookstore.error;

import lombok.Data;
import com.codeintune.bookstore.exception.ValidationException;


/**
 * Used in combination with {@link ValidationException} to describe
 * validation errors on fields or entities
 * @see ValidationException
 */
@Data
public class ValidationErrorDTO {

    private String field;
    private String message;
}
