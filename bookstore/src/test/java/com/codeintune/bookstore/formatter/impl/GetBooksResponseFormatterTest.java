package com.codeintune.bookstore.formatter.impl;

import com.codeintune.bookstore.configuration.formatter.FormatterConfiguration;
import com.codeintune.bookstore.dto.book.GetBookResponseDTO;
import com.codeintune.bookstore.dto.book.GetBooksResponseDTO;
import com.codeintune.bookstore.formatter.ResponseFormatter;
import com.codeintune.bookstore.testutils.seeder.book.dto.GetBookResponseDTOSeeder;
import com.codeintune.bookstore.utils.constants.facade.FacadeConstants;
import com.codeintune.bookstore.utils.constants.formatter.FormatterConfigurationConstants;
import com.codeintune.bookstore.utils.constants.formatter.ResponseFormatterConstants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@ContextConfiguration(classes = {FormatterConfiguration.class})
@ExtendWith(SpringExtension.class)
class GetBooksResponseFormatterTest {

    @Autowired
    @Qualifier(FormatterConfigurationConstants.GET_BOOKS_RESPONSE_FORMATTER)
    private ResponseFormatter<GetBooksResponseDTO> getBooksResponseDTOResponseFormatter;

    @Test
    void getBooksResponseDTOResponseFormatterTest() {
        GetBookResponseDTO content = GetBookResponseDTOSeeder.generateResponse();
        GetBooksResponseDTO response = new GetBooksResponseDTO();
        response.setBooks(List.of(content));

        String messageCompleted = String.format(ResponseFormatterConstants.FORMATTING_STRING_MESSAGE, FacadeConstants.MESSAGE_COMPLETED);
        String formatted = messageCompleted + response.getBooks().stream().map(b -> {

            String bookRecordId = String.format(ResponseFormatterConstants.FORMATTING_STRING_FIELD,ResponseFormatterConstants.BOOK_RECORD_ID_FIELD, b.getBookRecordId());
            String bookId = String.format(ResponseFormatterConstants.FORMATTING_STRING_FIELD,ResponseFormatterConstants.BOOK_ID_FIELD, b.getBookId());
            String title = String.format(ResponseFormatterConstants.FORMATTING_STRING_FIELD,ResponseFormatterConstants.TITLE_FIELD, b.getTitle());
            String author = String.format(ResponseFormatterConstants.FORMATTING_STRING_FIELD,ResponseFormatterConstants.AUTHOR_FIELD, b.getAuthor());
            String isbn = String.format(ResponseFormatterConstants.FORMATTING_STRING_FIELD,ResponseFormatterConstants.ISBN_FIELD, b.getIsbn());
            String price = String.format(ResponseFormatterConstants.FORMATTING_STRING_FIELD,ResponseFormatterConstants.PRICE_FIELD, b.getPrice());
            String quantity = String.format(ResponseFormatterConstants.FORMATTING_STRING_FIELD,ResponseFormatterConstants.QUANTITY_FIELD, b.getQuantity());
            String availability = String.format(ResponseFormatterConstants.FORMATTING_STRING_FIELD,ResponseFormatterConstants.AVAILABILITY_FIELD, b.getAvailability());
            String publisher = String.format(ResponseFormatterConstants.FORMATTING_STRING_FIELD,ResponseFormatterConstants.PUBLISHER_FIELD, b.getPublisher());

            StringBuilder sb = new StringBuilder();

            return sb
                    .append(bookRecordId)
                    .append(bookId)
                    .append(title)
                    .append(author)
                    .append(isbn)
                    .append(price)
                    .append(quantity)
                    .append(availability)
                    .append(publisher)
                    .toString();
        }).collect(Collectors.joining(FacadeConstants.SEPARATOR.concat(FacadeConstants.NEW_LINE_CHARACTER)));

        Assertions.assertEquals(formatted, getBooksResponseDTOResponseFormatter.format(response));
    }
}