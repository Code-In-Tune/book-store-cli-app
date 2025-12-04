package com.codeintune.bookstore.validator.impl;

import com.codeintune.bookstore.configuration.validator.ValidatorConfiguration;
import com.codeintune.bookstore.exception.ValidationException;
import com.codeintune.bookstore.utils.constants.exception.ValidationExceptionConstants;
import com.codeintune.bookstore.utils.constants.validator.ValidatorConfigurationConstants;
import com.codeintune.bookstore.validator.Validator;
import com.codeintune.bookstore.validator.field.InputField;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ContextConfiguration(classes = {ValidatorConfiguration.class})
@ExtendWith(SpringExtension.class)
class InputQuantityValidatorTest {

    @Autowired
    @Qualifier(ValidatorConfigurationConstants.INPUT_QUANTITY_VALIDATOR)
    private Validator<InputField> inputQuantityValidator;

    @Test
    void validateTest_WhenNoExceptionIsThrown() {
        InputField inputField = new InputField();
        inputField.setValue("1");

        Assertions.assertDoesNotThrow(() -> inputQuantityValidator.validate(inputField));
    }

    @Test
    void validateTest_WhenExceptionIsThrown() {
        InputField inputField = new InputField();
        inputField.setValue("INVALID_INPUT");

        Assertions.assertThrows(ValidationException.class, () -> inputQuantityValidator.validate(inputField));
    }
}