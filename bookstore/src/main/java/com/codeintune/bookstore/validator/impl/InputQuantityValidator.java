package com.codeintune.bookstore.validator.impl;

import com.codeintune.bookstore.error.ValidationErrorDTO;
import com.codeintune.bookstore.exception.ValidationException;
import com.codeintune.bookstore.utils.constants.validator.InputQuantityValidatorConstants;
import com.codeintune.bookstore.validator.Validator;
import com.codeintune.bookstore.validator.field.InputField;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

/**
 * Numerical quantity input field validator for a given user input
 */
@Component
public class InputQuantityValidator implements Validator<InputField> {

    private final static Pattern QUANTITY_PATTERN = Pattern.compile(InputQuantityValidatorConstants.REG_EXP_QUANTITY_VALIDATOR);

    @Override
    public void validate(InputField inputField) {
        if(inputField != null && !QUANTITY_PATTERN.matcher(inputField.getValue()).matches()){
            ValidationErrorDTO errorDTO = new ValidationErrorDTO();
            errorDTO.setField(inputField.getField());
            errorDTO.setMessage(InputQuantityValidatorConstants.ERROR_MESSAGE.formatted(inputField.getField(), inputField.getValue()));
            throw new ValidationException(errorDTO);
        }
    }
}
