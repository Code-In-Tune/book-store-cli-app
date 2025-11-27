package com.codeintune.bookstore.validator.impl;

import com.codeintune.bookstore.error.ValidationErrorDTO;
import com.codeintune.bookstore.exception.ValidationException;
import com.codeintune.bookstore.utils.constants.validator.InputNameValidatorConstants;
import com.codeintune.bookstore.validator.Validator;
import com.codeintune.bookstore.validator.field.InputField;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class InputNameValidator implements Validator<InputField> {

    private static final Pattern NAME_PATTERN = Pattern.compile(InputNameValidatorConstants.REG_EXP_NAME_VALIDATOR);

    @Override
    public void validate(InputField input){
        if(input != null && !NAME_PATTERN.matcher(input.getValue()).matches()){
            ValidationErrorDTO errorDTO = new ValidationErrorDTO();
            errorDTO.setField(input.getField());
            errorDTO.setMessage(InputNameValidatorConstants.ERROR_MESSAGE.formatted(input.getField(),  input.getValue()));
            throw new ValidationException(errorDTO);
        }
    }
}
