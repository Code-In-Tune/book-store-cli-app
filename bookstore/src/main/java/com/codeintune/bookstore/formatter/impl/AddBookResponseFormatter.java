package com.codeintune.bookstore.formatter.impl;

import com.codeintune.bookstore.dto.book.AddBookResponseDTO;
import com.codeintune.bookstore.formatter.ResponseFormatter;
import com.codeintune.bookstore.utils.constants.facade.FacadeConstants;

public class AddBookResponseFormatter implements ResponseFormatter<AddBookResponseDTO> {


    @Override
    public String format(AddBookResponseDTO response) {

        return FacadeConstants.MESSAGE_COMPLETED.concat(FacadeConstants.NEW_LINE_CHARACTER).concat( "book record id: ".concat(response.getBookId()))
                .concat(FacadeConstants.NEW_LINE_CHARACTER)
                .concat("book id: ".concat(response.getBookRecordId()))
                .concat(FacadeConstants.NEW_LINE_CHARACTER)
                .concat("title: ".concat(response.getTitle()))
                .concat(FacadeConstants.NEW_LINE_CHARACTER)
                .concat("author: ".concat(response.getAuthor()))
                .concat(FacadeConstants.NEW_LINE_CHARACTER)
                .concat("isbn: ".concat(response.getIsbn()))
                .concat(FacadeConstants.NEW_LINE_CHARACTER)
                .concat("price: ".concat(response.getPrice()))
                .concat(FacadeConstants.NEW_LINE_CHARACTER)
                .concat("quantity: ".concat(response.getQuantity()))
                .concat(FacadeConstants.NEW_LINE_CHARACTER)
                .concat("availability: ".concat(response.getAvailability()))
                .concat(FacadeConstants.NEW_LINE_CHARACTER)
                .concat("publisher: ".concat(response.getPublisher()))
                .concat(FacadeConstants.NEW_LINE_CHARACTER);
    }
}
