package com.codeintune.bookstore.service.book;

import com.codeintune.bookstore.dto.book.*;

import java.util.Optional;

public interface BookService {
    AddBookResponseDTO addBook(AddBookRequestDTO request);
    Optional<GetBookResponseDTO> getBookById(GetBookByIdRequestDTO request);
    GetBooksResponseDTO getBooksByAuthor(GetBooksByAuthorRequestDTO requestDTO);
    GetBooksResponseDTO getBooksByTitle(GetBooksByTitleRequestDTO requestDTO);
    Optional<GetBookResponseDTO> updateBookById(UpdateBookByIdRequestDTO requestDTO);
    Optional<GetBookResponseDTO> updateBookQuantityById(UpdateBookQuantityByIdRequestDTO requestDTO);
    void deleteBookById(GetBookByIdRequestDTO requestDTO);
}
