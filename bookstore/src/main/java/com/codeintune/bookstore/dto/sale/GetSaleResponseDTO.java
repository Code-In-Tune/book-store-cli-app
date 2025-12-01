package com.codeintune.bookstore.dto.sale;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class GetSaleResponseDTO {

    private String saleId;
    private String bookRecordId;
    private String dateSold;
    private String quantity;
    private String amount;

}
