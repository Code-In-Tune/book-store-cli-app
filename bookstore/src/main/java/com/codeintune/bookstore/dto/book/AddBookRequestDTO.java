package com.codeintune.bookstore.dto.book;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class AddBookRequestDTO {

    private String title;
    private String author;
    private String isbn;
    private String publisher;
    private String price;
    private String quantity;

}
