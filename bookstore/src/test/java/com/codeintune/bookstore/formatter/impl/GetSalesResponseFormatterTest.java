package com.codeintune.bookstore.formatter.impl;

import com.codeintune.bookstore.configuration.formatter.FormatterConfiguration;
import com.codeintune.bookstore.dto.sale.GetSaleResponseDTO;
import com.codeintune.bookstore.dto.sale.GetSalesResponseDTO;
import com.codeintune.bookstore.formatter.ResponseFormatter;
import com.codeintune.bookstore.testutils.seeder.sale.dto.GetSaleResponseDTOSeeder;
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
class GetSalesResponseFormatterTest {

    @Autowired
    @Qualifier(FormatterConfigurationConstants.GET_SALES_RESPONSE_FORMATTER)
    private ResponseFormatter<GetSalesResponseDTO> getSalesResponseFormatter;

    @Test
    void getSalesResponseFormatterTest() {
        GetSaleResponseDTO content = GetSaleResponseDTOSeeder.generateResponse();
        GetSalesResponseDTO response = new GetSalesResponseDTO();
        response.setGetSaleResponses(List.of(content));


        String messageCompleted = String.format(ResponseFormatterConstants.FORMATTING_STRING_MESSAGE, FacadeConstants.MESSAGE_COMPLETED);
        String formatted = messageCompleted + response.getGetSaleResponses().stream().map(resp -> {
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

        Assertions.assertEquals(formatted, getSalesResponseFormatter.format(response));
    }
}