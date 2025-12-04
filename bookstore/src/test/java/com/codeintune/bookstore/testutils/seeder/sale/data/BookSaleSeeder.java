package com.codeintune.bookstore.testutils.seeder.sale.data;

import com.codeintune.bookstore.model.sale.BookSale;

import java.math.BigDecimal;
import java.time.Instant;

public class BookSaleSeeder {

    public static BookSale generateEntity(){
        BookSale bookSale = new BookSale();
        bookSale.setSaleId(1L);
        bookSale.setBookRecordId(1L);
        bookSale.setQuantity(1);
        bookSale.setDateSold(Instant.ofEpochMilli(0L));
        bookSale.setAmount(BigDecimal.ZERO);
        return bookSale;
    }
}
