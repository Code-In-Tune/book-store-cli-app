package com.codeintune.bookstore.mapper.book.data.impl;

import com.codeintune.bookstore.dto.book.AddBookRequestDTO;
import com.codeintune.bookstore.dto.book.AddBookResponseDTO;
import com.codeintune.bookstore.dto.book.GetBookResponseDTO;
import com.codeintune.bookstore.dto.book.UpdateBookByIdRequestDTO;
import com.codeintune.bookstore.mapper.book.data.BookMapper;
import com.codeintune.bookstore.model.book.Book;
import com.codeintune.bookstore.testutils.seeder.book.data.BookSeeder;
import com.codeintune.bookstore.testutils.seeder.book.dto.AddBookRequestDTOSeeder;
import com.codeintune.bookstore.testutils.seeder.book.dto.GetBookResponseDTOSeeder;
import com.codeintune.bookstore.testutils.seeder.book.dto.UpdateBookByIdRequestDTOSeeder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = BookMapperImpl.class)
@ExtendWith(SpringExtension.class)
class BookMapperImplTest {

    @Autowired
    private BookMapper bookMapper;

    @Test
    void toEntityTest() {
        AddBookRequestDTO dto = AddBookRequestDTOSeeder.generateRequest();
        Book bookResult = BookSeeder.generateBook();

        Book bookOutputMapper = bookMapper.toEntity(dto);

        Assertions.assertAll("Checking Fields",
                () -> Assertions.assertEquals(bookResult.getTitle(), bookOutputMapper.getTitle(), "Title do not match"),
                () -> Assertions.assertEquals(bookResult.getAuthor(), bookOutputMapper.getAuthor(), "Author do not match"),
                () -> Assertions.assertEquals(bookResult.getIsbn(), bookOutputMapper.getIsbn(), "Isbn field do not match"),
                () -> Assertions.assertEquals(bookResult.getPublisher(), bookOutputMapper.getPublisher(), "Book Publisher do not match")
        );
    }

    @Test
    void updateWithBookDataTest() {
        AddBookResponseDTO dto = new AddBookResponseDTO();
        AddBookRequestDTO responseUpdated = AddBookRequestDTOSeeder.generateRequest();
        Book bookData = BookSeeder.generateBook();

        bookMapper.updateWithBookData(dto, bookData);

        Assertions.assertAll("Checking Fields",
                () -> Assertions.assertEquals(responseUpdated.getTitle(), dto.getTitle(), "Title do not match"),
                () -> Assertions.assertEquals(responseUpdated.getAuthor(), dto.getAuthor(), "Author do not match"),
                () -> Assertions.assertEquals(responseUpdated.getIsbn(), dto.getIsbn(), "Isbn field do not match"),
                () -> Assertions.assertEquals(responseUpdated.getPublisher(), dto.getPublisher(), "Book Publisher do not match")
        );
    }

    @Test
    void updateGetWithBookDataTest() {
        GetBookResponseDTO dto = new GetBookResponseDTO();
        GetBookResponseDTO responseUpdated = GetBookResponseDTOSeeder.generateResponse();
        Book bookData = BookSeeder.generateBook();


        bookMapper.updateGetWithBookData(dto, bookData);

        Assertions.assertAll("Checking Fields",
                () -> Assertions.assertEquals(responseUpdated.getTitle(), dto.getTitle(), "Title do not match"),
                () -> Assertions.assertEquals(responseUpdated.getAuthor(), dto.getAuthor(), "Author do not match"),
                () -> Assertions.assertEquals(responseUpdated.getIsbn(), dto.getIsbn(), "Isbn field do not match"),
                () -> Assertions.assertEquals(responseUpdated.getPublisher(), dto.getPublisher(), "Book Publisher do not match")
        );
    }

    @Test
    void updateEntityTest() {
        UpdateBookByIdRequestDTO request = UpdateBookByIdRequestDTOSeeder.generateRequest();
        Book bookToUpdate = new Book();
        Book bookData = BookSeeder.generateBook();

        bookMapper.updateEntity(bookToUpdate, request);

        Assertions.assertAll("Checking Fields",
                () -> Assertions.assertEquals(bookData.getTitle(), bookToUpdate.getTitle(), "Title do not match"),
                () -> Assertions.assertEquals(bookData.getAuthor(), bookToUpdate.getAuthor(), "Author do not match"),
                () -> Assertions.assertEquals(bookData.getIsbn(), bookToUpdate.getIsbn(), "Isbn do not match"),
                () -> Assertions.assertEquals(bookData.getPublisher(), bookToUpdate.getPublisher(), "Book Publisher do not match")
        );
    }
}