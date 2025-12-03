package com.codeintune.bookstore.service.book.impl;

import com.codeintune.bookstore.configuration.service.book.BookServiceConfiguration;
import com.codeintune.bookstore.dto.book.*;
import com.codeintune.bookstore.mapper.book.data.BookMapper;
import com.codeintune.bookstore.mapper.book.record.BookRecordMapper;
import com.codeintune.bookstore.model.book.Book;
import com.codeintune.bookstore.model.book.BookRecord;
import com.codeintune.bookstore.model.book.enums.Availability;
import com.codeintune.bookstore.repository.book.data.BookDataRepository;
import com.codeintune.bookstore.repository.book.record.BookRecordRepository;
import com.codeintune.bookstore.service.book.BookService;
import com.codeintune.bookstore.testutils.seeder.book.data.BookSeeder;
import com.codeintune.bookstore.testutils.seeder.book.dto.*;
import com.codeintune.bookstore.testutils.seeder.book.record.BookRecordSeeder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@ContextConfiguration(classes = {
        BookServiceConfiguration.class
})
@ExtendWith(SpringExtension.class)
class BookServiceImplTest {

    @Autowired
    private BookService bookService;
    @MockitoBean
    private BookRecordMapper bookRecordMapper;
    @MockitoBean
    private BookMapper bookMapper;
    @MockitoBean
    private BookRecordRepository bookRecordRepository;
    @MockitoBean
    private BookDataRepository bookDataRepository;


    @Test
    void addBookTest() {
        AddBookRequestDTO request = AddBookRequestDTOSeeder.generateRequest();
        Book entityBook = BookSeeder.generateBook();
        BookRecord entityBookRecord = BookRecordSeeder.generateBookRecord();
        AddBookResponseDTO response = AddBookResponseDTOSeeder.generateResponse();

        Mockito.when(bookMapper.toEntity(Mockito.any(AddBookRequestDTO.class))).thenReturn(entityBook);
        Mockito.when(bookRecordMapper.toEntity(Mockito.any(AddBookRequestDTO.class))).thenReturn(entityBookRecord);
        Mockito.when(bookRecordMapper.toDto(Mockito.any(BookRecord.class))).thenReturn(response);

        Mockito.doAnswer((invocationOnMock -> {
            AddBookResponseDTO responseToBeUpdated = invocationOnMock.getArgument(0, AddBookResponseDTO.class);
            Book bookPassed = invocationOnMock.getArgument(1, Book.class);

            responseToBeUpdated.setBookId(bookPassed.getBookId().toString());
            responseToBeUpdated.setTitle(bookPassed.getTitle());
            responseToBeUpdated.setAuthor(bookPassed.getAuthor());
            responseToBeUpdated.setPublisher(bookPassed.getPublisher());
            responseToBeUpdated.setIsbn(bookPassed.getIsbn());

            return responseToBeUpdated;
        })).when(bookMapper).updateWithBookData(Mockito.any(AddBookResponseDTO.class), Mockito.any(Book.class));

        AddBookResponseDTO resp = bookService.addBook(request);

        Mockito.verify(bookRecordRepository, Mockito.times(1)).save(Mockito.any(BookRecord.class));
        Mockito.verify(bookDataRepository, Mockito.times(1)).save(Mockito.any(Book.class));

        Assertions.assertAll("Checking field",
                () -> Assertions.assertEquals("Book Title", resp.getTitle(), "Titles do not match"),
                () -> Assertions.assertEquals("1", resp.getBookRecordId(), "Book Record Ids do not match"),
                () -> Assertions.assertEquals("1", resp.getBookId(), "Book Ids do not match"),
                () -> Assertions.assertEquals("Book Author", resp.getAuthor(), "Authors do not match"),
                () -> Assertions.assertEquals("Book ISBN", resp.getIsbn(), "Isbn fields do not match"),
                () -> Assertions.assertEquals(Availability.IN_STOCK.getDescription(), resp.getAvailability(), "Availabilities do not match"),
                () -> Assertions.assertEquals("0", resp.getPrice(), "Prices do not match"),
                () -> Assertions.assertEquals("1", resp.getQuantity(), "Quantities do not match")
        );
    }

    @Test
    void getBookByIdTest() {
        GetBookByIdRequestDTO request = GetBookByIdRequestDTOSeeder.generateRequest();
        Book entityBook = BookSeeder.generateBook();
        BookRecord entityBookRecord = BookRecordSeeder.generateBookRecord();
        GetBookResponseDTO response = GetBookResponseDTOSeeder.generateResponse();

        Mockito.when(bookRecordRepository.findById(Mockito.anyLong()))
                .thenReturn(Optional.of(entityBookRecord));
        Mockito.when(bookDataRepository.findById(Mockito.anyLong()))
                .thenReturn(Optional.of(entityBook));

        Mockito.when(bookRecordMapper.toGetDto(Mockito.any(BookRecord.class)))
                .thenReturn(response);

        Mockito.doAnswer((invocationOnMock) -> {
            GetBookResponseDTO responseToBeUpdated = invocationOnMock.getArgument(0, GetBookResponseDTO.class);
            Book bookPassed = invocationOnMock.getArgument(1, Book.class);

            responseToBeUpdated.setBookId(bookPassed.getBookId().toString());
            responseToBeUpdated.setTitle(bookPassed.getTitle());
            responseToBeUpdated.setAuthor(bookPassed.getAuthor());
            responseToBeUpdated.setPublisher(bookPassed.getPublisher());
            responseToBeUpdated.setIsbn(bookPassed.getIsbn());

            return responseToBeUpdated;
        }).when(bookMapper).updateGetWithBookData(Mockito.any(GetBookResponseDTO.class), Mockito.any(Book.class));

        Optional<GetBookResponseDTO> result = bookService.getBookById(request);

        Assertions.assertTrue(result.isPresent());

        GetBookResponseDTO resp = result.get();

        Assertions.assertAll("Checking fields",
                () -> Assertions.assertEquals("Book Title", resp.getTitle(), "Titles do not match"),
                () -> Assertions.assertEquals("1", resp.getBookRecordId(), "Book Record Ids do not match"),
                () -> Assertions.assertEquals("1", resp.getBookId(), "Book Ids do not match"),
                () -> Assertions.assertEquals("Book Author", resp.getAuthor(), "Authors do not match"),
                () -> Assertions.assertEquals("Book ISBN", resp.getIsbn(), "Isbn fields do not match"),
                () -> Assertions.assertEquals(Availability.IN_STOCK.getDescription(), resp.getAvailability(), "Availabilities do not match"),
                () -> Assertions.assertEquals("0", resp.getPrice(), "Prices do not match"),
                () -> Assertions.assertEquals("1", resp.getQuantity(), "Quantities do not match")
        );
    }

    @Test
    void getBooksByAuthorTest() {
        GetBooksByAuthorRequestDTO request = GetBooksByAuthorRequestDTOSeeder.generateRequest();
        Book entityBook = BookSeeder.generateBook();
        BookRecord entityBookRecord = BookRecordSeeder.generateBookRecord();
        GetBookResponseDTO response = GetBookResponseDTOSeeder.generateResponse();

        Mockito.when(bookDataRepository.findAllByAuthor(Mockito.anyString()))
                .thenReturn(List.of(entityBook));
        Mockito.when(bookRecordRepository.findByBookId(Mockito.anyLong()))
                .thenReturn(Optional.of(entityBookRecord));
        Mockito.when(bookRecordMapper.toGetDto(Mockito.any(BookRecord.class)))
                .thenReturn(response);

        Mockito.doAnswer((invocationOnMock) -> {
            GetBookResponseDTO responseToBeUpdated = invocationOnMock.getArgument(0, GetBookResponseDTO.class);
            Book bookPassed = invocationOnMock.getArgument(1, Book.class);

            responseToBeUpdated.setBookId(bookPassed.getBookId().toString());
            responseToBeUpdated.setTitle(bookPassed.getTitle());
            responseToBeUpdated.setAuthor(bookPassed.getAuthor());
            responseToBeUpdated.setPublisher(bookPassed.getPublisher());
            responseToBeUpdated.setIsbn(bookPassed.getIsbn());

            return responseToBeUpdated;
        }).when(bookMapper).updateGetWithBookData(Mockito.any(GetBookResponseDTO.class), Mockito.any(Book.class));

        List<GetBookResponseDTO> books = bookService.getBooksByAuthor(request).getBooks();

        Assertions.assertFalse(books.isEmpty());
        Assertions.assertEquals(1, books.size());

        GetBookResponseDTO resp = books.get(0);

        Assertions.assertAll("Checking fields",
                () -> Assertions.assertEquals("Book Title", resp.getTitle(), "Titles do not match"),
                () -> Assertions.assertEquals("1", resp.getBookRecordId(), "Book Record Ids do not match"),
                () -> Assertions.assertEquals("1", resp.getBookId(), "Book Ids do not match"),
                () -> Assertions.assertEquals("Book Author", resp.getAuthor(), "Authors do not match"),
                () -> Assertions.assertEquals("Book ISBN", resp.getIsbn(), "Isbn fields do not match"),
                () -> Assertions.assertEquals(Availability.IN_STOCK.getDescription(), resp.getAvailability(), "Availabilities do not match"),
                () -> Assertions.assertEquals("0", resp.getPrice(), "Prices do not match"),
                () -> Assertions.assertEquals("1", resp.getQuantity(), "Quantities do not match")
        );
    }

    @Test
    void getBooksByTitleTest() {
        GetBooksByTitleRequestDTO request = GetBooksByTitleRequestDTOSeeder.generateRequest();
        Book entityBook = BookSeeder.generateBook();
        BookRecord entityBookRecord = BookRecordSeeder.generateBookRecord();
        GetBookResponseDTO response = GetBookResponseDTOSeeder.generateResponse();


        Mockito.when(bookDataRepository.findAllByTitle(Mockito.anyString()))
                .thenReturn(List.of(entityBook));
        Mockito.when(bookRecordRepository.findByBookId(Mockito.anyLong()))
                .thenReturn(Optional.of(entityBookRecord));
        Mockito.when(bookRecordMapper.toGetDto(Mockito.any(BookRecord.class)))
                .thenReturn(response);


        Mockito.doAnswer((invocationOnMock) -> {
            GetBookResponseDTO responseToBeUpdated = invocationOnMock.getArgument(0, GetBookResponseDTO.class);
            Book bookPassed = invocationOnMock.getArgument(1, Book.class);

            responseToBeUpdated.setBookId(bookPassed.getBookId().toString());
            responseToBeUpdated.setTitle(bookPassed.getTitle());
            responseToBeUpdated.setAuthor(bookPassed.getAuthor());
            responseToBeUpdated.setPublisher(bookPassed.getPublisher());
            responseToBeUpdated.setIsbn(bookPassed.getIsbn());

            return responseToBeUpdated;
        }).when(bookMapper).updateGetWithBookData(Mockito.any(GetBookResponseDTO.class), Mockito.any(Book.class));

        List<GetBookResponseDTO> books = bookService.getBooksByTitle(request).getBooks();

        Assertions.assertFalse(books.isEmpty());
        Assertions.assertEquals(1, books.size());

        GetBookResponseDTO resp = books.get(0);

        Assertions.assertAll("Checking fields",
                () -> Assertions.assertEquals("Book Title", resp.getTitle(), "Titles do not match"),
                () -> Assertions.assertEquals("1", resp.getBookRecordId(), "Book Record Ids do not match"),
                () -> Assertions.assertEquals("1", resp.getBookId(), "Book Ids do not match"),
                () -> Assertions.assertEquals("Book Author", resp.getAuthor(), "Authors do not match"),
                () -> Assertions.assertEquals("Book ISBN", resp.getIsbn(), "Isbn fields do not match"),
                () -> Assertions.assertEquals(Availability.IN_STOCK.getDescription(), resp.getAvailability(), "Availabilities do not match"),
                () -> Assertions.assertEquals("0", resp.getPrice(), "Prices do not match"),
                () -> Assertions.assertEquals("1", resp.getQuantity(), "Quantities do not match")
        );
    }

    @Test
    void updateBookByIdTest() {
        UpdateBookByIdRequestDTO request = UpdateBookByIdRequestDTOSeeder.generateRequest();
        Book entityBook = BookSeeder.generateBook();
        BookRecord entityBookRecord = BookRecordSeeder.generateBookRecord();
        GetBookResponseDTO response = GetBookResponseDTOSeeder.generateResponse();

        Mockito.when(bookDataRepository.findById(Mockito.anyLong()))
                .thenReturn(Optional.of(entityBook));
        Mockito.when(bookRecordRepository.findById(Mockito.anyLong()))
                .thenReturn(Optional.of(entityBookRecord));
        Mockito.when(bookRecordMapper.toGetDto(Mockito.any(BookRecord.class)))
                .thenReturn(response);

        Mockito.doAnswer((invocationOnMock -> {
                    BookRecord bookRecordToUpdate = invocationOnMock.getArgument(0, BookRecord.class);
                    UpdateBookByIdRequestDTO updateBookByIdRequestDTO = invocationOnMock.getArgument(1, UpdateBookByIdRequestDTO.class);

                    bookRecordToUpdate.setPrice(new BigDecimal(updateBookByIdRequestDTO.getPrice()));

                    return null;
                })).when(bookRecordMapper)
                .updateEntity(Mockito.any(BookRecord.class), Mockito.any(UpdateBookByIdRequestDTO.class));

        Mockito.doAnswer(invocationOnMock -> {
            Book bookToUpdate = invocationOnMock.getArgument(0, Book.class);
            UpdateBookByIdRequestDTO updateBookByIdRequestDTO = invocationOnMock.getArgument(1, UpdateBookByIdRequestDTO.class);

            bookToUpdate.setTitle(updateBookByIdRequestDTO.getTitle());
            bookToUpdate.setAuthor(updateBookByIdRequestDTO.getAuthor());
            bookToUpdate.setIsbn(updateBookByIdRequestDTO.getIsbn());
            bookToUpdate.setPublisher(updateBookByIdRequestDTO.getPublisher());

            return null;

        }).when(bookMapper).updateEntity(Mockito.any(Book.class), Mockito.any(UpdateBookByIdRequestDTO.class));

        Mockito.doAnswer((invocationOnMock -> {
                    GetBookResponseDTO getBookResponseDTO = invocationOnMock.getArgument(0, GetBookResponseDTO.class);
                    Book bookUpdated = invocationOnMock.getArgument(1, Book.class);

                    getBookResponseDTO.setBookId(bookUpdated.getBookId().toString());
                    getBookResponseDTO.setTitle(bookUpdated.getTitle());
                    getBookResponseDTO.setAuthor(bookUpdated.getAuthor());
                    getBookResponseDTO.setIsbn(bookUpdated.getIsbn());
                    getBookResponseDTO.setPublisher(bookUpdated.getPublisher());

                    return null;
                })).when(bookMapper)
                .updateGetWithBookData(Mockito.any(GetBookResponseDTO.class), Mockito.any(Book.class));

        Optional<GetBookResponseDTO> resultOptional = bookService.updateBookById(request);

        Assertions.assertTrue(resultOptional.isPresent());
        Mockito.verify(bookRecordRepository, Mockito.times(1)).save(Mockito.any(BookRecord.class));
        Mockito.verify(bookDataRepository, Mockito.times(1)).save(Mockito.any(Book.class));

        GetBookResponseDTO resp = resultOptional.get();

        Assertions.assertAll("Checking fields",
                () -> Assertions.assertEquals("Book Title", resp.getTitle(), "Titles do not match"),
                () -> Assertions.assertEquals("1", resp.getBookRecordId(), "Book Record Ids do not match"),
                () -> Assertions.assertEquals("1", resp.getBookId(), "Book Ids do not match"),
                () -> Assertions.assertEquals("Book Author", resp.getAuthor(), "Authors do not match"),
                () -> Assertions.assertEquals("Book ISBN", resp.getIsbn(), "Isbn fields do not match"),
                () -> Assertions.assertEquals(Availability.IN_STOCK.getDescription(), resp.getAvailability(), "Availabilities do not match"),
                () -> Assertions.assertEquals("0", resp.getPrice(), "Prices do not match"),
                () -> Assertions.assertEquals("1", resp.getQuantity(), "Quantities do not match")
        );
    }

    @Test
    void updateBookQuantityByIdTest() {
        UpdateBookQuantityByIdRequestDTO request = UpdateBookQuantityByIdRequestDTOSeeder.generateRequest();
        Book entityBook = BookSeeder.generateBook();
        BookRecord entityBookRecord = BookRecordSeeder.generateBookRecord();
        GetBookResponseDTO response = GetBookResponseDTOSeeder.generateResponse();

        Mockito.when(bookDataRepository.findById(Mockito.anyLong()))
                .thenReturn(Optional.of(entityBook));
        Mockito.when(bookRecordRepository.findById(Mockito.anyLong()))
                .thenReturn(Optional.of(entityBookRecord));
        Mockito.when(bookRecordMapper.toGetDto(Mockito.any(BookRecord.class)))
                .thenReturn(response);


        Mockito.doAnswer((invocationOnMock -> {
                    GetBookResponseDTO getBookResponseDTO = invocationOnMock.getArgument(0, GetBookResponseDTO.class);
                    Book bookUpdated = invocationOnMock.getArgument(1, Book.class);

                    getBookResponseDTO.setBookId(bookUpdated.getBookId().toString());
                    getBookResponseDTO.setTitle(bookUpdated.getTitle());
                    getBookResponseDTO.setAuthor(bookUpdated.getAuthor());
                    getBookResponseDTO.setIsbn(bookUpdated.getIsbn());
                    getBookResponseDTO.setPublisher(bookUpdated.getPublisher());

                    return null;
                })).when(bookMapper)
                .updateGetWithBookData(Mockito.any(GetBookResponseDTO.class), Mockito.any(Book.class));

        Optional<GetBookResponseDTO> resultOptional = bookService.updateBookQuantityById(request);

        Assertions.assertTrue(resultOptional.isPresent());
        Mockito.verify(bookRecordRepository, Mockito.times(1)).save(Mockito.any(BookRecord.class));

        GetBookResponseDTO resp = resultOptional.get();

        Assertions.assertAll("Checking fields",
                () -> Assertions.assertEquals("Book Title", resp.getTitle(), "Titles do not match"),
                () -> Assertions.assertEquals("1", resp.getBookRecordId(), "Book Record Ids do not match"),
                () -> Assertions.assertEquals("1", resp.getBookId(), "Book Ids do not match"),
                () -> Assertions.assertEquals("Book Author", resp.getAuthor(), "Authors do not match"),
                () -> Assertions.assertEquals("Book ISBN", resp.getIsbn(), "Isbn fields do not match"),
                () -> Assertions.assertEquals(Availability.IN_STOCK.getDescription(), resp.getAvailability(), "Availabilities do not match"),
                () -> Assertions.assertEquals("0", resp.getPrice(), "Prices do not match"),
                () -> Assertions.assertEquals("1", resp.getQuantity(), "Quantities do not match")
        );

    }

    @Test
    void deleteBookByIdTest() {
        GetBookByIdRequestDTO request = GetBookByIdRequestDTOSeeder.generateRequest();
        BookRecord entityBookRecord = BookRecordSeeder.generateBookRecord();

        Mockito.when(bookRecordRepository.findById(Mockito.anyLong()))
                .thenReturn(Optional.of(entityBookRecord));

        bookService.deleteBookById(request);

        Mockito.verify(bookDataRepository, Mockito.times(1))
                .deleteById(Mockito.anyLong());
        Mockito.verify(bookRecordRepository, Mockito.times(1))
                .deleteById(Mockito.anyLong());

    }
}