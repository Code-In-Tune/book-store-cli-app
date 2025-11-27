package com.codeintune.bookstore.error;

import lombok.Data;

@Data
public class ValidationErrorDTO {

    private String field;
    private String message;
}
