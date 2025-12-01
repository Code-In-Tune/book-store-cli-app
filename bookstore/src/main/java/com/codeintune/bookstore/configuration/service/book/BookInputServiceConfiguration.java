package com.codeintune.bookstore.configuration.service.book;

import com.codeintune.bookstore.printer.BookStorePrinter;
import com.codeintune.bookstore.reader.BookStoreInputReader;
import com.codeintune.bookstore.service.book.BookInputService;
import com.codeintune.bookstore.service.book.impl.BookInputServiceImpl;
import com.codeintune.bookstore.utils.constants.service.BookInputServiceConfigurationConstants;
import com.codeintune.bookstore.utils.constants.validator.ValidatorConfigurationConstants;
import com.codeintune.bookstore.validator.Validator;
import com.codeintune.bookstore.validator.field.InputField;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BookInputServiceConfiguration {



    @Bean(BookInputServiceConfigurationConstants.BOOK_INPUT_SERVICE)
    public BookInputService bookInputService(
            BookStoreInputReader bookStoreInputReader,
            BookStorePrinter bookStorePrinter,
            @Qualifier(ValidatorConfigurationConstants.INPUT_NAME_VALIDATOR)
            Validator<InputField> inputNameValidator,
            @Qualifier(ValidatorConfigurationConstants.INPUT_ISBN_VALIDATOR)
            Validator<InputField> inputIsbnValidator,
            @Qualifier(ValidatorConfigurationConstants.INPUT_PRICE_VALIDATOR)
            Validator<InputField> inputPriceValidator,
            @Qualifier(ValidatorConfigurationConstants.INPUT_QUANTITY_VALIDATOR)
            Validator<InputField> inputQuantityValidator,
            @Qualifier(ValidatorConfigurationConstants.INPUT_ID_VALIDATOR)
            Validator<InputField> inputIdValidator
            )
    {
        return new BookInputServiceImpl(bookStoreInputReader, bookStorePrinter, inputNameValidator, inputIsbnValidator, inputQuantityValidator, inputPriceValidator, inputIdValidator);
    }
}
