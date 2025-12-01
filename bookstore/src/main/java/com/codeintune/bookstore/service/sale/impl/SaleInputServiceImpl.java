package com.codeintune.bookstore.service.sale.impl;

import com.codeintune.bookstore.dto.sale.AddSaleRequestDTO;
import com.codeintune.bookstore.printer.BookStorePrinter;
import com.codeintune.bookstore.reader.BookStoreInputReader;
import com.codeintune.bookstore.service.sale.SaleInputService;
import com.codeintune.bookstore.utils.constants.service.BookInputServiceConstants;
import com.codeintune.bookstore.utils.constants.validator.InputFieldConstants;
import com.codeintune.bookstore.validator.Validator;
import com.codeintune.bookstore.validator.field.InputField;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SaleInputServiceImpl implements SaleInputService {

    private final BookStoreInputReader bookStoreInputReader;
    private final Validator<InputField> inputQuantityValidator;
    private final Validator<InputField> inputIdValidator;
    private final BookStorePrinter bookStorePrinter;

    @Override
    public AddSaleRequestDTO buildAddSaleRequestDTO() {
        bookStorePrinter.print(BookInputServiceConstants.INSERT_BOOK_RECORD_ID_MESSAGE);
        bookStorePrinter.printQuitOption();

        String id = bookStoreInputReader.readNextLineWithQuitOption();


        InputField recordIdField = new InputField();
        recordIdField.setField(InputFieldConstants.BOOK_RECORD_ID);
        recordIdField.setValue(id);

        inputIdValidator.validate(recordIdField);

        bookStorePrinter.print(BookInputServiceConstants.INSERT_BOOK_QUANTITY);
        bookStorePrinter.printQuitOption();

        String quantity = bookStoreInputReader.readNextLineWithQuitOption();


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
