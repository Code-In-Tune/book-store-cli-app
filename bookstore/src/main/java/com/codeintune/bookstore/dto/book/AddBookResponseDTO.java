package com.codeintune.bookstore.dto.book;

import lombok.Data;

@Data
public class AddBookResponseDTO {

    private String bookId;
    private String bookRecordId;
    private String availability;
    private String title;
    private String author;
    private String isbn;
    private String publisher;
    private String price;
    private String quantity;
}
