package com.codeintune.bookstore.formatter.impl;

import com.codeintune.bookstore.dto.book.GetBooksResponseDTO;
import com.codeintune.bookstore.formatter.ResponseFormatter;
import com.codeintune.bookstore.utils.constants.facade.FacadeConstants;
import com.codeintune.bookstore.utils.constants.formatter.ResponseFormatterConstants;

import java.util.stream.Collectors;

public class GetBooksResponseFormatter implements ResponseFormatter<GetBooksResponseDTO> {


    @Override
    public String format(GetBooksResponseDTO response) {
        String messageCompleted = String.format(ResponseFormatterConstants.FORMATTING_STRING_MESSAGE, FacadeConstants.MESSAGE_COMPLETED);
        return messageCompleted + response.getBooks().stream().map(b -> {

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
    }
}
