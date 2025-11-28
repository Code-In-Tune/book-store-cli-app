package com.codeintune.bookstore.formatter.impl;

import com.codeintune.bookstore.dto.book.AddBookResponseDTO;
import com.codeintune.bookstore.formatter.ResponseFormatter;
import com.codeintune.bookstore.utils.constants.facade.FacadeConstants;
import com.codeintune.bookstore.utils.constants.formatter.ResponseFormatterConstants;

public class AddBookResponseFormatter implements ResponseFormatter<AddBookResponseDTO> {


    @Override
    public String format(AddBookResponseDTO response) {
        String messageCompleted = String.format(ResponseFormatterConstants.FORMATTING_STRING_MESSAGE,FacadeConstants.MESSAGE_COMPLETED);
        String bookRecordId = String.format(ResponseFormatterConstants.FORMATTING_STRING_FIELD,ResponseFormatterConstants.BOOK_RECORD_ID_FIELD, response.getBookRecordId());
        String bookId = String.format(ResponseFormatterConstants.FORMATTING_STRING_FIELD,ResponseFormatterConstants.BOOK_ID_FIELD, response.getBookId());
        String title = String.format(ResponseFormatterConstants.FORMATTING_STRING_FIELD,ResponseFormatterConstants.TITLE_FIELD, response.getTitle());
        String author = String.format(ResponseFormatterConstants.FORMATTING_STRING_FIELD,ResponseFormatterConstants.AUTHOR_FIELD, response.getAuthor());
        String isbn = String.format(ResponseFormatterConstants.FORMATTING_STRING_FIELD,ResponseFormatterConstants.ISBN_FIELD, response.getIsbn());
        String price = String.format(ResponseFormatterConstants.FORMATTING_STRING_FIELD,ResponseFormatterConstants.PRICE_FIELD, response.getPrice());
        String quantity = String.format(ResponseFormatterConstants.FORMATTING_STRING_FIELD,ResponseFormatterConstants.QUANTITY_FIELD, response.getQuantity());
        String availability = String.format(ResponseFormatterConstants.FORMATTING_STRING_FIELD,ResponseFormatterConstants.AVAILABILITY_FIELD, response.getAvailability());
        String publisher = String.format(ResponseFormatterConstants.FORMATTING_STRING_FIELD,ResponseFormatterConstants.PUBLISHER_FIELD, response.getPublisher());

        return messageCompleted
                .concat(bookRecordId)
                .concat(bookId)
                .concat(title)
                .concat(author)
                .concat(isbn)
                .concat(price)
                .concat(quantity)
                .concat(availability)
                .concat(publisher);

    }
}
