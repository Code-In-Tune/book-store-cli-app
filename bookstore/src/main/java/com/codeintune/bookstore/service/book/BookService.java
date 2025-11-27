package com.codeintune.bookstore.service.book;

import com.codeintune.bookstore.dto.book.AddBookRequestDTO;
import com.codeintune.bookstore.dto.book.AddBookResponseDTO;

public interface BookService {
    AddBookResponseDTO addBook(AddBookRequestDTO request);
}
