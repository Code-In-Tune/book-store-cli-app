package com.codeintune.bookstore.formatter.impl;

import com.codeintune.bookstore.configuration.formatter.FormatterConfiguration;
import com.codeintune.bookstore.dto.book.AddBookResponseDTO;
import com.codeintune.bookstore.formatter.ResponseFormatter;
import com.codeintune.bookstore.testutils.seeder.book.dto.AddBookResponseDTOSeeder;
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

@ContextConfiguration(classes = {FormatterConfiguration.class})
@ExtendWith(SpringExtension.class)
class AddBookResponseFormatterTest {

    @Autowired
    @Qualifier(FormatterConfigurationConstants.ADD_BOOK_RESPONSE_FORMATTER)
    private ResponseFormatter<AddBookResponseDTO> addBookResponseFormatter;

    @Test
    public void testAddBookResponseFormatter() {
        AddBookResponseDTO response = AddBookResponseDTOSeeder.generateResponse();

        String messageCompleted = String.format(ResponseFormatterConstants.FORMATTING_STRING_MESSAGE, FacadeConstants.MESSAGE_COMPLETED);
        String bookRecordId = String.format(ResponseFormatterConstants.FORMATTING_STRING_FIELD, ResponseFormatterConstants.BOOK_RECORD_ID_FIELD, response.getBookRecordId());
        String bookId = String.format(ResponseFormatterConstants.FORMATTING_STRING_FIELD, ResponseFormatterConstants.BOOK_ID_FIELD, response.getBookId());
        String title = String.format(ResponseFormatterConstants.FORMATTING_STRING_FIELD, ResponseFormatterConstants.TITLE_FIELD, response.getTitle());
        String author = String.format(ResponseFormatterConstants.FORMATTING_STRING_FIELD, ResponseFormatterConstants.AUTHOR_FIELD, response.getAuthor());
        String isbn = String.format(ResponseFormatterConstants.FORMATTING_STRING_FIELD, ResponseFormatterConstants.ISBN_FIELD, response.getIsbn());
        String price = String.format(ResponseFormatterConstants.FORMATTING_STRING_FIELD, ResponseFormatterConstants.PRICE_FIELD, response.getPrice());
        String quantity = String.format(ResponseFormatterConstants.FORMATTING_STRING_FIELD, ResponseFormatterConstants.QUANTITY_FIELD, response.getQuantity());
        String availability = String.format(ResponseFormatterConstants.FORMATTING_STRING_FIELD, ResponseFormatterConstants.AVAILABILITY_FIELD, response.getAvailability());
        String publisher = String.format(ResponseFormatterConstants.FORMATTING_STRING_FIELD, ResponseFormatterConstants.PUBLISHER_FIELD, response.getPublisher());

        StringBuilder sb = new StringBuilder();

        String formatted = sb
                .append(messageCompleted)
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

        Assertions.assertEquals(formatted, addBookResponseFormatter.format(response));
    }

}