package com.codeintune.bookstore.validator.field;

import lombok.Data;
import com.codeintune.bookstore.validator.Validator;

/**
 * Represents an abstraction of an input of a given field. Used with {@link Validator}
 */
@Data
public class InputField {
    private String field;
    private String value;
}
