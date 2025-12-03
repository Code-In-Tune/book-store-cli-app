package com.codeintune.bookstore.testutils.seeder.book.dto;

import com.codeintune.bookstore.dto.book.GetBooksByTitleRequestDTO;

public class GetBooksByTitleRequestDTOSeeder {

    public static GetBooksByTitleRequestDTO generateRequest(){
        GetBooksByTitleRequestDTO requestDTO = new GetBooksByTitleRequestDTO();

        requestDTO.setTitle("Book Title");

        return requestDTO;
    }
}
