package com.codeintune.bookstore.mapper.book.record.impl;

import com.codeintune.bookstore.dto.book.AddBookRequestDTO;
import com.codeintune.bookstore.dto.book.AddBookResponseDTO;
import com.codeintune.bookstore.mapper.book.record.BookRecordMapper;
import com.codeintune.bookstore.model.book.BookRecord;
import com.codeintune.bookstore.model.book.enums.Availability;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class BookRecordMapperImpl implements BookRecordMapper {

    @Override
    public BookRecord toEntity(AddBookRequestDTO dto) {
        BookRecord bookRecord = new BookRecord();
        bookRecord.setPrice(new BigDecimal(dto.getPrice()));
        bookRecord.setAvailability(Availability.IN_STOCK);
        bookRecord.setQuantity(Integer.valueOf(dto.getQuantity()));
        return bookRecord;
    }

    @Override
    public AddBookResponseDTO toDto(BookRecord entity) {
        AddBookResponseDTO dto = new AddBookResponseDTO();
        dto.setAvailability(entity.getAvailability().getDescription());
        dto.setBookRecordId(entity.getBookRecordId().toString());
        dto.setQuantity(entity.getQuantity().toString());
        dto.setPrice(entity.getPrice().toString());
        return dto;
    }
}
