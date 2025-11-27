package com.codeintune.bookstore.service.book.impl;

import com.codeintune.bookstore.dto.book.AddBookRequestDTO;
import com.codeintune.bookstore.dto.book.AddBookResponseDTO;
import com.codeintune.bookstore.mapper.book.data.BookMapper;
import com.codeintune.bookstore.mapper.book.record.BookRecordMapper;
import com.codeintune.bookstore.model.book.Book;
import com.codeintune.bookstore.model.book.BookRecord;
import com.codeintune.bookstore.repository.book.data.BookDataRepository;
import com.codeintune.bookstore.repository.book.record.BookRecordRepository;
import com.codeintune.bookstore.service.book.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
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
}
