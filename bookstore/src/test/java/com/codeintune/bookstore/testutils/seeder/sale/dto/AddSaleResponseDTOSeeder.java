package com.codeintune.bookstore.testutils.seeder.sale.dto;

import com.codeintune.bookstore.dto.sale.AddSaleResponseDTO;

import java.time.Instant;
import java.time.format.DateTimeFormatter;

public class AddSaleResponseDTOSeeder {

    public static AddSaleResponseDTO generateResponse(){
        AddSaleResponseDTO addSaleResponseDTO = new AddSaleResponseDTO();
        addSaleResponseDTO.setSaleId("1");
        addSaleResponseDTO.setBookRecordId("1");
        addSaleResponseDTO.setQuantity("1");
        addSaleResponseDTO.setAmount("0");
        addSaleResponseDTO.setDateSold(DateTimeFormatter.ISO_INSTANT.format(Instant.ofEpochMilli(0L)));
        return addSaleResponseDTO;
    }
}
