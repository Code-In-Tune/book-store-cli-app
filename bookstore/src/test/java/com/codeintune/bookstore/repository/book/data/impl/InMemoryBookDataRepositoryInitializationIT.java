package com.codeintune.bookstore.repository.book.data.impl;

import com.codeintune.bookstore.configuration.repository.RepositoryConfiguration;
import com.codeintune.bookstore.model.book.Book;
import com.codeintune.bookstore.populator.BookDataRepositoryPopulator;
import com.codeintune.bookstore.repository.book.data.BookDataRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ContextConfiguration(classes = {
        RepositoryConfiguration.class,
        BookDataRepositoryPopulator.class
})
@ExtendWith(SpringExtension.class)
public class InMemoryBookDataRepositoryInitializationIT {

    @Autowired
    private BookDataRepository repository;

    @Test
    public void testRepositoryIsPopulatedAtStartup(){
        List<String> titles = repository.findAll()
                .stream().map(Book::getTitle)
                .toList();

        Assertions.assertTrue(titles.contains("Alice In Wonderland"));
        Assertions.assertTrue(titles.contains("Alice Through The Looking Glass"));
    }
}
