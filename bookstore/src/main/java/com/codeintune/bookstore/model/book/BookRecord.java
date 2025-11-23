package com.codeintune.bookstore.model.book;

import com.codeintune.bookstore.model.book.enums.Availability;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;

@Data
@ToString
public class BookRecord {

    private Book bookData;
    private BigDecimal price;
    private Availability availability;
    private Long quantity;
}
