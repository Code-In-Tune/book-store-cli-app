package com.codeintune.bookstore.dto.book;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class GetBookResponseDTO {

    private String bookId;
    private String bookRecordId;
    private String title;
    private String author;
    private String isbn;
    private String publisher;
    private String price;
    private String quantity;
    private String availability;

}
