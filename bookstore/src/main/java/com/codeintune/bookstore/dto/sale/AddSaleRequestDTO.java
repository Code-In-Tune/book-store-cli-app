package com.codeintune.bookstore.dto.sale;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class AddSaleRequestDTO {

    private String bookRecordId;
    private String quantity;
}
