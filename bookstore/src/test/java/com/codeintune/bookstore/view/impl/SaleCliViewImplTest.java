package com.codeintune.bookstore.view.impl;

import com.codeintune.bookstore.configuration.view.SaleViewConfiguration;
import com.codeintune.bookstore.dto.sale.AddSaleRequestDTO;
import com.codeintune.bookstore.dto.sale.AddSaleResponseDTO;
import com.codeintune.bookstore.dto.sale.GetSaleResponseDTO;
import com.codeintune.bookstore.dto.sale.GetSalesResponseDTO;
import com.codeintune.bookstore.error.ValidationErrorDTO;
import com.codeintune.bookstore.exception.ValidationException;
import com.codeintune.bookstore.exception.handler.ExceptionHandler;
import com.codeintune.bookstore.formatter.ResponseFormatter;
import com.codeintune.bookstore.service.sale.SaleInputService;
import com.codeintune.bookstore.service.sale.SaleService;
import com.codeintune.bookstore.testutils.seeder.sale.dto.AddSaleRequestDTOSeeder;
import com.codeintune.bookstore.testutils.seeder.sale.dto.AddSaleResponseDTOSeeder;
import com.codeintune.bookstore.testutils.seeder.sale.dto.GetSaleResponseDTOSeeder;
import com.codeintune.bookstore.utils.constants.exception.ValidationExceptionConstants;
import com.codeintune.bookstore.utils.constants.facade.FacadeConstants;
import com.codeintune.bookstore.view.SaleCliView;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

@ContextConfiguration(classes = {SaleViewConfiguration.class})
@ExtendWith(SpringExtension.class)
class SaleCliViewImplTest {

    private final static String MOCKED_FORMATTER_OUTPUT = "FORMATTED_STRING";
    private final static String ERROR_MESSAGE_MOCK = "ERROR_MESSAGE_MOCK";

    @Autowired
    private SaleCliView saleCliView;
    @MockitoBean
    private ExceptionHandler exceptionHandler;
    @MockitoBean
    private ResponseFormatter<AddSaleResponseDTO> addSaleResponseDTOResponseFormatter;
    @MockitoBean
    private ResponseFormatter<GetSalesResponseDTO> getSalesResponseDTOResponseFormatter;
    @MockitoBean
    private SaleInputService saleInputService;
    @MockitoBean
    private SaleService saleService;


    @Test
    void registerSale_WhenNoExceptionIsThrown() {
        AddSaleRequestDTO request = AddSaleRequestDTOSeeder.generateRequest();
        AddSaleResponseDTO response = AddSaleResponseDTOSeeder.generateResponse();

        Mockito.when(saleInputService.buildAddSaleRequestDTO())
                .thenReturn(request);
        Mockito.when(saleService.registerSale(Mockito.any(AddSaleRequestDTO.class)))
                .thenReturn(Optional.of(response));
        Mockito.when(addSaleResponseDTOResponseFormatter.format(Mockito.any(AddSaleResponseDTO.class)))
                .thenReturn(MOCKED_FORMATTER_OUTPUT);

        String message = saleCliView.registerSale();

        Assertions.assertEquals(MOCKED_FORMATTER_OUTPUT, message);

    }

    @Test
    void registerSale_WhenExceptionIsThrown() {
        AddSaleRequestDTO request = AddSaleRequestDTOSeeder.generateRequest();

        Mockito.when(saleInputService.buildAddSaleRequestDTO())
                .thenReturn(request);
        Mockito.when(saleService.registerSale(Mockito.any(AddSaleRequestDTO.class)))
                .thenReturn(Optional.empty());

        Mockito.doAnswer(invocationOnMock -> {
            ValidationException exception = invocationOnMock.getArgument(0, ValidationException.class);

            Assertions.assertEquals(ValidationExceptionConstants.BOOK_RECORD_NOT_FOUND.formatted(request.getBookRecordId()), exception.getValidationErrorDTO().getMessage());

            return null;
        }).when(exceptionHandler).handleException(Mockito.any(Exception.class));

        String message =  saleCliView.registerSale();

        Mockito.verify(exceptionHandler, Mockito.times(1)).handleException(Mockito.any(Exception.class));

        Assertions.assertEquals(FacadeConstants.MESSAGE_FAILURE, message);
    }

    @Test
    void showSales_WhenNoExceptionIsThrown() {
        GetSaleResponseDTO response = GetSaleResponseDTOSeeder.generateResponse();
        GetSalesResponseDTO sales = new GetSalesResponseDTO();
        sales.setGetSaleResponses(List.of(response));

        Mockito.when(saleService.getSales())
                .thenReturn(sales);
        Mockito.when(getSalesResponseDTOResponseFormatter.format(Mockito.any(GetSalesResponseDTO.class)))
                .thenReturn(MOCKED_FORMATTER_OUTPUT);

        String message = saleCliView.showSales();

        Assertions.assertEquals(MOCKED_FORMATTER_OUTPUT, message);
    }

    @Test
    void showSales_WhenExceptionIsThrown() {
        ValidationErrorDTO errorDTO = new ValidationErrorDTO();
        errorDTO.setMessage(ERROR_MESSAGE_MOCK);
        ValidationException exception = new ValidationException(errorDTO);

        Mockito.when(saleService.getSales()).thenThrow(exception);

        Mockito.doAnswer((invocationOnMock ->  {
            ValidationException exceptionThrown = invocationOnMock.getArgument(0, ValidationException.class);

            Assertions.assertEquals(ERROR_MESSAGE_MOCK, exceptionThrown.getValidationErrorDTO().getMessage());

            return null;
        })).when(exceptionHandler).handleException(Mockito.any(Exception.class));

        String message =  saleCliView.showSales();

        Mockito.verify(exceptionHandler, Mockito.times(1)).handleException(Mockito.any(Exception.class));

        Assertions.assertEquals(FacadeConstants.MESSAGE_FAILURE, message);
    }
}