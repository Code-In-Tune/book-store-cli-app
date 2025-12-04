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
class InputIsbnValidatorTest {

    @Autowired
    @Qualifier(ValidatorConfigurationConstants.INPUT_ISBN_VALIDATOR)
    private Validator<InputField> inputIsbnValidator;

    @Test
    void validateTest_WhenNoExceptionIsThrown() {
        InputField inputField = new InputField();
        inputField.setValue("0345242238");

        Assertions.assertDoesNotThrow(() -> inputIsbnValidator.validate(inputField));
    }

    @Test
    void validateTest_WhenIsbnIsInvalid(){
        InputField inputField = new InputField();
        inputField.setValue("INVALID_ISBN");

        Assertions.assertThrows(ValidationException.class, () ->  inputIsbnValidator.validate(inputField));
    }
}