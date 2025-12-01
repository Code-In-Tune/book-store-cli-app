package com.codeintune.bookstore.dto.sale;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class GetSalesResponseDTO {

    private List<GetSaleResponseDTO> getSaleResponses;

}
