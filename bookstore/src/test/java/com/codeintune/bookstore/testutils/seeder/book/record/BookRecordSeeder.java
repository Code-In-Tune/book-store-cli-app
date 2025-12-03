package com.codeintune.bookstore.testutils.seeder.book.record;

import com.codeintune.bookstore.model.book.BookRecord;
import com.codeintune.bookstore.model.book.enums.Availability;

import java.math.BigDecimal;

public class BookRecordSeeder {

    public static BookRecord generateBookRecord()
    {
        BookRecord bookRecord = new BookRecord();

        bookRecord.setBookRecordId(1L);
        bookRecord.setBookId(1L);

        bookRecord.setQuantity(1);
        bookRecord.setAvailability(Availability.IN_STOCK);
        bookRecord.setPrice(BigDecimal.ZERO);

        return bookRecord;

    }
}
