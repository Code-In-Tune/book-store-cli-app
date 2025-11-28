package com.codeintune.bookstore.formatter;

public interface ResponseFormatter<T> {

    String format(T response);
}
