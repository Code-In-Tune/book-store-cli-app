package com.codeintune.bookstore.testutils.seeder.book.dto;

import com.codeintune.bookstore.dto.book.UpdateBookByIdRequestDTO;

public class UpdateBookByIdRequestDTOSeeder {

    public static UpdateBookByIdRequestDTO generateRequest() {
        UpdateBookByIdRequestDTO request = new UpdateBookByIdRequestDTO();

        request.setBookRecordId("1");
        request.setTitle("Book Title");
        request.setAuthor("Book Author");
        request.setIsbn("Book ISBN");
        request.setPublisher("Book Publisher");
        request.setPrice("0");

        return request;
    }
}
