package com.codeintune.bookstore.service.sale.impl;

import com.codeintune.bookstore.configuration.service.sale.SaleInputServiceConfiguration;
import com.codeintune.bookstore.dto.sale.AddSaleRequestDTO;
import com.codeintune.bookstore.printer.BookStorePrinter;
import com.codeintune.bookstore.reader.BookStoreInputReader;
import com.codeintune.bookstore.service.sale.SaleInputService;
import com.codeintune.bookstore.utils.constants.validator.ValidatorConfigurationConstants;
import com.codeintune.bookstore.validator.Validator;
import com.codeintune.bookstore.validator.field.InputField;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {SaleInputServiceConfiguration.class})
@ExtendWith(SpringExtension.class)
class SaleInputServiceImplTest {

    private final static String QUANTITY = "1";
    private final static String ID = "1";

    @Autowired
    private SaleInputService saleInputService;
    @MockitoBean
    private BookStoreInputReader bookStoreInputReader;
    @MockitoBean
    private BookStorePrinter bookStorePrinter;
    @MockitoBean
    @Qualifier(ValidatorConfigurationConstants.INPUT_QUANTITY_VALIDATOR)
    private Validator<InputField> inputQuantityValidator;
    @MockitoBean
    @Qualifier(ValidatorConfigurationConstants.INPUT_ID_VALIDATOR)
    private Validator<InputField> inputIdValidator;

    @Test
    void buildAddSaleRequestDTOTest() {
        Mockito.when(bookStoreInputReader.readNextLineWithQuitOption())
                .thenReturn(ID)
                .thenReturn(QUANTITY);


        AddSaleRequestDTO addSaleRequestDTO = saleInputService.buildAddSaleRequestDTO();

        Mockito.verify(inputIdValidator, Mockito.times(1)).validate(Mockito.any(InputField.class));
        Mockito.verify(inputQuantityValidator, Mockito.times(1)).validate(Mockito.any(InputField.class));

        Assertions.assertAll(
                () -> Assertions.assertEquals(ID, addSaleRequestDTO.getBookRecordId()),
                () -> Assertions.assertEquals(QUANTITY, addSaleRequestDTO.getQuantity())
        );
    }
}