package com.codeintune.bookstore.testutils.seeder.book.dto;

import com.codeintune.bookstore.dto.book.GetBookByIdRequestDTO;

public class GetBookByIdRequestDTOSeeder {

    public static GetBookByIdRequestDTO generateRequest(){
        GetBookByIdRequestDTO getBookByIdRequestDTO = new GetBookByIdRequestDTO();

        getBookByIdRequestDTO.setBookRecordId(1L);

        return getBookByIdRequestDTO;
    }
}
