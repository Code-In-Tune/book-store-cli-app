package com.codeintune.bookstore.configuration.service.book;

import com.codeintune.bookstore.service.sale.SaleInputService;
import com.codeintune.bookstore.service.sale.impl.SaleInputServiceImpl;
import com.codeintune.bookstore.utils.constants.service.SaleInputServiceConfigurationConstants;
import com.codeintune.bookstore.utils.constants.validator.ValidatorConfigurationConstants;
import com.codeintune.bookstore.validator.Validator;
import com.codeintune.bookstore.validator.field.InputField;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Scanner;

@Configuration
public class SaleInputServiceConfiguration {


    @Bean(SaleInputServiceConfigurationConstants.SALE_INPUT_SERVICE)
    public SaleInputService saleInputService(
            @Qualifier(ValidatorConfigurationConstants.INPUT_QUANTITY_VALIDATOR)
            Validator<InputField> inputQuantityValidator,
            @Qualifier(ValidatorConfigurationConstants.INPUT_ID_VALIDATOR)
            Validator<InputField> inputIdValidator
    ){
        return new SaleInputServiceImpl(new Scanner(System.in), inputQuantityValidator, inputIdValidator);
    }
}
