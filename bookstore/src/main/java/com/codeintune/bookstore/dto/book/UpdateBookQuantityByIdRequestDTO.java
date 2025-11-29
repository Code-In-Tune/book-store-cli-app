package com.codeintune.bookstore.dto.book;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class UpdateBookQuantityByIdRequestDTO {

    private String bookRecordId;
    private String quantity;
}
