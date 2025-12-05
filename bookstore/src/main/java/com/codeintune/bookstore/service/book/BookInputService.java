package com.codeintune.bookstore.service.book;

import com.codeintune.bookstore.dto.book.*;

/**
 * Contract defining how to gather input for book and book record domains
 */
public interface BookInputService {

    AddBookRequestDTO buildBookRequestDTO();
    GetBookByIdRequestDTO buildGetBookByIdRequestDTO();
    GetBooksByAuthorRequestDTO buildGetBooksByAuthorRequestDTO();
    GetBooksByTitleRequestDTO buildGetBooksByTitleRequestDTO();
    UpdateBookByIdRequestDTO buildUpdateBookByIdRequestDTO();
    UpdateBookQuantityByIdRequestDTO buildUpdateBookQuantityByIdRequestDTO();
}
