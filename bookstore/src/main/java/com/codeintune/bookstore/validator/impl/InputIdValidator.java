package com.codeintune.bookstore.validator.impl;

import com.codeintune.bookstore.error.ValidationErrorDTO;
import com.codeintune.bookstore.exception.ValidationException;
import com.codeintune.bookstore.utils.constants.validator.InputIdValidatorConstants;
import com.codeintune.bookstore.validator.Validator;
import com.codeintune.bookstore.validator.field.InputField;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

/**
 * Validator for id-like inputs from users
 */
@Component
public class InputIdValidator implements Validator<InputField> {

    public static final Pattern ID_PATTERN = Pattern.compile(InputIdValidatorConstants.REG_EXP_ID_VALIDATOR);

    @Override
    public void validate(InputField inputField) {
        if(inputField != null && !ID_PATTERN.matcher(inputField.getValue()).matches()){
            ValidationErrorDTO errorDTO = new ValidationErrorDTO();
            errorDTO.setField(inputField.getField());
            errorDTO.setMessage(InputIdValidatorConstants.ERROR_MESSAGE.formatted(inputField.getValue()));
            throw new ValidationException(errorDTO);
        }
    }
}
