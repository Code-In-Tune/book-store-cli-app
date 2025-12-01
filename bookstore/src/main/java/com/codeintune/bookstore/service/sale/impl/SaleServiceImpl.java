package com.codeintune.bookstore.service.sale.impl;

import com.codeintune.bookstore.dto.sale.AddSaleRequestDTO;
import com.codeintune.bookstore.dto.sale.AddSaleResponseDTO;
import com.codeintune.bookstore.dto.sale.GetSaleResponseDTO;
import com.codeintune.bookstore.dto.sale.GetSalesResponseDTO;
import com.codeintune.bookstore.error.ValidationErrorDTO;
import com.codeintune.bookstore.exception.ValidationException;
import com.codeintune.bookstore.mapper.sale.SaleMapper;
import com.codeintune.bookstore.model.book.enums.Availability;
import com.codeintune.bookstore.model.sale.BookSale;
import com.codeintune.bookstore.repository.book.record.BookRecordRepository;
import com.codeintune.bookstore.repository.sale.SaleRepository;
import com.codeintune.bookstore.service.sale.SaleService;
import com.codeintune.bookstore.utils.constants.exception.ValidationExceptionConstants;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class SaleServiceImpl implements SaleService {

    private final SaleRepository saleRepository;
    private final SaleMapper saleMapper;
    private final BookRecordRepository bookRecordRepository;

    @Override
    public Optional<AddSaleResponseDTO> registerSale(AddSaleRequestDTO dto) {
        return bookRecordRepository.findById(Long.parseLong(dto.getBookRecordId()))
                .map(br -> {
                    if(br.getQuantity() < Integer.parseInt(dto.getQuantity())){
                        ValidationErrorDTO errorDTO = new ValidationErrorDTO();
                        errorDTO.setMessage(ValidationExceptionConstants.CANNOT_SELL_MORE_THAN_ACTUAL_QUANTITY.formatted(dto.getQuantity(), br.getQuantity()));
                        throw new ValidationException(errorDTO);
                    }
                    br.setQuantity(br.getQuantity() - Integer.parseInt(dto.getQuantity()));
                    if(br.getQuantity() == 0){
                        br.setAvailability(Availability.NOT_IN_STOCK);
                    }
                    bookRecordRepository.save(br);
                    BigDecimal amount = br.getPrice().multiply(new BigDecimal(dto.getQuantity()));
                    AddSaleResponseDTO responseDTO = new AddSaleResponseDTO();
                    responseDTO.setBookRecordId(br.getBookRecordId().toString());
                    responseDTO.setDateSold(DateTimeFormatter.ISO_INSTANT.format(Instant.now()));
                    responseDTO.setQuantity(dto.getQuantity());
                    responseDTO.setAmount(amount.toString());
                    BookSale sale = saleRepository.save(saleMapper.toEntity(responseDTO));
                    responseDTO.setSaleId(sale.getSaleId().toString());

                    return responseDTO;
                });
    }

    @Override
    public GetSalesResponseDTO getSales() {
        GetSalesResponseDTO responseDTO = new GetSalesResponseDTO();
        List<GetSaleResponseDTO> sales = saleRepository.findAll().stream().map(s -> {
            GetSaleResponseDTO dto = new GetSaleResponseDTO();
            dto.setSaleId(s.getSaleId().toString());
            dto.setBookRecordId(s.getBookRecordId().toString());
            dto.setDateSold(DateTimeFormatter.ISO_INSTANT.format(Instant.now()));
            dto.setQuantity(s.getQuantity().toString());
            dto.setAmount(s.getAmount().toString());
            return dto;
        }).toList();
        responseDTO.setGetSaleResponses(sales);
        return responseDTO;
    }
}
