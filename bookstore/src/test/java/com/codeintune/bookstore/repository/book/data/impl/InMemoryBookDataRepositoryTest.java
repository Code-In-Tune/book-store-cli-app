package com.codeintune.bookstore.repository.book.data.impl;

import com.codeintune.bookstore.model.book.Book;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class InMemoryBookDataRepositoryTest {

    private InMemoryBookDataRepository repository;

    @BeforeEach
    public void setup(){
        Book book = new Book();
        book.setBookId(1L);
        book.setPublisher("MOCK_PUBLISHER");
        book.setAuthor("MOCK_AUTHOR");
        book.setIsbn("MOCK_ISBN");
        book.setTitle("MOCK_TITLE");

        Map<Long, Book> books = new HashMap<>();
        books.put(book.getBookId(), book);

        repository = new InMemoryBookDataRepository(books);
    }

    @Test
    public void testFindAll(){
        Assertions.assertTrue(repository.findAll()
                .stream()
                .map(Book::getBookId)
                .anyMatch(id -> Long.valueOf(1L).equals(id)));
    }

    @Test
    public void testFindByAuthor(){
        Assertions.assertTrue(repository
                .findAllByAuthor("AUTHOR")
                .stream()
                .map(Book::getAuthor)
                .anyMatch("MOCK_AUTHOR"::equals));
    }

    @Test
    public void testFindByTitle(){
        Assertions.assertTrue(repository
                .findAllByTitle("TITLE")
                .stream()
                .map(Book::getTitle)
                .anyMatch("MOCK_TITLE"::equals)
        );
    }

    @Test
    public void testFindById(){
        Assertions.assertTrue(repository.findById(1L).isPresent());
    }

    @Test
    public void testSave(){
        Book book = new Book();
        book.setBookId(2L);

        repository.save(book);

        // Check if exists

        Assertions.assertTrue(repository.findById(2L).isPresent());
    }

    @Test
    public void testDeleteById(){
        Book book = new Book();
        book.setBookId(2L);

        repository.save(book);

        // Check if it exists

        Assertions.assertTrue(repository.findById(2L).isPresent());

        // Delete record

        repository.deleteById(2L);

        // Check if deleted

        Assertions.assertFalse(repository.findById(2L).isPresent());
    }
}
