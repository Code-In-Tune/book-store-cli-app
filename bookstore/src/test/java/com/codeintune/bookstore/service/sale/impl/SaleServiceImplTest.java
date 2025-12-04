package com.codeintune.bookstore.service.sale.impl;

import com.codeintune.bookstore.configuration.service.sale.SaleServiceConfiguration;
import com.codeintune.bookstore.dto.sale.AddSaleRequestDTO;
import com.codeintune.bookstore.dto.sale.AddSaleResponseDTO;
import com.codeintune.bookstore.dto.sale.GetSaleResponseDTO;
import com.codeintune.bookstore.dto.sale.GetSalesResponseDTO;
import com.codeintune.bookstore.exception.ValidationException;
import com.codeintune.bookstore.mapper.sale.SaleMapper;
import com.codeintune.bookstore.model.book.BookRecord;
import com.codeintune.bookstore.model.sale.BookSale;
import com.codeintune.bookstore.repository.book.record.BookRecordRepository;
import com.codeintune.bookstore.repository.sale.SaleRepository;
import com.codeintune.bookstore.service.sale.SaleService;
import com.codeintune.bookstore.testutils.seeder.book.record.BookRecordSeeder;
import com.codeintune.bookstore.testutils.seeder.sale.data.BookSaleSeeder;
import com.codeintune.bookstore.testutils.seeder.sale.dto.AddSaleRequestDTOSeeder;
import com.codeintune.bookstore.testutils.seeder.sale.dto.AddSaleResponseDTOSeeder;
import com.codeintune.bookstore.testutils.seeder.sale.dto.GetSaleResponseDTOSeeder;
import com.codeintune.bookstore.utils.constants.exception.ValidationExceptionConstants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@ContextConfiguration(classes = {SaleServiceConfiguration.class})
@ExtendWith(SpringExtension.class)
class SaleServiceImplTest {

    @Autowired
    private SaleService saleService;
    @MockitoBean
    private SaleRepository saleRepository;
    @MockitoBean
    private BookRecordRepository bookRecordRepository;
    @MockitoBean
    private SaleMapper saleMapper;


    @Test
    void registerSale_WhenNoExceptionIsThrown() {
        BookRecord bookRecord = BookRecordSeeder.generateBookRecord();
        BookSale bookSale = BookSaleSeeder.generateEntity();
        AddSaleRequestDTO request = AddSaleRequestDTOSeeder.generateRequest();
        AddSaleResponseDTO response = AddSaleResponseDTOSeeder.generateResponse();

        Mockito.when(bookRecordRepository.findById(Mockito.anyLong()))
                .thenReturn(Optional.of(bookRecord));
        Mockito.when(saleMapper.toEntity(Mockito.any(AddSaleResponseDTO.class)))
                .thenReturn(bookSale);

        Mockito.doAnswer((invocationOnMock -> {
            BookRecord bookRecordSaved = invocationOnMock.getArgument(0, BookRecord.class);

            Assertions.assertEquals(0, bookRecordSaved.getQuantity());

            return bookRecordSaved;
        })).when(bookRecordRepository).save(Mockito.any(BookRecord.class));

        Mockito.doAnswer((invocationOnMock -> {
            BookSale bookSaleSaved = invocationOnMock.getArgument(0, BookSale.class);

            bookSaleSaved.setSaleId(1L);
            Assertions.assertEquals(1, bookSaleSaved.getQuantity());

            return bookSaleSaved;
        })).when(saleRepository).save(Mockito.any(BookSale.class));


        Optional<AddSaleResponseDTO> result = saleService.registerSale(request);

        Assertions.assertTrue(result.isPresent());

        AddSaleResponseDTO content = result.get();

        Assertions.assertAll("Checking fields",
                () -> Assertions.assertEquals(response.getSaleId(), content.getSaleId(), "Sale id do not match"),
                () -> Assertions.assertEquals(response.getQuantity(), content.getQuantity(), "Quantity do not match"),
                () -> Assertions.assertEquals(response.getBookRecordId(), content.getBookRecordId(), "Book Record Id do not match"),
                () -> Assertions.assertEquals(response.getAmount(), content.getAmount(), "Amount do not match")
        );
    }

    @Test
    void registerSale_WhenExceptionIsThrown() {
        BookRecord bookRecord = BookRecordSeeder.generateBookRecord();
        AddSaleRequestDTO request = AddSaleRequestDTOSeeder.generateRequest();

        // Setting book record quantity to zero in order to trigger exception

        bookRecord.setQuantity(0);

        Mockito.when(bookRecordRepository.findById(Mockito.anyLong()))
                .thenReturn(Optional.of(bookRecord));

        ValidationException ex = Assertions.assertThrows(ValidationException.class, () -> saleService.registerSale(request));

        Assertions.assertEquals(ValidationExceptionConstants.CANNOT_SELL_MORE_THAN_ACTUAL_QUANTITY.formatted(request.getQuantity(), bookRecord.getQuantity()), ex.getValidationErrorDTO().getMessage());
    }

    @Test
    void getSales_WhenNoExceptionIsThrown() {
        GetSaleResponseDTO getSaleResponseDTO = GetSaleResponseDTOSeeder.generateResponse();
        BookSale bookSale = BookSaleSeeder.generateEntity();
        GetSalesResponseDTO sales = new GetSalesResponseDTO();
        sales.setGetSaleResponses(List.of(getSaleResponseDTO));

        Mockito.when(saleRepository.findAll()).thenReturn(List.of(bookSale));

        GetSalesResponseDTO result = saleService.getSales();

        Assertions.assertFalse(result.getGetSaleResponses().isEmpty());
        Assertions.assertEquals(1, result.getGetSaleResponses().size());

        GetSaleResponseDTO content = result.getGetSaleResponses().get(0);

        Assertions.assertAll("Checking fields",
                () -> Assertions.assertEquals(getSaleResponseDTO.getSaleId(), content.getSaleId(), "Sale id do not match"),
                () -> Assertions.assertEquals(getSaleResponseDTO.getBookRecordId(), content.getBookRecordId(), "Book Record Id do not match"),
                () -> Assertions.assertEquals(getSaleResponseDTO.getAmount(), content.getAmount(), "Amount do not match"),
                () -> Assertions.assertEquals(getSaleResponseDTO.getQuantity(), content.getQuantity(), "Quantity do not match"));
    }

    @Test
    void  getSales_WhenExceptionIsThrown() {
        Mockito.when(saleRepository.findAll()).thenReturn(List.of());

        ValidationException ex = Assertions.assertThrows(ValidationException.class, () -> saleService.getSales());

        Assertions.assertEquals(ValidationExceptionConstants.NO_SALES_FOUND, ex.getValidationErrorDTO().getMessage());
    }
}