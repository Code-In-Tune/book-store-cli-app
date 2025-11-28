package com.codeintune.bookstore.configuration.formatter;

import com.codeintune.bookstore.dto.book.AddBookResponseDTO;
import com.codeintune.bookstore.dto.book.GetBookResponseDTO;
import com.codeintune.bookstore.formatter.ResponseFormatter;
import com.codeintune.bookstore.formatter.impl.AddBookResponseFormatter;
import com.codeintune.bookstore.formatter.impl.GetBookResponseFormatter;
import com.codeintune.bookstore.utils.constants.formatter.FormatterConfigurationConstants;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FormatterConfiguration {

    @Bean(FormatterConfigurationConstants.ADD_BOOK_RESPONSE_FORMATTER)
    public ResponseFormatter<AddBookResponseDTO>  addBookResponseFormatter() {
        return new AddBookResponseFormatter();
    }

    @Bean(FormatterConfigurationConstants.GET_BOOK_RESPONSE_FORMATTER)
    public ResponseFormatter<GetBookResponseDTO>  getBookResponseFormatter() {
        return new GetBookResponseFormatter();
    }
}
