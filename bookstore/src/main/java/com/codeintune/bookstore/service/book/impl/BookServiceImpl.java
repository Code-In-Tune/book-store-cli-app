package com.codeintune.bookstore.service.book.impl;

import com.codeintune.bookstore.dto.book.*;
import com.codeintune.bookstore.mapper.book.data.BookMapper;
import com.codeintune.bookstore.mapper.book.record.BookRecordMapper;
import com.codeintune.bookstore.model.book.Book;
import com.codeintune.bookstore.model.book.BookRecord;
import com.codeintune.bookstore.model.book.enums.Availability;
import com.codeintune.bookstore.repository.book.data.BookDataRepository;
import com.codeintune.bookstore.repository.book.record.BookRecordRepository;
import com.codeintune.bookstore.service.book.BookService;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

/**
 * Implementation of {@link BookService} to perform CRUD operations on book and book record
 * domains.
 */
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRecordMapper bookRecordMapper;
    private final BookMapper bookMapper;
    private final BookDataRepository bookDataRepository;
    private final BookRecordRepository bookRecordRepository;

    @Override
    public AddBookResponseDTO addBook(AddBookRequestDTO request) {
        Book book = bookMapper.toEntity(request);
        bookDataRepository.save(book);

        BookRecord bookRecord = bookRecordMapper.toEntity(request);
        bookRecord.setBookId(book.getBookId());

        bookRecordRepository.save(bookRecord);

        AddBookResponseDTO addBookResponseDTO = bookRecordMapper.toDto(bookRecord);
        bookMapper.updateWithBookData(addBookResponseDTO, book);

        return addBookResponseDTO;
    }

    @Override
    public Optional<GetBookResponseDTO> getBookById(GetBookByIdRequestDTO request) {
        return bookRecordRepository.findById(request.getBookRecordId())
                .flatMap(br ->
                        bookDataRepository.findById(br.getBookId())
                                .map(book -> {
                                    GetBookResponseDTO dto = bookRecordMapper.toGetDto(br);
                                    bookMapper.updateGetWithBookData(dto, book);
                                    return dto;
                                })
                );
    }

    @Override
    public GetBooksResponseDTO getBooksByAuthor(GetBooksByAuthorRequestDTO requestDTO) {
        List<Book> books = bookDataRepository.findAllByAuthor(requestDTO.getAuthor());
        var listOfResponse = books.stream().map((b) -> bookRecordRepository.findByBookId(b.getBookId()).map(record -> {
             GetBookResponseDTO dto = bookRecordMapper.toGetDto(record);
             bookMapper.updateGetWithBookData(dto, b);
             return dto;
         })).flatMap(Optional::stream)
                .toList();
        GetBooksResponseDTO responseDTO = new GetBooksResponseDTO();
        responseDTO.setBooks(listOfResponse);
        return responseDTO;
    }

    @Override
    public GetBooksResponseDTO getBooksByTitle(GetBooksByTitleRequestDTO requestDTO) {
        List<Book> books = bookDataRepository.findAllByTitle(requestDTO.getTitle());
        var listOfResponse = books.stream().map((b) -> bookRecordRepository.findByBookId(b.getBookId()).map(record -> {
                    GetBookResponseDTO dto = bookRecordMapper.toGetDto(record);
                    bookMapper.updateGetWithBookData(dto, b);
                    return dto;
                })).flatMap(Optional::stream)
                .toList();
        GetBooksResponseDTO responseDTO = new GetBooksResponseDTO();
        responseDTO.setBooks(listOfResponse);
        return responseDTO;
    }


    @Override
    public Optional<GetBookResponseDTO> updateBookById(UpdateBookByIdRequestDTO requestDTO) {
        Optional<BookRecord> bookRecord = bookRecordRepository.findById(Long.parseLong(requestDTO.getBookRecordId()));
        return bookRecord.flatMap(br ->
            bookDataRepository.findById(br.getBookId())
                    .map(b -> {
                        bookRecordMapper.updateEntity(br, requestDTO);
                        bookRecordRepository.save(br);
                        bookMapper.updateEntity(b, requestDTO);
                        bookDataRepository.save(b);
                        GetBookResponseDTO dto = bookRecordMapper.toGetDto(br);
                        bookMapper.updateGetWithBookData(dto, b);
                        return dto;
                    })
        );
    }


    @Override
    public Optional<GetBookResponseDTO> updateBookQuantityById(UpdateBookQuantityByIdRequestDTO requestDTO) {
        Optional<BookRecord> bookRecord = bookRecordRepository.findById(Long.parseLong(requestDTO.getBookRecordId()));
        return bookRecord.flatMap(br -> {
            br.setQuantity(br.getQuantity() + Integer.parseInt(requestDTO.getQuantity()));
            br.setAvailability(Availability.IN_STOCK);
            return bookDataRepository.findById(br.getBookId())
                    .map(b -> {
                        bookRecordRepository.save(br);
                        GetBookResponseDTO dto = bookRecordMapper.toGetDto(br);
                        bookMapper.updateGetWithBookData(dto, b);
                        return dto;
                    });
        });
    }

    @Override
    public void deleteBookById(GetBookByIdRequestDTO requestDTO) {
        Optional<BookRecord> bookRecord = bookRecordRepository.findById(requestDTO.getBookRecordId());
        bookRecord.ifPresent(br -> {
            bookDataRepository.deleteById(br.getBookId());
            bookRecordRepository.deleteById(br.getBookRecordId());
        });
    }
}
