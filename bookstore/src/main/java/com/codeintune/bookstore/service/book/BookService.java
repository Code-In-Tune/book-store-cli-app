package com.codeintune.bookstore.service.book;

import com.codeintune.bookstore.dto.book.AddBookRequestDTO;
import com.codeintune.bookstore.dto.book.AddBookResponseDTO;
import com.codeintune.bookstore.dto.book.GetBookByIdRequestDTO;
import com.codeintune.bookstore.dto.book.GetBookResponseDTO;

import java.util.Optional;

public interface BookService {
    AddBookResponseDTO addBook(AddBookRequestDTO request);
    Optional<GetBookResponseDTO> getBookById(GetBookByIdRequestDTO request);
}
