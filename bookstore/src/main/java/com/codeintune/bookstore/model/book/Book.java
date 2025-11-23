package com.codeintune.bookstore.model.book;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Book {

    private Long bookId;
    private String title;
    private String isbn;
    private String author;
    private String publisher;
}
