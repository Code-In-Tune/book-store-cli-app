package com.codeintune.bookstore.testutils.seeder.book.dto;

import com.codeintune.bookstore.dto.book.GetBooksByAuthorRequestDTO;

public class GetBooksByAuthorRequestDTOSeeder {

    public static GetBooksByAuthorRequestDTO generateRequest(){
        GetBooksByAuthorRequestDTO requestDTO = new GetBooksByAuthorRequestDTO();

        requestDTO.setAuthor("Book Author");

        return requestDTO;
    }
}
