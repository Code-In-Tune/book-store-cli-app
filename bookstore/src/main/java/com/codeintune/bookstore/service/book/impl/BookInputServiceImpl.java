package com.codeintune.bookstore.service.book.impl;

import com.codeintune.bookstore.dto.book.*;
import com.codeintune.bookstore.service.book.BookInputService;
import com.codeintune.bookstore.utils.constants.service.BookInputServiceConstants;
import com.codeintune.bookstore.utils.constants.validator.InputFieldConstants;
import com.codeintune.bookstore.validator.Validator;
import com.codeintune.bookstore.validator.field.InputField;
import lombok.RequiredArgsConstructor;

import java.util.Scanner;


@RequiredArgsConstructor
public class BookInputServiceImpl implements BookInputService {

    private final Scanner scanner;

    private final Validator<InputField> inputNameValidator;
    private final Validator<InputField> inputIsbnValidator;
    private final Validator<InputField> inputQuantityValidator;
    private final Validator<InputField> inputPriceValidator;
    private final Validator<InputField> inputIdValidator;

    @Override
    public AddBookRequestDTO buildBookRequestDTO() {
        System.out.println(BookInputServiceConstants.INSERT_BOOK_TITLE_MESSAGE);

        String title = scanner.nextLine().trim();

        InputField titleField = new InputField();
        titleField.setField(InputFieldConstants.TITLE);
        titleField.setValue(title);

        inputNameValidator.validate(titleField);

        System.out.println(BookInputServiceConstants.INSERT_BOOK_AUTHOR_MESSAGE);

        String author = scanner.nextLine().trim();

        InputField authorField = new InputField();
        authorField.setField(InputFieldConstants.AUTHOR);
        authorField.setValue(author);

       inputNameValidator.validate(authorField);

       System.out.println(BookInputServiceConstants.INSERT_BOOK_ISBN_MESSAGE);

        String isbn = scanner.nextLine().trim();

        InputField isbnField = new InputField();
        isbnField.setField(InputFieldConstants.ISBN);
        isbnField.setValue(isbn);

        inputIsbnValidator.validate(isbnField);

        System.out.println(BookInputServiceConstants.INSERT_BOOK_QUANTITY);

        String quantity = scanner.nextLine().trim();

        InputField quantityField = new InputField();
        quantityField.setField(InputFieldConstants.QUANTITY);
        quantityField.setValue(quantity);

        inputQuantityValidator.validate(quantityField);


        System.out.println(BookInputServiceConstants.INSERT_BOOK_PRICE_MESSAGE);

        String price = scanner.nextLine().trim();

        InputField priceField = new InputField();
        priceField.setField(InputFieldConstants.PRICE);
        priceField.setValue(price);

        inputPriceValidator.validate(priceField);


        System.out.println(BookInputServiceConstants.INSERT_BOOK_PUBLISHER_MESSAGE);

        String publisher = scanner.nextLine().trim();

        InputField publisherField = new InputField();
        publisherField.setField(InputFieldConstants.PUBLISHER);
        publisherField.setValue(publisher);

        inputNameValidator.validate(publisherField);

        AddBookRequestDTO bookRequestDTO = new AddBookRequestDTO();
        bookRequestDTO.setTitle(title);
        bookRequestDTO.setAuthor(author);
        bookRequestDTO.setIsbn(isbn);
        bookRequestDTO.setQuantity(quantity);
        bookRequestDTO.setPrice(price);
        bookRequestDTO.setPublisher(publisher);

        return bookRequestDTO;
    }

    @Override
    public GetBookByIdRequestDTO buildGetBookByIdRequestDTO() {
        System.out.println(BookInputServiceConstants.INSERT_BOOK_RECORD_ID_MESSAGE);

        String id = scanner.nextLine().trim();

        InputField recordIdField = new InputField();
        recordIdField.setField(InputFieldConstants.BOOK_RECORD_ID);
        recordIdField.setValue(id);

        inputIdValidator.validate(recordIdField);

        GetBookByIdRequestDTO requestDTO = new GetBookByIdRequestDTO();
        requestDTO.setBookRecordId(Long.parseLong(id));
        return requestDTO;
    }

    @Override
    public GetBooksByAuthorRequestDTO buildGetBooksByAuthorRequestDTO() {
        System.out.println(BookInputServiceConstants.INSERT_BOOK_AUTHOR_MESSAGE);

        String author = scanner.nextLine().trim();

        InputField authorField = new InputField();
        authorField.setField(InputFieldConstants.AUTHOR);
        authorField.setValue(author);

        inputNameValidator.validate(authorField);

        GetBooksByAuthorRequestDTO requestDTO = new GetBooksByAuthorRequestDTO();
        requestDTO.setAuthor(author);
        return requestDTO;
    }

    @Override
    public GetBooksByTitleRequestDTO buildGetBooksByTitleRequestDTO() {
        System.out.println(BookInputServiceConstants.INSERT_BOOK_TITLE_MESSAGE);

        String title = scanner.nextLine().trim();

        InputField titleField = new InputField();
        titleField.setField(InputFieldConstants.TITLE);
        titleField.setValue(title);

        inputNameValidator.validate(titleField);

        GetBooksByTitleRequestDTO requestDTO = new GetBooksByTitleRequestDTO();
        requestDTO.setTitle(title);
        return requestDTO;
    }

    @Override
    public UpdateBookByIdRequestDTO buildUpdateBookByIdRequestDTO() {



        System.out.println(BookInputServiceConstants.INSERT_BOOK_RECORD_ID_MESSAGE);



        String id = scanner.nextLine().trim();


        InputField recordIdField = new InputField();
        recordIdField.setField(InputFieldConstants.BOOK_RECORD_ID);
        recordIdField.setValue(id);
        inputIdValidator.validate(recordIdField);


        System.out.println(BookInputServiceConstants.LEAVE_BLANK_MESSAGE);

        System.out.println();


        System.out.println(BookInputServiceConstants.INSERT_BOOK_TITLE_MESSAGE);

        String title = scanner.nextLine().trim();

        InputField titleField = new InputField();
        titleField.setField(InputFieldConstants.TITLE);
        titleField.setValue(title);

        if(!title.isBlank()){
            inputNameValidator.validate(titleField);
        }

        System.out.println(BookInputServiceConstants.INSERT_BOOK_AUTHOR_MESSAGE);

        String author = scanner.nextLine().trim();

        InputField authorField = new InputField();
        authorField.setField(InputFieldConstants.AUTHOR);
        authorField.setValue(author);

        if(!author.isBlank()){
            inputNameValidator.validate(authorField);
        }

        System.out.println(BookInputServiceConstants.INSERT_BOOK_ISBN_MESSAGE);

        String isbn = scanner.nextLine().trim();

        InputField isbnField = new InputField();
        isbnField.setField(InputFieldConstants.ISBN);
        isbnField.setValue(isbn);

        if(!isbn.isBlank()){
            inputIsbnValidator.validate(isbnField);
        }



        System.out.println(BookInputServiceConstants.INSERT_BOOK_PRICE_MESSAGE);

        String price = scanner.nextLine().trim();

        InputField priceField = new InputField();
        priceField.setField(InputFieldConstants.PRICE);
        priceField.setValue(price);

        if(!price.isBlank()){
            inputPriceValidator.validate(priceField);
        }


        System.out.println(BookInputServiceConstants.INSERT_BOOK_PUBLISHER_MESSAGE);

        String publisher = scanner.nextLine().trim();

        InputField publisherField = new InputField();
        publisherField.setField(InputFieldConstants.PUBLISHER);
        publisherField.setValue(publisher);

        if (!publisher.isBlank()){
            inputNameValidator.validate(publisherField);
        }


        UpdateBookByIdRequestDTO requestDTO = new UpdateBookByIdRequestDTO();
        requestDTO.setBookRecordId(id);
        requestDTO.setTitle(title);
        requestDTO.setAuthor(author);
        requestDTO.setIsbn(isbn);
        requestDTO.setPrice(price);
        requestDTO.setPublisher(publisher);
        return requestDTO;
    }

    @Override
    public UpdateBookQuantityByIdRequestDTO buildUpdateBookQuantityByIdRequestDTO() {
        System.out.println(BookInputServiceConstants.INSERT_BOOK_RECORD_ID_MESSAGE);



        String id = scanner.nextLine().trim();


        InputField recordIdField = new InputField();
        recordIdField.setField(InputFieldConstants.BOOK_RECORD_ID);
        recordIdField.setValue(id);
        inputIdValidator.validate(recordIdField);



        System.out.println(BookInputServiceConstants.INSERT_BOOK_QUANTITY);

        String quantity = scanner.nextLine().trim();

        InputField quantityField = new InputField();
        quantityField.setField(InputFieldConstants.TITLE);
        quantityField.setValue(quantity);

        inputQuantityValidator.validate(quantityField);

        UpdateBookQuantityByIdRequestDTO requestDTO = new UpdateBookQuantityByIdRequestDTO();
        requestDTO.setBookRecordId(id);
        requestDTO.setQuantity(quantity);
        return requestDTO;
    }
}
