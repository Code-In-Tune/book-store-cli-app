package com.codeintune.bookstore.repository.book.impl;

import com.codeintune.bookstore.configuration.repository.RepositoryConfiguration;
import com.codeintune.bookstore.model.book.Book;
import com.codeintune.bookstore.model.book.BookRecord;
import com.codeintune.bookstore.repository.book.BookRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ContextConfiguration(classes = RepositoryConfiguration.class)
@ExtendWith(SpringExtension.class)
public class InMemoryBookRepositoryInitializationIT {

    @Autowired
    private BookRepository initializedBookRepository;

    @Test
    public void testRepositoryIsPopulatedAtStartup(){
        List<String> titles = initializedBookRepository.findAll()
                .stream()
                .map(BookRecord::getBookData)
                .map(Book::getTitle)
                .toList();
        Assertions.assertTrue(titles.stream()
                .anyMatch(title -> title.equals("Alice In Wonderland")));
        Assertions.assertTrue(titles.stream()
                .anyMatch(title -> title.equals("Through The Looking Glass")));
    }
}
