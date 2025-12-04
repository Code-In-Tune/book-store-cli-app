package com.codeintune.bookstore.validator.impl;

import com.codeintune.bookstore.configuration.validator.ValidatorConfiguration;
import com.codeintune.bookstore.exception.ValidationException;
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
class InputNameValidatorTest {

    @Autowired
    @Qualifier(ValidatorConfigurationConstants.INPUT_NAME_VALIDATOR)
    private Validator<InputField> inputNameValidator;

    @Test
    void validateTest_WhenNoExceptionIsThrown() {
        InputField inputField = new InputField();
        inputField.setValue("Lewis Carroll");

        Assertions.assertDoesNotThrow(() -> inputNameValidator.validate(inputField));
    }

    @Test
    void validateTest_WhenExceptionIsThrown(){
        InputField inputField = new InputField();
        inputField.setValue("INVALID_NAME");

        Assertions.assertThrows(ValidationException.class, () ->  inputNameValidator.validate(inputField));
    }
}