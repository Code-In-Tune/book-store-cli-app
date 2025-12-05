package com.codeintune.bookstore.view;

/**
 * View contract for book domain defining common operations
 */
public interface BookCliView {

    String addBook();
    String getBookById();
    String getByAuthor();
    String getByTitle();
    String updateById();
    String addBookQuantity();
    String removeBookById();
}
