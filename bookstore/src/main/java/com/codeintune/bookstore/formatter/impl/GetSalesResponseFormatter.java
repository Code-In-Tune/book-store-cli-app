package com.codeintune.bookstore.formatter.impl;

import com.codeintune.bookstore.dto.sale.GetSalesResponseDTO;
import com.codeintune.bookstore.formatter.ResponseFormatter;
import com.codeintune.bookstore.utils.constants.facade.FacadeConstants;
import com.codeintune.bookstore.utils.constants.formatter.ResponseFormatterConstants;

import java.util.stream.Collectors;

public class GetSalesResponseFormatter implements ResponseFormatter<GetSalesResponseDTO> {


    @Override
    public String format(GetSalesResponseDTO response) {
        String messageCompleted = String.format(ResponseFormatterConstants.FORMATTING_STRING_MESSAGE, FacadeConstants.MESSAGE_COMPLETED);
        return messageCompleted + response.getGetSaleResponses().stream().map(resp -> {
            String saleId = String.format(ResponseFormatterConstants.FORMATTING_STRING_FIELD,ResponseFormatterConstants.SALE_ID_FIELD, resp.getSaleId());
            String bookRecordId = String.format(ResponseFormatterConstants.FORMATTING_STRING_FIELD,ResponseFormatterConstants.BOOK_RECORD_ID_FIELD, resp.getBookRecordId());
            String quantity = String.format(ResponseFormatterConstants.FORMATTING_STRING_FIELD,ResponseFormatterConstants.QUANTITY_FIELD, resp.getQuantity());
            String amount = String.format(ResponseFormatterConstants.FORMATTING_STRING_FIELD,ResponseFormatterConstants.AMOUNT_FIELD, resp.getAmount());
            String dateSold = String.format(ResponseFormatterConstants.FORMATTING_STRING_FIELD,ResponseFormatterConstants.DATE_SOLD, resp.getDateSold());

            StringBuilder sb = new StringBuilder();

            return sb
                    .append(saleId)
                    .append(bookRecordId)
                    .append(quantity)
                    .append(amount)
                    .append(dateSold)
                    .toString();
        }).collect(Collectors.joining(FacadeConstants.SEPARATOR.concat(FacadeConstants.NEW_LINE_CHARACTER)));
    }
}
