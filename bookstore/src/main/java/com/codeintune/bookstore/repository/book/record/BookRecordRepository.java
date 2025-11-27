package com.codeintune.bookstore.repository.book.record;

import com.codeintune.bookstore.model.book.BookRecord;

import java.util.List;
import java.util.Optional;


public interface BookRecordRepository {

    Optional<BookRecord> findById(Long id);
    List<BookRecord> findAll();
    BookRecord save(BookRecord bookRecord);
    void deleteById(Long id);
}
