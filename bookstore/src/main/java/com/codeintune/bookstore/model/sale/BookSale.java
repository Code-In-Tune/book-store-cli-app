package com.codeintune.bookstore.model.sale;

import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@ToString
public class BookSale {

    private Long saleId;
    private Long bookId;
    private Instant dateSold;
    private BigDecimal amount;
    private Integer quantity;
}
