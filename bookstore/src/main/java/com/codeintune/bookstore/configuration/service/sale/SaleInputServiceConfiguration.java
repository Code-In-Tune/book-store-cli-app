package com.codeintune.bookstore.configuration.service.sale;

import com.codeintune.bookstore.printer.BookStorePrinter;
import com.codeintune.bookstore.reader.BookStoreInputReader;
import com.codeintune.bookstore.service.sale.SaleInputService;
import com.codeintune.bookstore.service.sale.impl.SaleInputServiceImpl;
import com.codeintune.bookstore.utils.constants.service.SaleInputServiceConfigurationConstants;
import com.codeintune.bookstore.utils.constants.validator.ValidatorConfigurationConstants;
import com.codeintune.bookstore.validator.Validator;
import com.codeintune.bookstore.validator.field.InputField;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SaleInputServiceConfiguration {


    @Bean(SaleInputServiceConfigurationConstants.SALE_INPUT_SERVICE)
    public SaleInputService saleInputService(
            BookStorePrinter bookStorePrinter,
            @Qualifier(ValidatorConfigurationConstants.INPUT_QUANTITY_VALIDATOR)
            Validator<InputField> inputQuantityValidator,
            @Qualifier(ValidatorConfigurationConstants.INPUT_ID_VALIDATOR)
            Validator<InputField> inputIdValidator,
            BookStoreInputReader bookStoreInputReader
    ){
        return new SaleInputServiceImpl(bookStoreInputReader,inputQuantityValidator, inputIdValidator, bookStorePrinter);
    }
}
