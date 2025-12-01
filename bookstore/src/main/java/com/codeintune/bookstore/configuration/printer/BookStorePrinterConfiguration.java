package com.codeintune.bookstore.configuration.printer;

import com.codeintune.bookstore.printer.BookStorePrinter;
import com.codeintune.bookstore.printer.impl.BookStorePrinterImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BookStorePrinterConfiguration {


    @Bean
    public BookStorePrinter bookStorePrinter() {
        return new BookStorePrinterImpl();
    }
}
