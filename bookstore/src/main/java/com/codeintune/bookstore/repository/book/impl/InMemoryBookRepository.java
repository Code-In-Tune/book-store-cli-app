package com.codeintune.bookstore.repository.book.impl;

import com.codeintune.bookstore.model.book.Book;
import com.codeintune.bookstore.model.book.BookRecord;
import com.codeintune.bookstore.model.book.enums.Availability;
import com.codeintune.bookstore.repository.book.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * In Memory Repository implementation for {@link  BookRecord} entities
 */
@RequiredArgsConstructor
@Repository
public class InMemoryBookRepository implements BookRepository, InitializingBean {

    private final Map<Long, BookRecord> bookRecords;

    @Override
    public Optional<BookRecord> findById(Long id) {
        return Optional.ofNullable(bookRecords.get(id));
    }

    @Override
    public List<BookRecord> findAll() {
        return bookRecords.values().stream().toList();
    }

    @Override
    public BookRecord save(BookRecord bookRecord) {
        return bookRecords.put(bookRecord.getBookRecordId(), bookRecord);
    }

    @Override
    public void deleteById(Long id) {
        bookRecords.remove(id);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Map<Long, BookRecord> bookRecordsStart = new HashMap<>();
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
        book2.setTitle("Through The Looking Glass");
        book2.setPublisher("MOCK_PUBLISHER");


        BookRecord bookRecord1 = new BookRecord();
        bookRecord1.setBookRecordId(1L);
        bookRecord1.setBookData(book1);
        bookRecord1.setQuantity(1);
        bookRecord1.setPrice(BigDecimal.valueOf(10.50));
        bookRecord1.setAvailability(Availability.IN_STOCK);

        BookRecord bookRecord2 = new BookRecord();
        bookRecord1.setBookRecordId(2L);
        bookRecord1.setBookData(book2);
        bookRecord1.setQuantity(1);
        bookRecord1.setPrice(BigDecimal.valueOf(10.50));
        bookRecord1.setAvailability(Availability.IN_STOCK);

        bookRecordsStart.put(bookRecord1.getBookRecordId(),bookRecord1);
        bookRecordsStart.put(bookRecord2.getBookRecordId(),bookRecord2);

        bookRecords.putAll(bookRecordsStart);
    }
}
