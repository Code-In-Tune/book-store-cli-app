package com.codeintune.bookstore.repository.book.impl;

import com.codeintune.bookstore.model.book.BookRecord;
import com.codeintune.bookstore.repository.book.BookRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
public class InMemoryBookRepository implements BookRepository {

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
}
