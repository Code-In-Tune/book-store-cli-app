package com.codeintune.bookstore.testutils.seeder.book.dto;

import com.codeintune.bookstore.dto.book.GetBookResponseDTO;
import com.codeintune.bookstore.model.book.enums.Availability;

public class GetBookResponseDTOSeeder {

    public static GetBookResponseDTO generateResponse(){
        GetBookResponseDTO responseDTO = new GetBookResponseDTO();

        responseDTO.setTitle("Book Title");
        responseDTO.setAuthor("Book Author");
        responseDTO.setIsbn("Book ISBN");
        responseDTO.setPublisher("Book Publisher");
        responseDTO.setQuantity("1");
        responseDTO.setPrice("0");
        responseDTO.setBookId("1");
        responseDTO.setBookRecordId("1");
        responseDTO.setAvailability(Availability.IN_STOCK.getDescription());

        return responseDTO;
    }
}
