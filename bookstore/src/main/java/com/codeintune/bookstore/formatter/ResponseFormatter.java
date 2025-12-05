package com.codeintune.bookstore.formatter;

/**
 * Response formatter for a given dto or entity returned as output in CLI
 * @param <T> the given response
 */
public interface ResponseFormatter<T> {

    String format(T response);
}
