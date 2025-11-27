package com.codeintune.bookstore.mapper.book.record;

import com.codeintune.bookstore.dto.book.AddBookRequestDTO;
import com.codeintune.bookstore.dto.book.AddBookResponseDTO;
import com.codeintune.bookstore.model.book.BookRecord;

public interface BookRecordMapper {

    BookRecord toEntity(AddBookRequestDTO dto);
    AddBookResponseDTO toDto(BookRecord entity);
}
