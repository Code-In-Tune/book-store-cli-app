package com.codeintune.bookstore.testutils.seeder.book.dto;

import com.codeintune.bookstore.dto.book.UpdateBookQuantityByIdRequestDTO;

public class UpdateBookQuantityByIdRequestDTOSeeder {

    public static UpdateBookQuantityByIdRequestDTO generateRequest(){
        UpdateBookQuantityByIdRequestDTO request = new UpdateBookQuantityByIdRequestDTO();

        request.setBookRecordId("1");
        request.setQuantity("1");

        return request;
    }
}
