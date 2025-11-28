package com.codeintune.bookstore.repository.book.data.impl;

import com.codeintune.bookstore.model.book.Book;
import com.codeintune.bookstore.repository.book.data.BookDataRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

/**
 * In Memory Repository implementation for {@link Book} records
 */
@RequiredArgsConstructor
public class InMemoryBookDataRepository implements BookDataRepository {

    private final Map<Long, Book> books;
    private final AtomicLong nextId = new AtomicLong(0L);

    @Override
    public Optional<Book> findById(Long id) {
        return Optional.ofNullable(books.get(id));
    }

    @Override
    public List<Book> findAll() {
        return books.values().stream().toList();
    }

    @Override
    public Book save(Book book) {
        if(book.getBookId() == null) {
            book.setBookId(nextId.incrementAndGet());
        }
        return books.put(book.getBookId(), book);
    }

    @Override
    public void deleteById(Long id) {
        books.remove(id);
    }

    @Override
    public List<Book> findAllByTitle(String title) {
        return books.values().stream().filter(book -> {
            String normalizedTitle = title.toLowerCase();
            String titleFound = book.getTitle();
            String normalizedTitleFound = titleFound.toLowerCase();
            return normalizedTitleFound.contains(normalizedTitle);
        }).toList();
    }

    @Override
    public List<Book> findAllByAuthor(String author) {
        return books.values().stream().filter(book -> {
            String normalizedAuthor = author.toLowerCase();
            String authorFound = book.getAuthor();
            String normalizedAuthorFound = authorFound.toLowerCase();
            return normalizedAuthorFound.contains(normalizedAuthor);
        }).toList();
    }
}
