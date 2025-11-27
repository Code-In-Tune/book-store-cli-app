package com.codeintune.bookstore.configuration.repository;

import com.codeintune.bookstore.repository.book.data.BookDataRepository;
import com.codeintune.bookstore.repository.book.data.impl.InMemoryBookDataRepository;
import com.codeintune.bookstore.repository.book.record.BookRecordRepository;
import com.codeintune.bookstore.repository.book.record.impl.InMemoryBookRecordRepository;
import com.codeintune.bookstore.repository.sale.SaleRepository;
import com.codeintune.bookstore.repository.sale.impl.InMemorySaleRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

@Configuration
public class RepositoryConfiguration {


    @Bean
    public BookRecordRepository bookRecordRepository(){
        return new InMemoryBookRecordRepository(new HashMap<>());
    }

    @Bean
    public BookDataRepository bookDataRepository(){
        return new InMemoryBookDataRepository(new HashMap<>());
    }

    @Bean
    public SaleRepository saleRepository(){
        return new InMemorySaleRepository(new HashMap<>());
    }
}
