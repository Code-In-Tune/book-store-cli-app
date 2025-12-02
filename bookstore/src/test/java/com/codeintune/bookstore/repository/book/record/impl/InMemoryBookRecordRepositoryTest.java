package com.codeintune.bookstore.repository.book.record.impl;


import com.codeintune.bookstore.model.book.Book;
import com.codeintune.bookstore.model.book.BookRecord;
import com.codeintune.bookstore.model.book.enums.Availability;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;


public class InMemoryBookRecordRepositoryTest {

    private InMemoryBookRecordRepository repository;

    @BeforeEach
    public void setup(){
        BookRecord bookRecord = new BookRecord();
        bookRecord.setBookId(1L);
        bookRecord.setAvailability(Availability.IN_STOCK);
        bookRecord.setPrice(BigDecimal.ZERO);
        bookRecord.setQuantity(1);

        Map<Long, BookRecord> bookRecords = new HashMap<>();

        repository = new InMemoryBookRecordRepository(bookRecords);
        repository.save(bookRecord);
    }


    @Test
    public void testFindAll() {
        Assertions.assertTrue(repository
                .findAll()
                .stream()
                .map(BookRecord::getBookRecordId)
                .anyMatch(id -> id.equals(1L))
        );
    }

    @Test
    public void testFindById() {
        Assertions.assertTrue(repository
                .findById(1L)
                .isPresent());
    }

    @Test
    public void testSave() {
        BookRecord bookRecord = new BookRecord();
        bookRecord.setBookId(2L);
        bookRecord.setAvailability(Availability.IN_STOCK);
        bookRecord.setPrice(BigDecimal.ZERO);
        bookRecord.setQuantity(1);


        repository.save(bookRecord);

        Assertions.assertTrue(repository.findById(2L).isPresent());
    }


    @Test
    public void testDelete() {

        // Mock data
        Book bookData = new Book();
        bookData.setTitle("Book 1");
        bookData.setAuthor("Author 1");
        bookData.setIsbn("ISBN 1");
        BookRecord bookRecord = new BookRecord();
        bookRecord.setBookId(2L);
        bookRecord.setAvailability(Availability.IN_STOCK);
        bookRecord.setPrice(BigDecimal.ZERO);
        bookRecord.setQuantity(1);


        repository.save(bookRecord);
        // check
        Assertions.assertTrue(repository.findById(2L).isPresent());

        repository.deleteById(2L);
        // verify
        Assertions.assertFalse(repository.findById(2L).isPresent());
    }

    @Test
    public void testFindByBookId(){
        Assertions.assertTrue(repository.findByBookId(1L).isPresent());
    }
}
