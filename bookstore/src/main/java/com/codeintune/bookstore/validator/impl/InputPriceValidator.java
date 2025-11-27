package com.codeintune.bookstore.validator.impl;

import com.codeintune.bookstore.error.ValidationErrorDTO;
import com.codeintune.bookstore.exception.ValidationException;
import com.codeintune.bookstore.utils.constants.validator.InputPriceValidatorConstants;
import com.codeintune.bookstore.validator.Validator;
import com.codeintune.bookstore.validator.field.InputField;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class InputPriceValidator implements Validator<InputField> {

    private static final Pattern PRICE_PATTERN = Pattern.compile(InputPriceValidatorConstants.REG_EXP_PRICE_VALIDATOR);

    @Override
    public void validate(InputField inputField) {
        if(inputField != null && !PRICE_PATTERN.matcher(inputField.getValue()).matches()) {
            ValidationErrorDTO  errorDTO = new ValidationErrorDTO();
            errorDTO.setField(inputField.getField());
            errorDTO.setMessage(InputPriceValidatorConstants.ERROR_MESSAGE.formatted(inputField.getField(), inputField.getValue()));
            throw new ValidationException(errorDTO);
        }

    }
}
