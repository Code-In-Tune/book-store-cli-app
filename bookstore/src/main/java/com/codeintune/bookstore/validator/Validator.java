package com.codeintune.bookstore.validator;

public interface Validator<T> {

    void validate(T t);
}
