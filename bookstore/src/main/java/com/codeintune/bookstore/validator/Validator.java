package com.codeintune.bookstore.validator;

/**
 * Contract defining a validator for any given input
 * @param <T> the object to validate
 */
public interface Validator<T> {

    void validate(T t);
}
