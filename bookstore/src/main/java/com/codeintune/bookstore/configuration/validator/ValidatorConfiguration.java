package com.codeintune.bookstore.configuration.validator;

import com.codeintune.bookstore.validator.Validator;
import com.codeintune.bookstore.validator.field.InputField;
import com.codeintune.bookstore.validator.impl.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.codeintune.bookstore.utils.constants.validator.ValidatorConfigurationConstants.*;

@Configuration
public class ValidatorConfiguration {


    @Bean(INPUT_NAME_VALIDATOR)
    public Validator<InputField> inputNameValidator() {
        return new InputNameValidator();
    }

    @Bean(INPUT_ISBN_VALIDATOR)
    public Validator<InputField> inputIsbnValidator() {
        return new InputIsbnValidator();
    }

    @Bean(INPUT_QUANTITY_VALIDATOR)
    public Validator<InputField> inputQuantityValidator() {
        return new InputQuantityValidator();
    }

    @Bean(INPUT_PRICE_VALIDATOR)
    public Validator<InputField> inputPriceValidator() {
        return new InputPriceValidator();
    }

    @Bean(INPUT_ID_VALIDATOR)
    public Validator<InputField> inputIdValidator() {
        return new InputIdValidator();
    }
}
