package com.codeintune.bookstore.service.book;

import com.codeintune.bookstore.dto.book.AddBookRequestDTO;

public interface BookInputService {

    AddBookRequestDTO buildBookRequestDTO();
}
