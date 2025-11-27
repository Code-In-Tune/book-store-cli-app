package com.codeintune.bookstore.configuration.repository;

import com.codeintune.bookstore.model.book.Book;
import com.codeintune.bookstore.model.book.BookRecord;
import com.codeintune.bookstore.model.book.enums.Availability;
import com.codeintune.bookstore.repository.book.data.BookDataRepository;
import com.codeintune.bookstore.repository.book.data.impl.InMemoryBookDataRepository;
import com.codeintune.bookstore.repository.book.record.BookRecordRepository;
import com.codeintune.bookstore.repository.book.record.impl.InMemoryBookRecordRepository;
import com.codeintune.bookstore.repository.sale.SaleRepository;
import com.codeintune.bookstore.repository.sale.impl.InMemorySaleRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class RepositoryConfiguration {

    public static final String IN_MEMORY_BOOK_RECORDS = "IN_MEMORY_BOOK_RECORDS";
    public static final String IN_MEMORY_BOOK_DATA = "IN_MEMORY_BOOK_DATA";

    @Bean
    public BookRecordRepository bookRecordRepository(
            @Qualifier(IN_MEMORY_BOOK_RECORDS) Map<Long, BookRecord> bookRecords
    ){
        return new InMemoryBookRecordRepository(bookRecords);
    }

    @Bean
    public SaleRepository saleRepository(){
        return new InMemorySaleRepository(new HashMap<>());
    }

    @Bean
    public BookDataRepository bookDataRepository(
            @Qualifier(IN_MEMORY_BOOK_DATA) Map<Long, Book> bookData
    ){
        return new InMemoryBookDataRepository(bookData);
    }

    @Bean(IN_MEMORY_BOOK_RECORDS)
    public Map<Long, BookRecord> bookRecords() {
        Map<Long, BookRecord> bookRecords = new HashMap<>();
        BookRecord bookRecord1 = new BookRecord();
        bookRecord1.setBookRecordId(1L);
        bookRecord1.setBookId(1L);
        bookRecord1.setQuantity(1);
        bookRecord1.setPrice(BigDecimal.valueOf(10.50));
        bookRecord1.setAvailability(Availability.IN_STOCK);

        BookRecord bookRecord2 = new BookRecord();
        bookRecord2.setBookRecordId(2L);
        bookRecord2.setBookId(2L);
        bookRecord2.setQuantity(1);
        bookRecord2.setPrice(BigDecimal.valueOf(10.50));
        bookRecord2.setAvailability(Availability.IN_STOCK);

        bookRecords.put(bookRecord1.getBookRecordId(),bookRecord1);
        bookRecords.put(bookRecord2.getBookRecordId(),bookRecord2);

        return bookRecords;
    }

    @Bean(IN_MEMORY_BOOK_DATA)
    public Map<Long, Book> bookData() {
        Map<Long, Book> books = new HashMap<>();

        Book book1 = new Book();
        book1.setBookId(1L);
        book1.setAuthor("Lewis Carroll");
        book1.setIsbn("MOCK_ISBN");
        book1.setTitle("Alice In Wonderland");
        book1.setPublisher("MOCK_PUBLISHER");

        Book book2 = new Book();
        book2.setBookId(2L);
        book2.setAuthor("Lewis Carroll");
        book2.setIsbn("MOCK_ISBN");
        book2.setTitle("Alice Through The Looking Glass");
        book2.setPublisher("MOCK_PUBLISHER");


        books.put(book1.getBookId(), book1);
        books.put(book2.getBookId(), book2);

        return books;

    }
}
