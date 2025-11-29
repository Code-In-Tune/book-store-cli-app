package com.codeintune.bookstore.service.book;

import com.codeintune.bookstore.dto.book.*;

public interface BookInputService {

    AddBookRequestDTO buildBookRequestDTO();
    GetBookByIdRequestDTO buildGetBookByIdRequestDTO();
    GetBooksByAuthorRequestDTO buildGetBooksByAuthorRequestDTO();
    GetBooksByTitleRequestDTO buildGetBooksByTitleRequestDTO();
    UpdateBookByIdRequestDTO buildUpdateBookByIdRequestDTO();
    UpdateBookQuantityByIdRequestDTO buildUpdateBookQuantityByIdRequestDTO();
}
