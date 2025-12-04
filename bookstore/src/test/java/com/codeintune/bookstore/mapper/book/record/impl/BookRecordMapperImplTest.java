package com.codeintune.bookstore.mapper.book.record.impl;

import com.codeintune.bookstore.dto.book.AddBookRequestDTO;
import com.codeintune.bookstore.dto.book.AddBookResponseDTO;
import com.codeintune.bookstore.dto.book.GetBookResponseDTO;
import com.codeintune.bookstore.dto.book.UpdateBookByIdRequestDTO;
import com.codeintune.bookstore.mapper.book.record.BookRecordMapper;
import com.codeintune.bookstore.model.book.BookRecord;
import com.codeintune.bookstore.testutils.seeder.book.dto.AddBookRequestDTOSeeder;
import com.codeintune.bookstore.testutils.seeder.book.dto.AddBookResponseDTOSeeder;
import com.codeintune.bookstore.testutils.seeder.book.dto.GetBookResponseDTOSeeder;
import com.codeintune.bookstore.testutils.seeder.book.dto.UpdateBookByIdRequestDTOSeeder;
import com.codeintune.bookstore.testutils.seeder.book.record.BookRecordSeeder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = BookRecordMapperImpl.class)
@ExtendWith(SpringExtension.class)
class BookRecordMapperImplTest {

    @Autowired
    private BookRecordMapper bookRecordMapper;

    @Test
    void toEntityTest() {
        AddBookRequestDTO request = AddBookRequestDTOSeeder.generateRequest();
        BookRecord entity = BookRecordSeeder.generateBookRecord();

        BookRecord result = bookRecordMapper.toEntity(request);

        Assertions.assertAll("Checking Fields",
                () -> Assertions.assertEquals(entity.getAvailability(), result.getAvailability(), "Availability do not match"),
                () -> Assertions.assertEquals(entity.getQuantity(), result.getQuantity(), "Quantity do not match"),
                () -> Assertions.assertEquals(entity.getPrice(), result.getPrice(), "Price do not match")

        );
    }

    @Test
    void toDtoTest() {
        BookRecord entity = BookRecordSeeder.generateBookRecord();
        AddBookResponseDTO response = bookRecordMapper.toDto(entity);
        AddBookResponseDTO expectedResponse = AddBookResponseDTOSeeder.generateResponse();

        Assertions.assertAll("Checking Fields",
                () -> Assertions.assertEquals(expectedResponse.getAvailability(), response.getAvailability(), "Availability do not match"),
                () -> Assertions.assertEquals(expectedResponse.getQuantity(), response.getQuantity(), "Quantity do not match"),
                () -> Assertions.assertEquals(expectedResponse.getPrice(), response.getPrice(), "Price do not match")
        );
    }

    @Test
    void toGetDtoTest() {
        BookRecord entity = BookRecordSeeder.generateBookRecord();
        GetBookResponseDTO expectedResponse = GetBookResponseDTOSeeder.generateResponse();
        GetBookResponseDTO response = bookRecordMapper.toGetDto(entity);

        Assertions.assertAll("Checking Fields",
                () -> Assertions.assertEquals(expectedResponse.getAvailability(), response.getAvailability(), "Availability do not match"),
                () -> Assertions.assertEquals(expectedResponse.getQuantity(), response.getQuantity(), "Quantity do not match"),
                () -> Assertions.assertEquals(expectedResponse.getPrice(), response.getPrice(), "Price do not match")
        );
    }

    @Test
    void updateEntityTest() {
        BookRecord entity = new BookRecord();
        UpdateBookByIdRequestDTO request = UpdateBookByIdRequestDTOSeeder.generateRequest();
        BookRecord expected = BookRecordSeeder.generateBookRecord();

        bookRecordMapper.updateEntity(entity, request);

        Assertions.assertEquals(expected.getPrice(), entity.getPrice(), "Price do not match");
    }
}