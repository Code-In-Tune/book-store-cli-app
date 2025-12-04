package com.codeintune.bookstore.testutils.seeder.sale.dto;

import com.codeintune.bookstore.dto.sale.GetSaleResponseDTO;

import java.time.Instant;
import java.time.format.DateTimeFormatter;

public class GetSaleResponseDTOSeeder {

    public static GetSaleResponseDTO generateResponse(){
        GetSaleResponseDTO getSaleResponseDTO = new GetSaleResponseDTO();
        getSaleResponseDTO.setSaleId("1");
        getSaleResponseDTO.setBookRecordId("1");
        getSaleResponseDTO.setQuantity("1");
        getSaleResponseDTO.setAmount("0");
        getSaleResponseDTO.setDateSold(DateTimeFormatter.ISO_INSTANT.format(Instant.ofEpochMilli(0L)));
        return getSaleResponseDTO;
    }
}
