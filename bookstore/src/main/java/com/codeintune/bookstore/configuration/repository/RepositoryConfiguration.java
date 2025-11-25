package com.codeintune.bookstore.configuration.repository;

import com.codeintune.bookstore.repository.book.BookRepository;
import com.codeintune.bookstore.repository.book.impl.InMemoryBookRepository;
import com.codeintune.bookstore.repository.sale.SaleRepository;
import com.codeintune.bookstore.repository.sale.impl.InMemorySaleRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

@Configuration
public class RepositoryConfiguration {

    @Bean
    public BookRepository bookRepository(){
        return new InMemoryBookRepository(new HashMap<>());
    }

    @Bean
    public SaleRepository saleRepository(){
        return new InMemorySaleRepository(new HashMap<>());
    }
}
