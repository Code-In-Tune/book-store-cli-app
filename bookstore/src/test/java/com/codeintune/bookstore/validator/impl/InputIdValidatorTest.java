package com.codeintune.bookstore.validator.impl;

import com.codeintune.bookstore.configuration.validator.ValidatorConfiguration;
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

@ContextConfiguration(classes = {ValidatorConfiguration.class})
@ExtendWith(SpringExtension.class)
class InputIdValidatorTest {

    @Autowired
    @Qualifier(ValidatorConfigurationConstants.INPUT_ID_VALIDATOR)
    private Validator<InputField> inputIdValidator;

    @Test
    void validateTest_WhenNoExceptionIsThrown() {
        InputField inputField = new InputField();
        inputField.setValue("1");

        Assertions.assertDoesNotThrow(() -> inputIdValidator.validate(inputField));
    }


    @Test
    void validatorTest_WhenExceptionIsThrown() {
        InputField inputField = new InputField();
        inputField.setValue("INVALID_INPUT");

        Assertions.assertThrows(RuntimeException.class, () -> inputIdValidator.validate(inputField));
    }
}