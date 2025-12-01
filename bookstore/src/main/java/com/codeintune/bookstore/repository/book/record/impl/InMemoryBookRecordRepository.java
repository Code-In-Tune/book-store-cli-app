package com.codeintune.bookstore.repository.book.record.impl;

import com.codeintune.bookstore.model.book.BookRecord;
import com.codeintune.bookstore.repository.book.record.BookRecordRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

/**
 * In Memory Repository implementation for {@link  BookRecord} entities
 */
@RequiredArgsConstructor
public class InMemoryBookRecordRepository implements BookRecordRepository {

    private final Map<Long, BookRecord> bookRecords;
    private final AtomicLong nextId = new AtomicLong(0L);

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
        if(bookRecord.getBookRecordId() == null) {
            bookRecord.setBookRecordId(nextId.incrementAndGet());
        }
        bookRecords.put(bookRecord.getBookRecordId(), bookRecord);
        return bookRecord;
    }

    @Override
    public void deleteById(Long id) {
        bookRecords.remove(id);
    }

    @Override
    public Optional<BookRecord> findByBookId(Long bookId) {
        return bookRecords.values().stream().filter(b -> bookId.equals(b.getBookId())).findFirst();
    }
}
