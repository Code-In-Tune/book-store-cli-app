package com.codeintune.bookstore.mapper.sale.impl;

import com.codeintune.bookstore.dto.sale.AddSaleResponseDTO;
import com.codeintune.bookstore.mapper.sale.SaleMapper;
import com.codeintune.bookstore.model.sale.BookSale;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;

@Component
public class SaleMapperImpl implements SaleMapper {


    @Override
    public BookSale toEntity(AddSaleResponseDTO dto) {
        BookSale entity = new BookSale();
        entity.setBookRecordId(Long.parseLong(dto.getBookRecordId()));
        entity.setDateSold(Instant.parse(dto.getDateSold()));
        entity.setQuantity(Integer.parseInt(dto.getQuantity()));
        entity.setAmount(new BigDecimal(dto.getAmount()));
        return entity;
    }
}
