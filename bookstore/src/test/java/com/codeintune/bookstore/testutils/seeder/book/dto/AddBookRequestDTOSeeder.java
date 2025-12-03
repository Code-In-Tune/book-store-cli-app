package com.codeintune.bookstore.testutils.seeder.book.dto;

import com.codeintune.bookstore.dto.book.AddBookRequestDTO;

public class AddBookRequestDTOSeeder {


    public static AddBookRequestDTO generateRequest() {
        AddBookRequestDTO request =  new AddBookRequestDTO();

        request.setTitle("Book Title");
        request.setAuthor("Book Author");
        request.setIsbn("Book ISBN");
        request.setPublisher("Book Publisher");
        request.setQuantity("1");
        request.setPrice("0");

        return request;
    }
}
