package com.codeintune.bookstore.validator.impl;

import com.codeintune.bookstore.error.ValidationErrorDTO;
import com.codeintune.bookstore.exception.ValidationException;
import com.codeintune.bookstore.utils.constants.validator.InputIsbnValidatorConstants;
import com.codeintune.bookstore.validator.Validator;
import com.codeintune.bookstore.validator.field.InputField;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class InputIsbnValidator implements Validator<InputField> {

    public static final Pattern ISBN_PATTERN = Pattern.compile(InputIsbnValidatorConstants.REG_EXP_ISBN_VALIDATOR);

    @Override
    public void validate(InputField inputField) {
        if(inputField != null && !ISBN_PATTERN.matcher(inputField.getValue()).matches()) {
            ValidationErrorDTO errorDTO = new ValidationErrorDTO();
            errorDTO.setField(inputField.getField());
            errorDTO.setMessage(InputIsbnValidatorConstants.ERROR_MESSAGE.formatted(inputField.getValue()));
            throw new ValidationException(errorDTO);
        }
    }
}
