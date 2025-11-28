package com.codeintune.bookstore.service.book;

import com.codeintune.bookstore.dto.book.AddBookRequestDTO;
import com.codeintune.bookstore.dto.book.GetBookByIdRequestDTO;

public interface BookInputService {

    AddBookRequestDTO buildBookRequestDTO();
    GetBookByIdRequestDTO buildGetBookByIdRequestDTO();
}
