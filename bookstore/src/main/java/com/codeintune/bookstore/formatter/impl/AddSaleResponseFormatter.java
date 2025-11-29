package com.codeintune.bookstore.formatter.impl;

import com.codeintune.bookstore.dto.sale.AddSaleResponseDTO;
import com.codeintune.bookstore.formatter.ResponseFormatter;
import com.codeintune.bookstore.utils.constants.facade.FacadeConstants;
import com.codeintune.bookstore.utils.constants.formatter.ResponseFormatterConstants;

public class AddSaleResponseFormatter implements ResponseFormatter<AddSaleResponseDTO> {


    @Override
    public String format(AddSaleResponseDTO response) {
        String messageCompleted = String.format(ResponseFormatterConstants.FORMATTING_STRING_MESSAGE, FacadeConstants.MESSAGE_COMPLETED);
        String saleId = String.format(ResponseFormatterConstants.FORMATTING_STRING_FIELD,ResponseFormatterConstants.SALE_ID_FIELD, response.getSaleId());
        String bookRecordId = String.format(ResponseFormatterConstants.FORMATTING_STRING_FIELD,ResponseFormatterConstants.BOOK_RECORD_ID_FIELD, response.getBookRecordId());
        String quantity = String.format(ResponseFormatterConstants.FORMATTING_STRING_FIELD,ResponseFormatterConstants.QUANTITY_FIELD, response.getQuantity());
        String amount = String.format(ResponseFormatterConstants.FORMATTING_STRING_FIELD,ResponseFormatterConstants.AMOUNT_FIELD, response.getAmount());
        String dateSold = String.format(ResponseFormatterConstants.FORMATTING_STRING_FIELD,ResponseFormatterConstants.DATE_SOLD, response.getDateSold());

        StringBuilder sb = new StringBuilder();

        return sb
                .append(messageCompleted)
                .append(saleId)
                .append(bookRecordId)
                .append(quantity)
                .append(amount)
                .append(dateSold)
                .toString();
    }
}
