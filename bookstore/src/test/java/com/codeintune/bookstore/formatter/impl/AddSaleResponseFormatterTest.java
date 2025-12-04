package com.codeintune.bookstore.formatter.impl;

import com.codeintune.bookstore.configuration.formatter.FormatterConfiguration;
import com.codeintune.bookstore.dto.sale.AddSaleResponseDTO;
import com.codeintune.bookstore.formatter.ResponseFormatter;
import com.codeintune.bookstore.testutils.seeder.sale.dto.AddSaleResponseDTOSeeder;
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
class AddSaleResponseFormatterTest {

    @Autowired
    @Qualifier(FormatterConfigurationConstants.ADD_SALE_RESPONSE_FORMATTER)
    private ResponseFormatter<AddSaleResponseDTO> addSaleResponseDTOResponseFormatter;

    @Test
    void addSaleResponseFormatterTest() {
        AddSaleResponseDTO response = AddSaleResponseDTOSeeder.generateResponse();

        String messageCompleted = String.format(ResponseFormatterConstants.FORMATTING_STRING_MESSAGE, FacadeConstants.MESSAGE_COMPLETED);
        String saleId = String.format(ResponseFormatterConstants.FORMATTING_STRING_FIELD,ResponseFormatterConstants.SALE_ID_FIELD, response.getSaleId());
        String bookRecordId = String.format(ResponseFormatterConstants.FORMATTING_STRING_FIELD,ResponseFormatterConstants.BOOK_RECORD_ID_FIELD, response.getBookRecordId());
        String quantity = String.format(ResponseFormatterConstants.FORMATTING_STRING_FIELD,ResponseFormatterConstants.QUANTITY_FIELD, response.getQuantity());
        String amount = String.format(ResponseFormatterConstants.FORMATTING_STRING_FIELD,ResponseFormatterConstants.AMOUNT_FIELD, response.getAmount());
        String dateSold = String.format(ResponseFormatterConstants.FORMATTING_STRING_FIELD,ResponseFormatterConstants.DATE_SOLD, response.getDateSold());

        StringBuilder sb = new StringBuilder();

        String formatted =  sb
                .append(messageCompleted)
                .append(saleId)
                .append(bookRecordId)
                .append(quantity)
                .append(amount)
                .append(dateSold)
                .toString();


        Assertions.assertEquals(formatted, addSaleResponseDTOResponseFormatter.format(response));
    }
}