package com.codeintune.bookstore.service.sale.impl;

import com.codeintune.bookstore.dto.sale.AddSaleRequestDTO;
import com.codeintune.bookstore.service.sale.SaleInputService;
import com.codeintune.bookstore.utils.constants.service.BookInputServiceConstants;
import com.codeintune.bookstore.utils.constants.validator.InputFieldConstants;
import com.codeintune.bookstore.validator.Validator;
import com.codeintune.bookstore.validator.field.InputField;
import lombok.RequiredArgsConstructor;

import java.util.Scanner;

@RequiredArgsConstructor
public class SaleInputServiceImpl implements SaleInputService {

    private final Scanner scanner;
    private final Validator<InputField> inputQuantityValidator;
    private final Validator<InputField> inputIdValidator;

    @Override
    public AddSaleRequestDTO buildAddSaleRequestDTO() {
        System.out.println(BookInputServiceConstants.INSERT_BOOK_RECORD_ID_MESSAGE);

        String id = scanner.nextLine().trim();

        InputField recordIdField = new InputField();
        recordIdField.setField(InputFieldConstants.BOOK_RECORD_ID);
        recordIdField.setValue(id);

        inputIdValidator.validate(recordIdField);

        System.out.println(BookInputServiceConstants.INSERT_BOOK_QUANTITY);

        String quantity = scanner.nextLine().trim();

        InputField quantityField = new InputField();
        quantityField.setField(InputFieldConstants.QUANTITY);
        quantityField.setValue(quantity);

        inputQuantityValidator.validate(quantityField);

        AddSaleRequestDTO requestDTO = new AddSaleRequestDTO();
        requestDTO.setQuantity(quantity);
        requestDTO.setBookRecordId(id);
        return requestDTO;
    }
}
