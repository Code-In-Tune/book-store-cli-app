package com.codeintune.bookstore.repository.book.record.impl;

import com.codeintune.bookstore.configuration.repository.RepositoryConfiguration;
import com.codeintune.bookstore.model.book.BookRecord;
import com.codeintune.bookstore.repository.book.record.BookRecordRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ContextConfiguration(classes = RepositoryConfiguration.class)
@ExtendWith(SpringExtension.class)
public class InMemoryBookRecordRepositoryInitializationIT {

    @Autowired
    private BookRecordRepository initializedBookRecordRepository;

    @Test
    public void testRepositoryIsPopulatedAtStartup(){
        List<Long> recordIds = initializedBookRecordRepository.findAll()
                .stream()
                .map(BookRecord::getBookId)
                .toList();
        Assertions.assertTrue(recordIds.stream()
                .anyMatch(id -> id.equals(1L)));
        Assertions.assertTrue(recordIds.stream()
                .anyMatch(id -> id.equals(2L)));
    }
}
