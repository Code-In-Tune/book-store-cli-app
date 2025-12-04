package com.codeintune.bookstore.testutils.seeder.sale.dto;

import com.codeintune.bookstore.dto.sale.AddSaleRequestDTO;

public class AddSaleRequestDTOSeeder {
    public static AddSaleRequestDTO generateRequest(){
        AddSaleRequestDTO addSaleRequestDTO = new AddSaleRequestDTO();
        addSaleRequestDTO.setBookRecordId("1");
        addSaleRequestDTO.setQuantity("1");
        return addSaleRequestDTO;
    }
}
