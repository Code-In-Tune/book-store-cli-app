package com.codeintune.bookstore.configuration.reader;

import com.codeintune.bookstore.reader.BookStoreInputReader;
import com.codeintune.bookstore.reader.impl.BookStoreInputReaderImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Scanner;

@Configuration
public class BookStoreInputReaderConfiguration {

    @Bean
    public BookStoreInputReader bookStoreInputReader() {
        return new BookStoreInputReaderImpl(new Scanner(System.in));
    }

}
