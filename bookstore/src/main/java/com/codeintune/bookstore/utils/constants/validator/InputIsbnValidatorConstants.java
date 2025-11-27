package com.codeintune.bookstore.utils.constants.validator;

public class InputIsbnValidatorConstants {

    public static final String REG_EXP_ISBN_VALIDATOR = "^(?:[0-9]{9}[0-9X]|97[89][0-9]{10})$";
    public static final String ERROR_MESSAGE = "The ISBN number is invalid: %s";
}
